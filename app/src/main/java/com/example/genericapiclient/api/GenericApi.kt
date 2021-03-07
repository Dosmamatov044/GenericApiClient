package com.example.genericapiclient.api

import com.example.genericapiclient.model.GenericModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface GenericApi {

    @GET("/random_ten")
    @Headers("Content-type: application/json")
    fun  getGenericList(): Single<GenericModel>



}