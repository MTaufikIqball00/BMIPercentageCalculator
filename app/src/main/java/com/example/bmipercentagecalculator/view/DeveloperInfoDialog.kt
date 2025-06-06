package com.example.bmipercentagecalculator.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bmipercentagecalculator.databinding.ActivityDeveloperInfoDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DeveloperInfoDialog : BottomSheetDialogFragment() {

    private var _binding: ActivityDeveloperInfoDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = ActivityDeveloperInfoDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}