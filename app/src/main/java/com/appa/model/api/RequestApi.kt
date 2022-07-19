package com.adviserall22spdaslld.model.response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RequestWebService {
    private lateinit var api: RequestApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        api = retrofit.create(RequestApi::class.java)


    }

    @Throws(Exception::class)
    suspend fun getRequest(): MainResponses {
        return api.getRequest()
    }

//    suspend fun getRequest(value: String): MainResponses {
//        return  api.getRequest(value)
//   }

    interface RequestApi {
        @Throws(Exception::class)
        @GET("/comments")
        suspend fun getRequest(): MainResponses
    }

//  interface RequestApi {
//        @GET("/comments")
//        suspend fun getRequest(@Query("advise") value: String): MainResponses
//   }


}