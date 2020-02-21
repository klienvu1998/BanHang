package com.example.hyvu.banhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.hyvu.banhang.R;
import com.example.hyvu.banhang.model.Categories;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterCategories extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<Categories> arrayList;

    public AdapterCategories(Context context, int layout, ArrayList<Categories> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holder=new ViewHolder();
            holder.textView=view.findViewById(R.id.id_line_navigation_textView);
            holder.imageView=view.findViewById(R.id.id_line_navigation_imageView);
            view.setTag(holder);
        }
        else{
            holder= (ViewHolder) view.getTag();
        }
        Categories categories=arrayList.get(i);
        holder.textView.setText(categories.getCategory());
        Picasso.get().load(categories.getImg()).into(holder.imageView);
        return view;
    }

    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}
