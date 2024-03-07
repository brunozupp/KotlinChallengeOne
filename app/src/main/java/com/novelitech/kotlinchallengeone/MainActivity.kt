package com.novelitech.kotlinchallengeone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.novelitech.kotlinchallengeone.pages.home.HomePage
import com.novelitech.kotlinchallengeone.pages.home.HomeViewModel
import com.novelitech.kotlinchallengeone.ui.theme.KotlinChallengeOneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            KotlinChallengeOneTheme {
                HomePage()
            }
        }
    }
}
