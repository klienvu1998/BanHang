package com.example.hyvu.banhang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hyvu.banhang.R;
import com.example.hyvu.banhang.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterNewProduct extends RecyclerView.Adapter<AdapterNewProduct.ViewHolder> {
    View view;
    Context context;
    int layout;
    ArrayList<Product> arr_products;

    public AdapterNewProduct(Context context,int layout ,ArrayList<Product> arr_products) {
        this.context = context;
        this.arr_products = arr_products;
        this.layout=layout;
    }

    @NonNull
    @Override
    public AdapterNewProduct.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNewProduct.ViewHolder viewHolder, int i) {
        viewHolder.tv_tensp.setText(arr_products.get(i).getName());
        viewHolder.tv_giasp.setText(arr_products.get(i).getPrice()+"");
        Picasso.get().load(arr_products.get(i).getImg()).into(viewHolder.img_sp);
    }

    @Override
    public int getItemCount() {
        return arr_products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tensp,tv_giasp;
        ImageView img_sp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tensp=itemView.findViewById(R.id.id_line_spmoi_tv_tensp);
            tv_giasp=itemView.findViewById(R.id.id_line_spmoi_tv_giasp);
            img_sp=itemView.findViewById(R.id.id_line_spmoi_img_sp);
        }
    }
}
