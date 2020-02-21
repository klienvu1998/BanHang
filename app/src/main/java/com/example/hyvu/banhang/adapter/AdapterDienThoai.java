package com.example.hyvu.banhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hyvu.banhang.DienThoai;
import com.example.hyvu.banhang.R;
import com.example.hyvu.banhang.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterDienThoai extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Product> arr_DienThoai;

    public AdapterDienThoai(Context context, int layout, ArrayList<Product> arr_DienThoai) {
        this.context = context;
        this.layout = layout;
        this.arr_DienThoai = arr_DienThoai;
    }

    @Override
    public int getCount() {
        return arr_DienThoai.size();
    }

    @Override
    public Object getItem(int i) {
        return arr_DienThoai.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder=new ViewHolder();
            holder.tv_mota=view.findViewById(R.id.id_line_dienthoai_tv_mota);
            holder.tv_tensp=view.findViewById(R.id.id_line_dienthoai_tv_tensp);
            holder.img=view.findViewById(R.id.id_line_dienthoai_img);
            holder.tv_giasp=view.findViewById(R.id.id_line_dienthoai_tv_giasp);
            view.setTag(holder);
        }
        else {
            holder= (ViewHolder) view.getTag();
        }
        Product product=arr_DienThoai.get(i);
        holder.tv_tensp.setText(product.getName());
        holder.tv_giasp.setText(product.getPrice()+"");
        holder.tv_mota.setText(product.getDesciption());
        Picasso.get().load(product.getImg()).into(holder.img);
        return view;
    }
    class ViewHolder{
        ImageView img;
        TextView tv_tensp,tv_giasp,tv_mota;
    }
}
