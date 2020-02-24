package com.example.hyvu.banhang;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.hyvu.banhang.model.GioHang;
import com.example.hyvu.banhang.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChiTietSanPham extends AppCompatActivity {

    ImageView img;
    TextView tv_tensp,tv_giasp,tv_mota;
    Spinner spinner;
    Button btn_add;
    Toolbar toolbar;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Mapping();
        CreateToolBar();
        product= (Product) getIntent().getSerializableExtra("sanphamdachon");
        if(product.getName()!=null) {
            tv_tensp.setText(product.getName());
            tv_mota.setText(product.getDesciption());
            tv_giasp.setText(product.getPrice() + "");
            Picasso.get().load(product.getImg()).into(img);
        }
    }

    private void CreateToolBar() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.arr_gioHang.size()>0){
                    int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean isExist=false;
                    for(int i=0;i<MainActivity.arr_gioHang.size();i++){
                        if(MainActivity.arr_gioHang.get(i).getIdsp()==product.getId()){
                            isExist=true;
                            if((MainActivity.arr_gioHang.get(i).getSoluongsp() + soluong) < 10) {
                                int soluongnew = soluong + MainActivity.arr_gioHang.get(i).getSoluongsp();
                                int total_price = soluongnew * product.getPrice();
                                MainActivity.arr_gioHang.get(i).setGiasp(total_price);
                                MainActivity.arr_gioHang.get(i).setSoluongsp(soluongnew);
                            }
                            else{
                                int soluongnew = 9;
                                int total_price = soluongnew * product.getPrice();
                                MainActivity.arr_gioHang.get(i).setGiasp(total_price);
                                MainActivity.arr_gioHang.get(i).setSoluongsp(soluongnew);
                            }
                        }
                    }
                    if(isExist==false){
                        int total_price=soluong*product.getPrice();
                        MainActivity.arr_gioHang.add(new GioHang(product.getId(),product.getName(),total_price,product.getImg(),soluong));
                    }
                }
                else{
                    int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                    int total_price=soluong*product.getPrice();
                    MainActivity.arr_gioHang.add(new GioHang(product.getId(),product.getName(),total_price,product.getImg(),soluong));
                }
                Intent intent=new Intent(ChiTietSanPham.this,ChiTietGioHang.class);
                startActivity(intent);
            }
        });
    }

    private void Mapping() {
        toolbar=findViewById(R.id.id_ctsp_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        img=findViewById(R.id.id_ctsp_img);
        tv_giasp=findViewById(R.id.id_ctsp_tv_giasp);
        tv_mota=findViewById(R.id.id_ctsp_tv_mota);
        tv_tensp=findViewById(R.id.id_ctsp_tv_tensp);
        btn_add=findViewById(R.id.id_ctsp_button_add);
        spinner=findViewById(R.id.id_ctsp_spinner);
        Integer[] soluong ={1,2,3,4,5,6,7,8,9};
        ArrayAdapter<Integer> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(adapter);
    }
}
