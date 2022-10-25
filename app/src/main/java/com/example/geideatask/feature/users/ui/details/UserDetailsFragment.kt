package com.example.geideatask.feature.users.ui.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.RECEIVER_EXPORTED
import androidx.core.content.ContextCompat.registerReceiver
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.geideatask.R
import com.example.geideatask.databinding.FragmentUserDetailsBinding
import com.example.geideatask.feature.users.data.database.User
import com.example.geideatask.feature.users.data.models.shared.State
import com.example.geideatask.feature.users.ui.service.CountDownService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    private val userDetailsViewModel: UserDetailsViewModel by viewModels()
    private val args: UserDetailsFragmentArgs by navArgs()

    private lateinit var serviceIntent: Intent
    private var time = 6.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userDetailsViewModel.getUserDetails(args.userId)
        setupObservers()
        setupTimerService()
    }

    private fun setupTimerService() {
        serviceIntent = Intent(requireContext(), CountDownService::class.java)
        registerReceiver(
            requireContext(),
            updateTime,
            IntentFilter(CountDownService.TIMER_UPDATED),
            RECEIVER_EXPORTED
        )
        startTimer()
    }

    private fun setupObservers() {
        userDetailsViewModel.userLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    binding.pbUserDetails.visibility = View.VISIBLE
                }
                is State.Success -> {
                    binding.pbUserDetails.visibility = View.GONE
                    setupUserData(state.data)
                }
                is State.Error -> {
                    binding.pbUserDetails.visibility = View.GONE
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupUserData(user: User) {
        with(binding) {
            tvUserId.text = user.id.toString()
            tvUserName.text =
                getString(R.string.user_name_place_holder, user.first_name, user.last_name)
            tvUserEmail.text = user.email
            Glide.with(requireContext()).load(user.avatar).into(ivUserAvatar)
        }
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(CountDownService.TIME_EXTRA, 0.0)
            binding.tvTimer.text = time.toString()
            if (time == 0.0)
                findNavController().popBackStack()
        }
    }

    private fun startTimer() {
        serviceIntent.putExtra(CountDownService.TIME_EXTRA, time)
        requireActivity().startService(serviceIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().stopService(serviceIntent)
        _binding = null
    }
}