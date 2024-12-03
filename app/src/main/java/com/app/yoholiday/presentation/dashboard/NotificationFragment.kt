package com.app.yoholiday.presentation.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.yoholiday.R
import com.app.yoholiday.common.BaseFragment
import com.app.yoholiday.common.EventType
import com.app.yoholiday.databinding.FragmentNotificationBinding
import com.app.yoholiday.vm.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment :
    BaseFragment<FragmentNotificationBinding>(FragmentNotificationBinding::inflate) {

    private val sessionViewModel by viewModels<SessionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storeLogEvent()
        val navController = findNavController()

        binding.localToolbar.apply {
            titleSmall.text = getString(R.string.notification_fragment)

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
            eventKey = "NotificationFragment",
            eventValue = EventType.PageVisit.name
        )
    }

    private fun stopSession() {
        sessionViewModel.endSession()
//        viewLifecycleOwner.lifecycleScope.launch {
//            MySdKObject.endSession()
//        }
    }

}