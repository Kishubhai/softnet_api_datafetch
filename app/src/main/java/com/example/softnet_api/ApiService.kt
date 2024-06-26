package com.example.softnet_api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/v3/member-subscription-image/144?type=term")
    fun getMembershipData(): Call<MembershipData>
}