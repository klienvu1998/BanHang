package com.example.hyvu.banhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hyvu.banhang.ChiTietGioHang;
import com.example.hyvu.banhang.MainActivity;
import com.example.hyvu.banhang.R;
import com.example.hyvu.banhang.model.GioHang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterGioHang extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<GioHang> arr_gioHang;
    int soluongmoi=0;

    public AdapterGioHang(Context context, int layout, ArrayList<GioHang> arr_gioHang) {
        this.context = context;
        this.layout = layout;
        this.arr_gioHang = arr_gioHang;
    }



    @Override
    public int getCount() {
        return arr_gioHang.size();
    }

    @Override
    public Object getItem(int i) {
        return arr_gioHang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if(view==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder=new ViewHolder();
            holder.btn_up=view.findViewById(R.id.id_line_chitiet_giohang_button_up);
            holder.btn_down=view.findViewById(R.id.id_line_chitiet_giohang_button_down);
            holder.img=view.findViewById(R.id.id_line_chitiet_giohang_img);
            holder.tv_dongia=view.findViewById(R.id.id_line_chitiet_giohang_tv_dongia);
            holder.tv_name=view.findViewById(R.id.id_line_chitiet_giohang_tv_name);
            holder.tv_soluong=view.findViewById(R.id.id_line_chitiet_giohang_tv_soluong);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        GioHang gioHang=arr_gioHang.get(i);
        holder.tv_name.setText(gioHang.getTensp());
        holder.tv_soluong.setText(gioHang.getSoluongsp()+"");
        holder.tv_dongia.setText(gioHang.getGiasp()+"");
        Picasso.get().load(gioHang.getHinhsp()).into(holder.img);
        if(soluongmoi==9){
            holder.btn_up.setVisibility(View.INVISIBLE);
        }
        holder.btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soluongmoi=arr_gioHang.get(i).getSoluongsp();
                soluongmoi-=1;
                if(soluongmoi==0){
                    MainActivity.arr_gioHang.remove(i);
                    ChiTietGioHang.adapterGioHang.notifyDataSetChanged();
                }
                else{
                    holder.btn_up.setVisibility(View.VISIBLE);
                    holder.tv_soluong.setText(soluongmoi+"");
                    MainActivity.arr_gioHang.get(i).setGiasp((soluongmoi*MainActivity.arr_gioHang.get(i).getGiasp())/MainActivity.arr_gioHang.get(i).getSoluongsp());
                    MainActivity.arr_gioHang.get(i).setSoluongsp(soluongmoi);
                    holder.tv_dongia.setText(MainActivity.arr_gioHang.get(i).getGiasp()+"");
                }
                ChiTietGioHang.getData();
            }
        });
        holder.btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soluongmoi=arr_gioHang.get(i).getSoluongsp();
                soluongmoi+=1;
                if(soluongmoi>9){
                    Toast.makeText(context, "Maximum", Toast.LENGTH_SHORT).show();
                    holder.btn_up.setVisibility(View.INVISIBLE);
                    soluongmoi-=1;
                }
                else{
                    holder.tv_soluong.setText(soluongmoi+"");
                    MainActivity.arr_gioHang.get(i).setGiasp((soluongmoi*MainActivity.arr_gioHang.get(i).getGiasp())/MainActivity.arr_gioHang.get(i).getSoluongsp());
                    MainActivity.arr_gioHang.get(i).setSoluongsp(soluongmoi);
                    holder.tv_dongia.setText(MainActivity.arr_gioHang.get(i).getGiasp()+"");
                }
                ChiTietGioHang.getData();
            }
        });

        return view;
    }

    class ViewHolder{
        ImageView img;
        TextView tv_name,tv_dongia,tv_soluong;
        Button btn_up,btn_down;
    }
}
