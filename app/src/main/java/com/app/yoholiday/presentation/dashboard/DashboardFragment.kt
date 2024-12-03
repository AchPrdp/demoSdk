package com.app.yoholiday.presentation.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.yoholiday.R
import com.app.yoholiday.common.BaseFragment
import com.app.yoholiday.common.EventType
import com.app.yoholiday.databinding.FragmentDashboardBinding
import com.app.yoholiday.vm.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment :
    BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    private val sessionViewModel by viewModels<SessionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storeLogEvent()
        val navController = findNavController()

        binding.localToolbar.apply {
            titleSmall.text = getString(R.string.dashboard_fragment)

            backButton.setOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }

        binding.btnNavProfile.apply {
            setOnClickListener {
                navController.navigate(R.id.profileFragment)
            }
        }

        binding.btnNAvNotification.apply {
            setOnClickListener {
                navController.navigate(R.id.notificationFragment)
            }
        }
    }

    private fun storeLogEvent() {
        sessionViewModel.storeNewEvents(
            eventKey = "DashboardFragment",
            eventValue = EventType.PageVisit.name
        )
    }
}

