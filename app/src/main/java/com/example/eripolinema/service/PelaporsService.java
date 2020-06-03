package com.example.eripolinema.service;

import com.example.eripolinema.model.Pelapor;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class PelaporsService {
    // Fungsi ini untuk memanggil API http://192.168.88.20/latihan1/login.php
    // penamaan link sesuai dengan localhost masing-masing
    @FormUrlEncoded
    @POST("/api/user")
    public Call<Pelapor>loginRequest(@Field("kode_user") String kode_user,
                                     @Field("password") String password) {
        return null;
    }

    // Fungsi ini untuk memanggil API http://192.168.88.20/latihan1/register.php

    @FormUrlEncoded
    @POST("/api/user")
    public Call<Pelapor>registerRequest(@Field("id_user") String id_user,
                                        @Field("nama") String nama,
                                        @Field("email") String email,
                                        @Field("jurusan") String jurusan) {
        return null;
    }


}
