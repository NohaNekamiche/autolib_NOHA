package com.clovertech.autolibdz.APIs


import com.clovertech.autolibdz.DataClasses.Pay
import com.clovertech.autolibdz.ui.card.Card
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServiceCards {

    @GET("all")
    suspend fun getAllCards(): Response<MutableList<Card>>

    @POST("pay")
    suspend fun createPay(@Body post: Pay): Response<Pay>

    @POST("add")
    suspend fun createCard(@Body post: Card): Response<Card>

}
