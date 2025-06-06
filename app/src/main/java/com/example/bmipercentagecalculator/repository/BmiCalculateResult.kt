package com.example.bmipercentagecalculator.repository

data class BmiCalculateResult(
    val bmi: Double,
    val bfp: Double,
    val bmiCategory: String,
    val bfpCategory: String
)
