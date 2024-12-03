package com.app.yoholiday.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.mylibrary.repo.EventRepository
import com.app.yoholiday.R
import com.app.yoholiday.common.BaseFragment
import com.app.yoholiday.common.EventType
import com.app.yoholiday.databinding.FragmentLoginBinding
import com.app.yoholiday.presentation.dashboard.DashboardActivity
import com.app.yoholiday.vm.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val sessionViewModel by viewModels<SessionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storeLogEvent()
        val navController = findNavController()

        binding.localToolbar.apply {
            titleSmall.text = getString(R.string.login_fragment)

            backButton.setOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }

        binding.btnLogin.apply {
            setOnClickListener {
                startActivity(Intent(this.context, DashboardActivity::class.java))
            }
        }

        binding.btnRegister.apply {
            setOnClickListener {
                navController.navigate(R.id.registerFragment)
            }
        }

        binding.btnSessionStart.apply {
            setOnClickListener {
                startSession()
            }
        }

    }

    private fun startSession() {
        sessionViewModel.startSession()
    }

    private fun storeLogEvent() {
        sessionViewModel.storeNewEvents(
            eventKey = "LoginFragment",
            eventValue = EventType.PageVisit.name
        )
    }
}