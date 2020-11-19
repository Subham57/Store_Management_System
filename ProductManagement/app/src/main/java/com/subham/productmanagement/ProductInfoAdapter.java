package com.subham.productmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductInfoAdapter extends RecyclerView.Adapter<ProductInfoAdapter.ProductInfoHolder>{

    public Context context;
    public ArrayList<Products> pronew;
    public ProductClickLisener lisener;

    public void setlisener(ProductClickLisener lisener){
        this.lisener = lisener;
    }


    public ProductInfoAdapter(Context context, ArrayList<Products> pro1){
        this.context = context;
        this.pronew = pro1;
    }

    @NonNull
    @Override
    public ProductInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductInfoHolder holder = new ProductInfoHolder(LayoutInflater.from(context).inflate(R.layout.cell_product,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductInfoHolder holder, int position) {
        Products products = pronew.get(position);
        holder.tv_product_name.setText(products.getName());
        holder.tv_quantity.setText(products.getQuantity());
        holder.tv_price.setText(String.valueOf(products.getPrice()));

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lisener != null){
                    lisener.onUpdateClicked(products);
                }
            }
        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lisener != null){
                    lisener.onDeleteClicked(products);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return pronew.size();
    }

    class ProductInfoHolder extends RecyclerView.ViewHolder {
        public TextView tv_product_name;
        private TextView tv_quantity;
        private TextView tv_price;
        private Button btn_edit;
        private Button btn_delete;

        public ProductInfoHolder(@NonNull View itemView) {
            super(itemView);

            tv_product_name = itemView.findViewById(R.id.tv_product_name);
            tv_quantity = itemView.findViewById(R.id.tv_product_quantity);
            tv_price = itemView.findViewById(R.id.tv_product_price);
            btn_edit = itemView.findViewById(R.id.ib_edit);
            btn_delete = itemView.findViewById(R.id.ib_delete);
        }
    }


    public interface ProductClickLisener{
        void onUpdateClicked(Products products);

        void onDeleteClicked(Products products);
    }
}
