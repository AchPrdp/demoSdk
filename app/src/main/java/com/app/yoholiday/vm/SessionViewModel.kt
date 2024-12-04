package com.app.yoholiday.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mylibrary.MySdKObject
import com.app.mylibrary.util.NetworkResult
import kotlinx.coroutines.launch

//@HiltViewModel
class SessionViewModel : ViewModel() {

    /**
     * Function to Start Session
     */
    fun startSession() {
        viewModelScope.launch {
            try {
                MySdKObject.startSession()
//                MySdKObject.initEventRepo()
            } catch (ex: Exception) {
                println("Session Start Exception : ${ex.message}")
            }
        }
    }

    /**
     * Function to End Session
     */
    fun endSession() {
        viewModelScope.launch {
            try {
                MySdKObject.endSession()
            } catch (ex: Exception) {
                println("Session End Exception : ${ex.message}")
            }
        }
    }

    /**
     * Function to Store Event Logs
     */
    fun storeNewEvents(eventKey: String, eventValue: String) {
        updateEvents()
        viewModelScope.launch {
            try {
                MySdKObject.addEventLog(
                    key = eventKey,
                    value = eventValue,
                )
            } catch (ex: Exception) {
                println("Store Log Exception : ${ex.message}")
            }

        }
    }

    /**
     * Function to sync event logs to server
     */
    fun updateEvents() {
        viewModelScope.launch {
            when (val data = MySdKObject.syncEvents()) {
                is NetworkResult.Error -> {
                    println("Error occurred on Api call ${data.errorMessage}")
                }

                is NetworkResult.Success -> {
                    println("Api Success Response : ${data.data.toString()}")
                }
            }
        }
    }

    /**
     * Function to delete event logs locally
     */
    fun deleteEvents() {
        viewModelScope.launch {

        }
    }

}