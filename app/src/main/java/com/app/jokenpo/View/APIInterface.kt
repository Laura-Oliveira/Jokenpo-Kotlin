package com.app.jokenpo.View

import com.app.jokenpo.Model.Computer
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body

public interface APIInterface
{
    @GET("results")
    fun getComputer(): Call<Computer>

   // fun getComputer(): Call<List<Computer>>

}