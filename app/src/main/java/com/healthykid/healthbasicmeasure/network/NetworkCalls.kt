package com.healthykid.healthbasicmeasure.network

import com.healthykid.healthbasicmeasure.modelclass.BasicDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL="https://health-measure.firebaseio.com/"
interface NetworkCalls {
    @GET("Students/{id}.json")
    fun getStudentBasicInfo(@Path("id") id:String):Call<BasicDetails>
}