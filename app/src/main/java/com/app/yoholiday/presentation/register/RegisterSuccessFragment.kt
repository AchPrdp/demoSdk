package com.app.yoholiday.presentation.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.mylibrary.repo.SessionRepository
import com.app.yoholiday.R
import com.app.yoholiday.common.BaseFragment
import com.app.yoholiday.databinding.FragmentRegisterSuccessBinding
import com.app.yoholiday.vm.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterSuccessFragment :
    BaseFragment<FragmentRegisterSuccessBinding>(FragmentRegisterSuccessBinding::inflate) {

    private val sessionViewModel by viewModels<SessionViewModel>()

    @Inject
    lateinit var sessionRepository: SessionRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storeLogEvent()
        val navController = findNavController()

        binding.localToolbar.apply {
            titleSmall.text = getString(R.string.register_success_fragment)

            backButton.setOnClickListener {
                navController.popBackStack()
            }
        }

        binding.btnRegisterSuccess.apply {
            setOnClickListener {
                navController.popBackStack(R.id.loginFragment, inclusive = false)
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
            eventKey = "RegisterSuccessFragment",
            eventValue = "Page Visit"
        )
    }

    private fun stopSession() {
        sessionViewModel.endSession()
    }
}