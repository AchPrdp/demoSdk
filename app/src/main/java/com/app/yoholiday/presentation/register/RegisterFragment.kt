package com.app.yoholiday.presentation.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.yoholiday.R
import com.app.yoholiday.common.BaseFragment
import com.app.yoholiday.common.EventType
import com.app.yoholiday.databinding.FragmentRegisterBinding
import com.app.yoholiday.vm.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val sessionViewModel by viewModels<SessionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.localToolbar.apply {
            titleSmall.text = getString(R.string.register_fragment)

            backButton.setOnClickListener {
                navController.popBackStack()
            }
        }

        storeLogEvent()

        binding.btnRegister.apply {
            setOnClickListener {
                navController.navigate(R.id.registerSuccessFragment)
            }
        }
    }

    private fun storeLogEvent() {
        sessionViewModel.storeNewEvents(
            eventKey = "RegisterFragment",
            eventValue = EventType.PageVisit.name
        )
    }
}