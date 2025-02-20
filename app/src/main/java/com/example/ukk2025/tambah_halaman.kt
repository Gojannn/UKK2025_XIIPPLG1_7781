package com.example.ukk2025

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ukk2025.databinding.FragmentTambahHalamanBinding
import com.google.firebase.firestore.FirebaseFirestore

class tambah_halaman : Fragment() {

    private var _binding: FragmentTambahHalamanBinding? = null
    private val binding get() = _binding!!

    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTambahHalamanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firestore = FirebaseFirestore.getInstance()

        binding.addButton.setOnClickListener {
            val title = binding.titleEditText.text.toString().trim()
            val description = binding.descEditText.text.toString().trim()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                addTask(title, description)
            } else {
                Toast.makeText(requireContext(), "Harap isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addTask(title: String, description: String) {
        val taskRef = firestore.collection("tasks").document()
        val task = Task(
            id = taskRef.id,  // Set ID otomatis dari Firestore
            title = title,
            description = description
        )

        taskRef.set(task)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Tugas Ditambahkan!", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Gagal: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Hindari memory leak
    }
}
