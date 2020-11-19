package com.subham.productmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeshboardActivity extends AppCompatActivity implements ProductInfoAdapter.ProductClickLisener {


    private RecyclerView mRcProductInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deshboard);
        Toolbar mToolbar;
        mToolbar = findViewById(R.id.d_toolbar);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Deshbord");
        }

        loadDatatoRecycler();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.madd_product){
            startActivity(new Intent(DeshboardActivity.this,AddproductActivity.class));
        }else {
            startActivity(new Intent(DeshboardActivity.this,DeshboardActivity.class));
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.drawer_manu_deshboard,menu);
        return true;
    }

    @Override
    public void onUpdateClicked(Products products) {
//        startActivity(new Intent(DeshboardActivity.this,UpdateProduct.class));
        Intent intent = new Intent(DeshboardActivity.this, UpdateProduct.class);
        intent.putExtra("Product",products);
        startActivity(intent);
    }

    @Override
    public void onDeleteClicked(Products products) {
        ProductService productService = Apiclient.getRetrofit().create(ProductService.class);
        Call<DeleteResponse> deleteResponseCall = productService.deleteProduct(products.getId());
        deleteResponseCall.enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                loadDatatoRecycler();
                String messege = "Product Deleted Sucessfuly";
                Toast.makeText(DeshboardActivity.this, messege, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                String messege = t.getLocalizedMessage();
//                Toast.makeText(DeshboardActivity.this, messege, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadDatatoRecycler(){
        mRcProductInfos = findViewById(R.id.rc_product_info);
        mRcProductInfos.setLayoutManager(new LinearLayoutManager(DeshboardActivity.this, RecyclerView.VERTICAL, false));
        ProductService productService = Apiclient.getRetrofit().create(ProductService.class);
        Call<ArrayList<Products>> ProductResponseCall = productService.Products();
        ProductResponseCall.enqueue(new Callback<ArrayList<Products>>() {
            ArrayList<Products> pro = new ArrayList<>();
            @Override
            public void onResponse(Call<ArrayList<Products>> call, Response<ArrayList<Products>> response) {
                if (response.isSuccessful()){
                    pro = response.body();
                    ProductInfoAdapter adapter = new ProductInfoAdapter(DeshboardActivity.this,pro);
                    adapter.setlisener(DeshboardActivity.this);
                    mRcProductInfos.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Products>> call, Throwable t) {
                String messege = t.getLocalizedMessage();
                Toast.makeText(DeshboardActivity.this, messege, Toast.LENGTH_SHORT).show();
            }
        });
    }
}