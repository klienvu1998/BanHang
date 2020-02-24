package com.example.hyvu.banhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hyvu.banhang.adapter.AdapterGioHang;
import com.example.hyvu.banhang.model.GioHang;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietGioHang extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    static TextView tv_thanhtien;
    Button btn_thanhtoan;
    Button btn_tieptucmuahang;
    public static AdapterGioHang adapterGioHang;
    static int tongtien=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_gio_hang);
        Mapping();
        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_tieptucmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChiTietGioHang.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public static void getData() {
        tongtien=0;
        for (int i=0;i<MainActivity.arr_gioHang.size();i++){
            tongtien+=MainActivity.arr_gioHang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        tv_thanhtien.setText(decimalFormat.format(tongtien)+" Đ");
    }


    private void Mapping() {
        toolbar=findViewById(R.id.id_chitietdonhang_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView=findViewById(R.id.id_chitietdonhang_listview);
        tv_thanhtien=findViewById(R.id.id_chitietdonhang_tv_tongtien);
        btn_thanhtoan=findViewById(R.id.id_chitietdonhang_button_thanhtoan);
        btn_tieptucmuahang=findViewById(R.id.id_chitietdonhang_button_tieptucmuahang);
        adapterGioHang=new AdapterGioHang(getApplicationContext(),R.layout.line_chi_tiet_gio_hang,MainActivity.arr_gioHang);
        listView.setAdapter(adapterGioHang);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
