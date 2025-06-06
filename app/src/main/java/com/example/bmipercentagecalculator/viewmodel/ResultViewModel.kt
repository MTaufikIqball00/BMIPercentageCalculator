package com.example.bmipercentagecalculator.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bmipercentagecalculator.repository.BmiCalculateResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor() : ViewModel() {

    var resultData: BmiCalculateResult? = null

    fun setResults(bmi: Double, bfp: Double, bmiCategory: String,  bfpCategory: String) {
        this.resultData = BmiCalculateResult(bmi, bfp, bmiCategory, bfpCategory)
    }
}