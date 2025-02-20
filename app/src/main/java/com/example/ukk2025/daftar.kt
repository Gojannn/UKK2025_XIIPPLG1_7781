import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.ukk2025.R
import com.example.ukk2025.databinding.FragmentDaftarBinding

import com.google.firebase.auth.FirebaseAuth

class daftar : Fragment() {

    private var _binding : FragmentDaftarBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment return inflater.inflate(R.layout.fragment_halaman_register, container, false)
        _binding = FragmentDaftarBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        //kembali ke halaman login setelah regist
        binding.daftar.setOnClickListener{
            findNavController().navigate(R.id.action_daftar2_to_signin)

        }
        binding.daftar.setOnClickListener{
            val name = binding.regNama.text.toString()
            val email = binding.regEmail.text.toString()
            val password = binding.regPassword.text.toString()

            if (name.isEmpty()) {
                binding.regNama.error = "Nama Harus Diisi"
                binding.regNama.requestFocus()
                return@setOnClickListener
            }

//            if (!Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
//                binding.regNama.error = "Nama Tidak Valid"
//                binding.regNama.requestFocus()
//                return@setOnClickListener
//            }

            if (email.isEmpty()) {
                binding.regEmail.error = "Email Harus Diisi"
                binding.regEmail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.regEmail.error = "Email Tidak Valid"
                binding.regEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.regPassword.error = "Password Harus Diisi"
                binding.regPassword.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 6 ) {
                binding.regPassword.error ="Buat Minimal 6 Karakter"
                binding.regPassword.requestFocus()
                return@setOnClickListener
            }

            //fungsi progressbar / loading
            binding.Loading.visibility = View.VISIBLE

            Handler(Looper.getMainLooper()).postDelayed({
                binding.Loading.visibility = View.GONE
            },3000)

            registerWithFirebase(email, password)
        }
    }

    private fun registerWithFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Register Berhasil", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_daftar2_to_signin)
                }else{
                    Toast.makeText(requireContext(), "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}