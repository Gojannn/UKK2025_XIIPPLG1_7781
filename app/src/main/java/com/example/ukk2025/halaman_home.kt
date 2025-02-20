package com.example.ukk2025

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ukk2025.databinding.FragmentHalamanHomeBinding
import com.google.firebase.firestore.FirebaseFirestore

class halaman_home : Fragment() {

    private var _binding: FragmentHalamanHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var firestore: FirebaseFirestore
    private lateinit var taskAdapter: TaskAdapter
    private val taskList = mutableListOf<Task>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHalamanHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tambah.setOnClickListener {
            findNavController().navigate(R.id.action_halaman_home_to_tambah_halaman)
        }

        firestore = FirebaseFirestore.getInstance()

        // Setup RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        taskAdapter = TaskAdapter(taskList, { task -> deleteTask(task.id) })
        binding.recyclerView.adapter = taskAdapter

        loadTasks()
    }

    // Fungsi untuk mengambil semua tugas dari Firestore
    private fun loadTasks() {
        firestore.collection("tasks").get()
            .addOnSuccessListener { documents ->
                taskList.clear()
                for (document in documents) {
                    val completedValue = document.get("completed")
                    val completedBoolean = when (completedValue) {
                        is Boolean -> completedValue
                        is String -> completedValue.toBoolean()
                        else -> false
                    }

                    val task = Task(
                        id = document.getString("id") ?: "",
                        title = document.getString("title") ?: "",
                        description = document.getString("description"),
                        dueDate = document.getLong("dueDate"),
                        completed = completedBoolean,
                        categoryId = document.getLong("categoryId")?.toInt() ?: 0
                    )

                    taskList.add(task)
                }
                taskAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Gagal memuat data: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    // Fungsi untuk menghapus tugas berdasarkan ID
    private fun deleteTask(taskId: String) {
        firestore.collection("tasks").document(taskId)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Tugas Dihapus!", Toast.LENGTH_SHORT).show()
                loadTasks()
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Gagal hapus: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Hindari memory leak
    }
}
