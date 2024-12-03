package com.app.yoholiday.presentation.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.yoholiday.R
import com.app.yoholiday.common.BaseFragment
import com.app.yoholiday.databinding.FragmentProfileBinding
import com.app.yoholiday.vm.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val sessionViewModel by viewModels<SessionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storeLogEvent()
        val navController = findNavController()

        binding.localToolbar.apply {
            titleSmall.text = getString(R.string.profile_fragment)

            backButton.setOnClickListener {
                navController.popBackStack()
            }
        }

        binding.btnSessionEnd.apply {
            setOnClickListener {
                stopSession()
            }
        }
    }

    private fun storeLogEvent() {
        sessionViewModel.storeNewEvents(
            eventKey = "ProfileFragment",
            eventValue = "Page Visit"
        )
    }

    private fun stopSession() {
        sessionViewModel.endSession()
    }

}