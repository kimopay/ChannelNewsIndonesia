package com.kimopay.channelnewsindonesia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.data.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolderView> {

    private Context context;
    private ArrayList<Category> categories;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryAdapter.MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_kategori_detail, parent, false);
        CategoryAdapter.MyHolderView myHolderView = new CategoryAdapter.MyHolderView(view);
        return myHolderView;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyHolderView holder, int position) {

        holder.tv_kategori.setText(categories.get(position).getCategory_name());

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder {
        TextView tv_kategori;
        public MyHolderView(@NonNull View itemView) {
            super(itemView);
            tv_kategori = itemView.findViewById(R.id.tv_kategori);
        }
    }
}
