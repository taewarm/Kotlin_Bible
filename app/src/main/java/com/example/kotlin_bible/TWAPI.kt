package com.example.kotlin_bible

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TWAPI {
    @GET("{id}")
    fun requestSearch(
        @Path("id") id: String
//        ,@Query("userNo") id2 : String
    ):Call<List<BibleData>>

}