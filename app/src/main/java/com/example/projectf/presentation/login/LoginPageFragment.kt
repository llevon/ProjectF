package com.example.projectf.presentation.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.projectf.data.Client
import com.example.projectf.data.GithubApi
import com.example.projectf.R
import com.example.projectf.databinding.FragmentLoginPageBinding
import com.example.projectf.domain.models.GithubUser
import com.example.projectf.presentation.main.SharedPreferencesManager
import com.example.projectf.presentation.main.SharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPageFragment : Fragment() {
    private var _binding: FragmentLoginPageBinding? = null
    private val binding: FragmentLoginPageBinding
        get() = _binding ?: throw Exception("fragment binding error")
    private val sharedViewModel:SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
    }

    private fun listeners() {
        with(binding){
            buttonLogin.setOnClickListener{
                val username = binding.etUsername.text.toString().trim()
                val token = binding.etToken.text.toString().trim()
                Log.d("is this wokring??????????", username )
                if (username.isNotEmpty() && token.isNotEmpty()) {
                    sharedViewModel.saveUsername(username)
                    signInWithGitHub(token)
                } else {
                    Toast.makeText(requireContext(), "Please enter your username and token", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun signInWithGitHub(token: String) {
        val githubApi = Client.instance.create(GithubApi::class.java)
        val call = githubApi.getUser("token $token")
        call.enqueue(
            object : Callback<GithubUser> {
                override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        Toast.makeText(
                            requireContext(),
                            "Welcome ${user?.login}",
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.action_loginPageFragment_to_homeFragment)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Authentication failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                    Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}