package com.subham.productmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnSignup;
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        btnLogin = findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etUsername.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())){
                    String messege = "All Inputs Required";
                    Toast.makeText(LoginActivity.this, messege,Toast.LENGTH_LONG).show();
                }else{
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setUsername(etUsername.getText().toString());
                    loginRequest.setPassword(etPassword.getText().toString());
                    Login(loginRequest);
                }
            }
        });


        btnSignup = findViewById(R.id.btnsignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signup();
            }
        });
    }

    public void Signup(){
        Intent intent = new Intent(this,SignupActivity.class);
        startActivity(intent);
    }

    public void Login(LoginRequest loginRequest){

        HashMap<String,Object> params = new HashMap<>();
        params.put("username",loginRequest.getUsername());
        params.put("password",loginRequest.getPassword());
        UserService userService = Apiclient.getRetrofit().create(UserService.class);
        Call<LoginResponse> loginResponseCall = userService.LoginUser(params);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if(response.isSuccessful()){
                    loginResponse = response.body();
                    String responsemessage = loginResponse.getMessege();
//                    System.out.println(responsemessage);
                    String code = "Sucess";
                    if (responsemessage.equals(code)){
                        String messege = "Hello ";
                        Toast.makeText(LoginActivity.this,messege+loginRequest.getUsername(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,DeshboardActivity.class));
                    }else{
                        String messege = "Invalid Creadential";
                        Toast.makeText(LoginActivity.this,messege, Toast.LENGTH_SHORT).show();
                    }

                }else{
                    String messege = "An Error occurred please try again later ";
                    Toast.makeText(LoginActivity.this, messege, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String messege = t.getLocalizedMessage();
                Toast.makeText(LoginActivity.this, messege, Toast.LENGTH_SHORT).show();
            }
        });
    }
}