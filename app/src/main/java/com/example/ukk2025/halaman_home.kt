package com.example.ukk2025

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ukk2025.databinding.FragmentHalamanHomeBinding
import com.google.firebase.auth.FirebaseAuth

class halaman_home : Fragment() {

    private lateinit var binding: FragmentHalamanHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHalamanHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tambah.setOnClickListener {
            findNavController().navigate(R.id.action_halaman_home_to_tambah_halaman)
        }
    }
}