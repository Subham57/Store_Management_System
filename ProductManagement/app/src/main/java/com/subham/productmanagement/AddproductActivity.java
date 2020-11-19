package com.subham.productmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddproductActivity extends AppCompatActivity {

    private Button btnAdd;
    private EditText etProductName;
    private EditText etQuantity;
    private EditText etPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);

        Toolbar mToolbar = findViewById(R.id.add_toolbar);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Add Product");
        }

        btnAdd = findViewById(R.id.btn_add_product);
        etProductName = findViewById(R.id.et_pname);
        etQuantity = findViewById(R.id.et_quantity);
        etPrice = findViewById(R.id.et_price);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etProductName.getText().toString()) || TextUtils.isEmpty(etQuantity.getText().toString()) || TextUtils.isEmpty(etPrice.getText().toString())){
                    String messege = "All Inputs Required";
                    Toast.makeText(AddproductActivity.this, messege,Toast.LENGTH_LONG).show();
                }else {
                    AddProductRequest addProductRequest = new AddProductRequest();
                    addProductRequest.setName(etProductName.getText().toString());
                    addProductRequest.setQuantity(etQuantity.getText().toString());
                    addProductRequest.setPrice(etPrice.getText().length());
                    AddProduct(addProductRequest);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return true;
    }
    public void AddProduct(AddProductRequest addProductRequest){
        HashMap<String,Object> params = new HashMap<>();
        params.put("name",addProductRequest.getName());
        params.put("quantity",addProductRequest.getQuantity());
        params.put("price",addProductRequest.getPrice());
        ProductService productService =Apiclient.getRetrofit().create(ProductService.class);
        Call<AddProductResponse> AddProductResponseCall = productService.AddProduct(params);
        AddProductResponseCall.enqueue(new Callback<AddProductResponse>() {
            @Override
            public void onResponse(Call<AddProductResponse> call, Response<AddProductResponse> response) {
                AddProductResponse addProductResponse = response.body();
                if(response.isSuccessful()){
                    addProductResponse = response.body();
                    String Rqname = addProductRequest.getName();
                    String RPname = addProductResponse.getName();
                    if(Rqname.equals(RPname)){
                        String message = "Product added Sucessfully";
                        Toast.makeText(AddproductActivity.this,message,Toast.LENGTH_LONG).show();
                    }else{
                        String message = "Something went wrong Please Try again later";
                        Toast.makeText(AddproductActivity.this,message,Toast.LENGTH_LONG).show();
                    }
                }else{
                    String messege = "An Error occurred please try again later ";
                    Toast.makeText(AddproductActivity.this, messege, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddProductResponse> call, Throwable t) {
                String messege = t.getLocalizedMessage();
                Toast.makeText(AddproductActivity.this, messege, Toast.LENGTH_SHORT).show();
            }
        });
    }
}