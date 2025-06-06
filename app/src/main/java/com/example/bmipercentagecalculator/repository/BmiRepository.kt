package com.example.bmipercentagecalculator.repository

import javax.inject.Inject
import kotlin.math.pow

class BmiRepository @Inject constructor()  {
    fun calculateBmiAndBfp(weightKg: Double, heightCm: Double, age: Int, isMale: Boolean): BmiCalculateResult {
        val heightM = heightCm / 100.0
        val bmi = weightKg / (heightM.pow(2))
        val bmiCategory = getBmiCategory(bmi)

        val bfp: Double =
            if (isMale) {
            (1.20 * bmi) + (0.23 * age) - 16.2
        } else {
            (1.20 * bmi) + (0.23 * age) - 5.4
        }
        val bfpCategory = getBfpCategory(bfp)

        return BmiCalculateResult(bmi, bfp, bmiCategory, bfpCategory)
    }

    private fun getBmiCategory(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Kekurangan Berat Badan"
            bmi >= 18.5 && bmi < 24.9 -> "Berat Badan Normal"
            bmi >= 25.0 && bmi < 29.9 -> "Kelebihan Berat Badan"
            else -> "Obesitas"
        }
    }

    private fun getBfpCategory(bfp: Double): String {
        return when {
            bfp < 10.0 -> "Sangat Rendah (< 10%)"
            bfp >= 10.0 && bfp < 20.0 -> "(10-20%) Rendah "
            bfp >= 20.0 && bfp < 25.0 -> "(21-25%) Normal "
            bfp >= 25.0 && bfp < 30.0 -> "(26-30%)Tinggi "
            else -> "(> 30%) Sangat Tinggi "
        }
    }

}