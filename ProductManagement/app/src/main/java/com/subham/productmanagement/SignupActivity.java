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

public class SignupActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnSignup;
    private EditText et_username;
    private EditText et_password;
    private EditText et_conpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnLogin = findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });


        btnSignup = findViewById(R.id.btn_signup);
        et_username = findViewById(R.id.et_username_signup);
        et_password = findViewById(R.id.et_password_signup);
        et_conpassword = findViewById(R.id.et_conpassword);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_username.getText().toString()) || TextUtils.isEmpty(et_password.getText().toString()) || TextUtils.isEmpty(et_conpassword.getText().toString())){
                    String messege = "All Inputs Required";
                    Toast.makeText(SignupActivity.this, messege,Toast.LENGTH_LONG).show();
                }
                else{
                    String Password = et_password.getText().toString();
                    String ConPassword = et_conpassword.getText().toString();
                    if (Password.equals(ConPassword)){
                        SignupRequest signupRequest = new SignupRequest();
                        signupRequest.username = et_username.getText().toString();
                        signupRequest.password = et_password.getText().toString();
                        SignupUser(signupRequest);

                    }else{
                        String messege = "Password Doesn't match with conform password.";
                        Toast.makeText(SignupActivity.this, messege,Toast.LENGTH_LONG).show();
                        String messege1 = "Please cheack before signup.";
                        Toast.makeText(SignupActivity.this, messege1,Toast.LENGTH_LONG).show();
                    }
                }

            }

            public void SignupUser(SignupRequest signupRequest){
                HashMap<String,Object> params = new HashMap<>();
                params.put("username",signupRequest.getUsername());
                params.put("password",signupRequest.getPassword());
                UserService userService = Apiclient.getRetrofit().create(UserService.class);
                Call<SignupResponse> SignupResponseCall = userService.SignupUser(params);
                SignupResponseCall.enqueue(new Callback<SignupResponse>() {
                    @Override
                    public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                        SignupResponse signupResponse  = response.body();
                        if(response.isSuccessful()){
                            signupResponse = response.body();
                            String SRPUsername = signupResponse.username;
                            String SRQUsername = signupRequest.username;
                            if (SRPUsername.equals(SRQUsername)){
                                String messege = "Sucessfully Signup.";
                                Toast.makeText(SignupActivity.this, messege, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this,DeshboardActivity.class));
                                String messege1 = "Welcome to Store ";
                                Toast.makeText(SignupActivity.this, messege1+signupResponse.username, Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            String messege = "An Error occurred please try again later ";
                            Toast.makeText(SignupActivity.this, messege, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SignupResponse> call, Throwable t) {
                        String messege = "SignUp failed please try after some time.";
                        Toast.makeText(SignupActivity.this, messege,Toast.LENGTH_LONG).show();
                    }
                });

            }

        });
    }
    public void Login(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}