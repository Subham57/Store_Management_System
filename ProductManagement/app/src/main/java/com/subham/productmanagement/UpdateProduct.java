package com.subham.productmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProduct extends AppCompatActivity {


    public TextView et_id;
    public EditText et_p_name;
    public EditText et_p_price;
    public EditText et_p_quantity;
    public Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        Intent intent = getIntent();
        Products products1 = intent.getParcelableExtra("Product");

        et_id = findViewById(R.id.et_id);
        et_p_name = findViewById(R.id.et_pr_name);
        et_p_price = findViewById(R.id.et_pr_price);
        et_p_quantity = findViewById(R.id.et_pr_quantity);
        btn_update = findViewById(R.id.btn_update_product);

        et_id.setText(String.valueOf(products1.getId()));
        et_p_name.setText(String.valueOf(products1.getName()));
        et_p_price.setText(String.valueOf(products1.getPrice()));
        et_p_quantity.setText(String.valueOf(products1.getQuantity()));

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_p_name.getText().toString()) || TextUtils.isEmpty(et_p_quantity.getText().toString()) || TextUtils.isEmpty(et_p_price.getText().toString())){
                    String messege = "All Inputs Required";
                    Toast.makeText(UpdateProduct.this, messege,Toast.LENGTH_LONG).show();
                }else{
                    HashMap<String,Object> params = new HashMap<>();
                    params.put("id",et_id.getText().toString());
                    params.put("name",et_p_name.getText().toString());
                    params.put("quantity",et_p_quantity.getText().toString());
                    params.put("price",et_p_price.getText().toString());
                    ProductService productService = Apiclient.getRetrofit().create(ProductService.class);
                    Call<AddProductResponse> updataResponseCall = productService.UpdateProduct(params);
                    updataResponseCall.enqueue(new Callback<AddProductResponse>() {
                        @Override
                        public void onResponse(Call<AddProductResponse> call, Response<AddProductResponse> response) {
                            AddProductResponse addProductResponse = response.body();
                            if (response.isSuccessful()){
                                String messege ="Updated Sucessfully";
                                Toast.makeText(UpdateProduct.this, messege, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(UpdateProduct.this,DeshboardActivity.class));
                            }
                        }

                        @Override
                        public void onFailure(Call<AddProductResponse> call, Throwable t) {
                            String messege = t.getLocalizedMessage();
                            Toast.makeText(UpdateProduct.this, messege, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}