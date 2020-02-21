package com.example.hyvu.banhang;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hyvu.banhang.adapter.AdapterDienThoai;
import com.example.hyvu.banhang.model.Product;
import com.example.hyvu.banhang.util.CheckConnection;
import com.example.hyvu.banhang.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DienThoai extends AppCompatActivity {

    ListView listView;
    Toolbar toolbar;
    AdapterDienThoai adapterDienThoai;
    ArrayList<Product> arr_products;
    int iddt=0;
    int page=1;
    View progressBar;
    boolean isLoading=false;
    mHandler handler;
    boolean isEndData=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        Mapping();
        if(new CheckConnection().haveConnection(getApplicationContext())){
            createListView();
            loadMoreData();
        }
        else{
            new CheckConnection().ToastConnection(getApplicationContext(),"Kiểm tra Internet");
        }
    }

    private void loadMoreData() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int start, int mid, int end) {
                if(start+mid==end && end!=0 && isLoading==false && isEndData==false){
                    isLoading=true;
                    mThread thread=new mThread();
                    thread.start();
                }
            }
        });
    }

    private void createListView() {
        getData(page);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(DienThoai.this,ChiTietSanPham.class);
                intent.putExtra("sanphamdachon",arr_products.get(i));
                startActivity(intent);
            }
        });
    }

    private void getData(int page){
        iddt=getIntent().getIntExtra("idsanpham",-1);
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(StringRequest.Method.POST, Server.URL_DIENTHOAI+page, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null && response.length()>0) {
                    isEndData=false;
                    try {
                        listView.removeFooterView(progressBar);
                        JSONArray jsonArray = new JSONArray(response);
                        for (int k=0;k<jsonArray.length();k++){
                            JSONObject jsonObject=jsonArray.getJSONObject(k);
                            int id=jsonObject.getInt("id");
                            String tensanpham=jsonObject.getString("tensanpham");
                            int giasanpham=jsonObject.getInt("giasanpham");
                            String hinhanhsanpham=jsonObject.getString("hinhanhsanpham");
                            String motasanpham=jsonObject.getString("motasanpham");
                            int idsanpham=jsonObject.getInt("idsanpham");
                            arr_products.add(new Product(id,tensanpham,giasanpham,hinhanhsanpham,motasanpham,idsanpham));
                            adapterDienThoai.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    isEndData=true;
                    listView.removeFooterView(progressBar);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("idsanpham",String.valueOf(iddt));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void Mapping() {
        listView=findViewById(R.id.id_dienthoai_listview);
        toolbar=findViewById(R.id.id_dienthoai_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        arr_products=new ArrayList<>();
        adapterDienThoai=new AdapterDienThoai(getApplicationContext(),R.layout.line_dienthoai,arr_products);
        listView.setAdapter(adapterDienThoai);
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        progressBar=inflater.inflate(R.layout.loadmore,null);
        handler=new mHandler();
    }

    class mHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    listView.addFooterView(progressBar);
                    break;
                case 1:
                    getData(++page);
                    isLoading=false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    class mThread extends Thread{
        @Override
        public void run() {
            handler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message=handler.obtainMessage(1);
            handler.sendMessage(message);
            super.run();
        }
    }
}
