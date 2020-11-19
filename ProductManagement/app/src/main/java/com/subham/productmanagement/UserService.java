package com.subham.productmanagement;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("login/")
    Call<LoginResponse> LoginUser(@Body HashMap<String, Object> params);

    @POST("addUser/")
    Call<SignupResponse> SignupUser(@Body HashMap<String, Object> params);
}
