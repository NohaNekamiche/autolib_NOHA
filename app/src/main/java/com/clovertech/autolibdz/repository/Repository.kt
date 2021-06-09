package com.clovertech.autolibdz.repository

import com.clovertech.autolibdz.utils.RetrofitInst
import model.Authentication
import model.Token
import retrofit2.Response


class Repository {

    suspend fun pushAuthentication(authentication : Authentication) : Response<Token> {
        return RetrofitInst.authenticationApi.pushAuthentication(authentication)
    }

}