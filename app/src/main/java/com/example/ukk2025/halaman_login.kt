import com.example.ukk2025.R
import com.example.ukk2025.databinding.FragmentHalamanLoginBinding
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth


class halaman_login : Fragment() {

    private lateinit var auth: FirebaseAuth
    private val binding get() = _binding!!
    private var _binding : FragmentHalamanLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHalamanLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        //navigasi to Register
        binding.tvToRegister.setOnClickListener{
            findNavController().navigate(R.id.action_halaman_login_to_halaman_register)
        }

        binding.masuk.setOnClickListener{
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (email.isEmpty()) {
                binding.email.error = "Email Harus Diisi"
                binding.email.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.email.error = "Email Tidak Valid"
                binding.email.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.password.error = "Password Harus Diisi"
                binding.password.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.password.error = "Minimal 6 Karakter"
                binding.password.requestFocus()
                return@setOnClickListener
            }

            loginWithFirebase(email, password)
        }
    }
    private fun loginWithFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(requireActivity()) {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Selamat Datang $email", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(R.id.action_halaman_login_to_halaman_home)
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