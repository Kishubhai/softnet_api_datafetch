package com.example.softnet_api

data class MembershipData(
    val image: String,
    val program_benefits: List<String>,
    val how_it_works: List<String>,
    val terms_conditions: List<String>
)
