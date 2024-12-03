package com.app.yoholiday

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.yoholiday.presentation.login.AuthActivity
import com.app.yoholiday.ui.theme.YoHolidayTheme
import com.app.yoholiday.vm.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val sessionViewModel by viewModels<SessionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YoHolidayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.LightGray)
                            .windowInsetsPadding(WindowInsets.systemBars)
                    ) {
                        val context = LocalContext.current
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Greeting(
                                name = "Yo Holiday",
                                modifier = Modifier.padding(innerPadding)
                            )

                            Image(
                                painterResource(R.drawable.ic_holiday),
                                modifier = Modifier
                                    .padding(all = 20.dp)
                                    .fillMaxWidth(),
                                contentScale = ContentScale.Fit,
                                contentDescription = "Holiday icons created by Freepik - Flaticon"
                            )

                            Button(
                                modifier = Modifier
                                    .padding(top = 32.dp, start = 20.dp, end = 20.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(10),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        0xFFF15657
                                    )
                                ),
                                onClick = {
                                    startActivity(Intent(context, AuthActivity::class.java))
                                }
                            ) {
                                Text("Navigate To Login")
                            }

                            Button(
                                modifier = Modifier
                                    .padding(top = 18.dp, start = 20.dp, end = 20.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(10),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        0xFFF15657
                                    )
                                ),
                                onClick = {
                                    startSession()
                                }
                            ) {
                                Text(getString(R.string.session_start))
                            }

                            Button(
                                modifier = Modifier
                                    .padding(top = 18.dp, start = 20.dp, end = 20.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(10),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        0xFFF15657
                                    )
                                ),
                                onClick = {
                                    endSession()
                                }
                            ) {
                                Text(getString(R.string.session_end))
                            }

                            Button(
                                modifier = Modifier
                                    .padding(top = 18.dp, start = 20.dp, end = 20.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(10),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        0xFFF15657
                                    )
                                ),
                                onClick = {
                                    storeLogEvent()
                                }
                            ) {
                                Text(getString(R.string.log_event))
                            }
                        }

                    }
                }
            }
        }
    }

    private fun startSession() {
        sessionViewModel.startSession()
    }

    private fun endSession() {
        sessionViewModel.endSession()
    }

    private fun storeLogEvent() {
        sessionViewModel.storeNewEvents(
            eventKey = "MainActivity",
            eventValue = "Button Click"
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Welcome, $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    YoHolidayTheme {
        Greeting("Android")
    }
}