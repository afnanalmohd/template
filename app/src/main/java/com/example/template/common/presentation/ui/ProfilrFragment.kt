package com.example.template.common.presentation.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.template.common.presentation.viewmodel.ProfileViewModel
import com.example.template.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        profileViewModel.getUserProfile("2")
    }

    private fun observeViewModel() {
        profileViewModel.userResponse.observe(viewLifecycleOwner) { userData ->
            binding.tvName.text = userData.first_name
            binding.tvEmail.text = userData.email
//            Glide.with(this)
//                .load(userData.profileImageUrl)
//                .into(binding.ivProfileImage)
        }

        profileViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}
