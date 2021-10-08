package com.kimopay.channelnewsindonesia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.model.Konten;

import java.util.ArrayList;

public class KontenAdapter extends RecyclerView.Adapter<KontenAdapter.MyHolderView> {

    private Context context;
    private ArrayList<Konten> kontens;

    public KontenAdapter(Context context, ArrayList<Konten> kontens) {
        this.context = context;
        this.kontens = kontens;
    }

    @NonNull
    @Override
    public KontenAdapter.MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_konten, parent, false);
        KontenAdapter.MyHolderView myHolderView = new KontenAdapter.MyHolderView(view);
        return myHolderView;
    }

    @Override
    public void onBindViewHolder(@NonNull KontenAdapter.MyHolderView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class MyHolderView extends RecyclerView.ViewHolder {
        public MyHolderView(@NonNull View itemView) {
            super(itemView);
        }
    }
}
