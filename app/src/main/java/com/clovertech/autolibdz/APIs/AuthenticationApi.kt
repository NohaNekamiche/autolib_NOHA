package api

import model.Authentication
import model.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {

    @POST("signin")
    suspend fun pushAuthentication(
        @Body authetication : Authentication
    ): Response<Token>

}