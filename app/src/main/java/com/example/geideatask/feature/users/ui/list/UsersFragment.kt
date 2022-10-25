package com.example.geideatask.feature.users.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.geideatask.databinding.FragmentUsersBinding
import com.example.geideatask.feature.users.data.models.shared.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val usersListAdapter by lazy {
        UsersListAdapter()
    }

    private val usersViewModel: UsersViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // setupUsersRecyclerView()
        setupObservers()
    }

    private fun setupUsersRecyclerView() {
        with(binding.rvUsers) {
            adapter = usersListAdapter
        }
    }

    private fun setupObservers() {
        usersViewModel.usersLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    binding.pbUsers.visibility = View.VISIBLE
                }
                is State.Success -> {
                    binding.pbUsers.visibility = View.GONE
                    usersListAdapter.submitList(state.data)
                }
                is State.Error -> {
                    binding.pbUsers.visibility = View.GONE
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
            }

        }

    }
}