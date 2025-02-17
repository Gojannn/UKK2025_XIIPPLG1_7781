package com.example.ukk2025

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ukk2025.databinding.FragmentTampilanBinding

class tampilan : Fragment() {

    private lateinit var binding: FragmentTampilanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tampilan, container, false)

        // Panggil showLoading
        showLoading()

        return binding.root
    }

    private fun showLoading() {
        binding.progresbarr.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progresbarr.visibility = View.GONE
    }
}
