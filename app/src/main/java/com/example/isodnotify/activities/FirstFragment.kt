package com.example.isodnotify.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.isodnotify.R
import com.example.isodnotify.databinding.FragmentFirstBinding
import com.example.isodnotify.utils.LoginValidator
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.content.Context
import com.example.isodnotify.utils.IsodApiRetriver


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            val loginValidator = LoginValidator()
            val username = binding.usernameEditText.text.toString()
            val apiKey = binding.apikeyEditText.text.toString()

            println("Username: $username")
            println("API Key: $apiKey")

            IsodApiRetriver(username, apiKey).execute()


            if (loginValidator.validate(username, apiKey)) {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            } else {
                Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}