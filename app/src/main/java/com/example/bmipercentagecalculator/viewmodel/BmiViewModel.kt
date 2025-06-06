package com.example.bmipercentagecalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope   // UPDATE PACKAGE
import com.example.bmipercentagecalculator.repository.BmiCalculateResult
import com.example.bmipercentagecalculator.repository.BmiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BmiViewModel @Inject constructor(
    private val bmiRepository: BmiRepository // UPDATE NAMA KELAS REPOSITORY
) : ViewModel() {

    private val _calculationResult = MutableLiveData<BmiCalculateResult>()
    val calculationResult: LiveData<BmiCalculateResult> = _calculationResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun calculateBmiAndBfp(weightKg: String, heightCm: String, age: String, isMale: Boolean) {
        viewModelScope.launch {
            try {
                val weight = weightKg.toDoubleOrNull()
                val height = heightCm.toDoubleOrNull()
                val userAge = age.toIntOrNull()

                if (weight == null || height == null || userAge == null || weight <= 0 || height <= 0 || userAge <= 0) {
                    _errorMessage.value = "Mohon isi semua data dengan angka yang valid."
                    return@launch
                }

                val result = bmiRepository.calculateBmiAndBfp(weight, height, userAge, isMale) // Call new method
                _calculationResult.value = result
            } catch (e: Exception) {
                _errorMessage.value = "Terjadi kesalahan: ${e.message}"
            }
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}