package com.example.hyvu.banhang;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hyvu.banhang.adapter.AdapterCategories;
import com.example.hyvu.banhang.adapter.AdapterNewProduct;
import com.example.hyvu.banhang.model.Categories;
import com.example.hyvu.banhang.model.GioHang;
import com.example.hyvu.banhang.model.Product;
import com.example.hyvu.banhang.util.CheckConnection;
import com.example.hyvu.banhang.util.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    AdapterCategories adapterCategories;
    ArrayList<Categories> arr_Categories;
    ListView listView;
    ArrayList<Product> arr_Product;
    AdapterNewProduct adapterNewProduct;
    public static ArrayList<GioHang> arr_gioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        createActionbar();
        if(new CheckConnection().haveConnection(getApplicationContext())){
            createViewFlipper();
            createListView();
            createRecyclerView();
        }
        else{
            new CheckConnection().ToastConnection(getApplicationContext(),"Please Connect Internet");
        }
    }

    private void createRecyclerView() {
        final RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        final JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Server.URL_SANPHAMMOI, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){
                    for(int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject=response.getJSONObject(i);
                            int id=jsonObject.getInt("id");
                            String tensanpham=jsonObject.getString("tensanpham");
                            int giasanpham=jsonObject.getInt("giasanpham");
                            String hinhanhsanpham=jsonObject.getString("hinhanhsanpham");
                            String motasanpham=jsonObject.getString("motasanpham");
                            int idsanpham=jsonObject.getInt("idsanpham");
                            arr_Product.add(new Product(id,tensanpham,giasanpham,hinhanhsanpham,motasanpham,idsanpham));
                            adapterNewProduct.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void createListView() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Server.URL_LOAISANPHAM, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){
                    for(int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject=response.getJSONObject(i);
                            int id=jsonObject.getInt("id");
                            String category=jsonObject.getString("tenloaisanpham");
                            String img=jsonObject.getString("hinhanhloaisanpham");
                            arr_Categories.add(new Categories(id,category,img));
                            adapterCategories.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (i){
                    case 0:
                        break;
                    case 1:
                        Intent intent=new Intent(MainActivity.this,DienThoai.class);
                        intent.putExtra("idsanpham",i);
                        startActivity(intent);
                        break;
                    case 2:
                        break;
                }
            }
        });
    }

    private void createViewFlipper() {
        ArrayList<String> arr_Banner = new ArrayList<>();
        arr_Banner.add("https://www.samsung.com/global/galaxy/main/images/banner_galaxy-s9_intro.jpg");
        arr_Banner.add("https://www.mobileciti.com.au/media/wysiwyg/mobileciti/landingpages/Samsung-S10-TopBanner1.jpg");
        for (int i=0;i<arr_Banner.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(arr_Banner.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_in=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_viewflipper_in);
        Animation animation_out=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_viewflipper_out);
        viewFlipper.setInAnimation(animation_in);
        viewFlipper.setOutAnimation(animation_out);
    }

    private void createActionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_camera);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void mapping() {
        toolbar=findViewById(R.id.id_main_toolbar);
        viewFlipper=findViewById(R.id.id_main_viewflipper);
        recyclerView=findViewById(R.id.id_main_recyclerview);
        navigationView=findViewById(R.id.id_main_navigation);
        drawerLayout=findViewById(R.id.id_main_drawerlayout);
        arr_Categories=new ArrayList<>();
        arr_Categories.add(new Categories(0,"Trang chủ","https://lh3.googleusercontent.com/proxy/Eq2pX344EvDDOJ3RZWN_5wDqToftxpMt5dI2OtIEmHTGkc0Yxuii89AIKCJyo5xxZ7-UD5FL7kQHS-yEZAXq2yiFJzT4uUWJcTfPj1KUZZpYrlY"));
        listView=findViewById(R.id.id_main_listview);
        adapterCategories=new AdapterCategories(getApplicationContext(),R.layout.line_navigationview,arr_Categories);
        listView.setAdapter(adapterCategories);
        //Tao Adapter sp moi
        arr_Product=new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setHasFixedSize(true);
        adapterNewProduct=new AdapterNewProduct(MainActivity.this,R.layout.line_spmoi,arr_Product);
        recyclerView.setAdapter(adapterNewProduct);
        if(arr_gioHang==null){
            arr_gioHang=new ArrayList<>();
        }
    }

    public void getSPmoi(int i){
        Intent intent=new Intent(MainActivity.this,ChiTietSanPham.class);
        intent.putExtra("sanphamdachon",arr_Product.get(i));
        startActivity(intent);
    }
}
