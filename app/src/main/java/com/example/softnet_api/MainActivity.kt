package com.example.softnet_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.softnet_api.retrofitInstance.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://hunarindia.org.in/") // Base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)


        fetchMembershipData()
    }

    private fun fetchMembershipData() {
        val call = apiService.getMembershipData()
        call.enqueue(object : Callback<MembershipData> {
            override fun onResponse(call: Call<MembershipData>, response: Response<MembershipData>) {
                if (response.isSuccessful) {
                    val membershipData = response.body()
                    // Update your UI with the fetched data
                    updateTextViews(membershipData)
                }
            }

            override fun onFailure(call: Call<MembershipData>, t: Throwable) {
                // Handle the error
            }
        })
    }

    private fun updateTextViews(data: MembershipData?) {
        // Use data to update your UI elements
        val imageView: ImageView = findViewById(R.id.imageView)
        val benefitsTextView: TextView = findViewById(R.id.benefitsTextView)
        val howItWorksTextView: TextView = findViewById(R.id.howItWorksTextView)
        val termsTextView: TextView = findViewById(R.id.termsTextView)

        data?.let {
            // Load image using Glide
            Glide.with(this).load(data.image).into(imageView)

            // Update TextViews
            benefitsTextView.text = data.program_benefits.joinToString("\n")
            howItWorksTextView.text = data.how_it_works.joinToString("\n")
            termsTextView.text = data.terms_conditions.joinToString("\n")
        }
    }
}