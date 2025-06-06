package com.example.bmipercentagecalculator.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmipercentagecalculator.R
import com.example.bmipercentagecalculator.databinding.ActivityResultBinding
import com.example.bmipercentagecalculator.viewmodel.ResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private val viewModel: ResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayResults()
        setupListeners()
    }

    private fun displayResults() {
        val bmi = intent.getDoubleExtra("bmi", 0.0)
        val bfp = intent.getDoubleExtra("bfp", 0.0)
        val bmiCategory = intent.getStringExtra("bmiCategory") ?: "N/A"
        val bfpCategory = intent.getStringExtra("bfpCategory") ?: "N/A"

        viewModel.setResults(bmi, bfp, bmiCategory, bfpCategory)

        binding.tvBmiResult.text = String.format("Nilai BMI Anda: %.2f", viewModel.resultData?.bmi)
        binding.tvBmiCategory.text = String.format("Kategori BMI: %s", viewModel.resultData?.bfpCategory)
        binding.tvBfpResult.text = String.format("Nilai BFP Anda: %.2f", viewModel.resultData?.bfp,)
    }

    private fun setupListeners() {
        binding.btnInfoPengembang.setOnClickListener {
            val dialog = DeveloperInfoDialog()
            dialog.show(supportFragmentManager, "DeveloperInfoDialog")
        }
    }
}