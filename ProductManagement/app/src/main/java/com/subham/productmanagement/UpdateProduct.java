package com.subham.productmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class UpdateProduct extends AppCompatActivity {


    public EditText et_id;
    public EditText et_p_name;
    public EditText et_p_price;
    public EditText et_p_quantity;
    public Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        Intent i = getIntent();
        Products products1 = (Products) i.getSerializableExtra("Product");

        et_id = findViewById(R.id.et_id);
        et_p_name = findViewById(R.id.et_pr_name);
        et_p_price = findViewById(R.id.et_price);
        et_p_quantity = findViewById(R.id.et_pr_quantity);
        btn_update = findViewById(R.id.btn_update_product);

        et_id.setText(products1.getId());
        et_p_name.setText(products1.getName());
        et_p_price.setText(products1.getPrice());
        et_p_quantity.setText(products1.getQuantity());


    }
}