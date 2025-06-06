package com.example.bmipercentagecalculator.view
/*
Nama : M Taufik Iqbal
Kelas : P.Andro4
NIM : 10122336
Tanggal Pengerjaan Terkahir : 4-6-2025
 */
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmipercentagecalculator.R
import com.example.bmipercentagecalculator.databinding.ActivityMainBinding
import com.example.bmipercentagecalculator.viewmodel.BmiViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: BmiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        binding.btnHitung.setOnClickListener {
            val beratBadan = binding.etBeratBadan.text.toString()
            val tinggiBadan = binding.etTinggiBadan.text.toString()
            val usia = binding.etUsia.text.toString()
            val isMale = binding.rbLakiLaki.isChecked

            viewModel.calculateBmiAndBfp(beratBadan, tinggiBadan, usia, isMale)
        }
    }

    private fun observeViewModel() {
        viewModel.calculationResult.observe(this) { result ->
            if (result != null) {
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra("bmi", result.bmi)
                    putExtra("bmiCategory", result.bmiCategory)
                    putExtra("bfp", result.bfp)
                    putExtra("bfpCategory", result.bfpCategory)
                }
                startActivity(intent)
            }
        }

        viewModel.errorMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.clearErrorMessage()
            }
        }
    }
}