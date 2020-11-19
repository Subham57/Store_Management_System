package com.subham.productmanagement;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {
    @POST("addProduct/")
    Call<AddProductResponse> AddProduct(@Body HashMap<String, Object> params);

    @GET("products/")
    Call<ArrayList<Products>> Products();

    @DELETE("delete/{id}/")
    Call<DeleteResponse> deleteProduct(@Path("id") int id);
}
