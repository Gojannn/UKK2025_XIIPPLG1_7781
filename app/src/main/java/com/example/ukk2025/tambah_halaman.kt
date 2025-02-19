package com.example.ukk2025

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ukk2025.databinding.FragmentHalamanHomeBinding
import com.example.ukk2025.databinding.FragmentTambahHalamanBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class tambah_halaman : Fragment() {

    private lateinit var binding: FragmentTambahHalamanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTambahHalamanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Format tanggal: Hari, dd MMMM yyyy (Contoh: Senin, 19 Februari 2025)
        val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))
        val currentDate = dateFormat.format(Date())

        // Tampilkan tanggal di TextView
        binding.date.setOnClickListener {
            currentDate
        }

        binding.btnSave.setOnClickListener {
            findNavController().navigate(R.id.action_tambah_halaman_to_halaman_home)
        }
    }

}