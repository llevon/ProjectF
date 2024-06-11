package com.example.projectf.presentation.profile

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.projectf.R
import com.example.projectf.databinding.FragmentProfileBinding
import com.example.projectf.databinding.FragmentRepositoriesBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw Exception("fragment binding error")
    private val pickImageLauncher = registerForActivityResult (ActivityResultContracts.GetContent())
    {
            uri: Uri? ->
        uri?.let {
            binding.ivProfilepicture.setImageURI(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
    }

    private fun listeners() {
        binding.ivProfilepicture.setImageResource(R.drawable.ic_blank_profile)
        binding.ivProfilepicture.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}