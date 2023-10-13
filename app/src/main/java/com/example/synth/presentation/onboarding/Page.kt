package com.example.synth.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.synth.R


// TODO: Refactor

data class Page(
    val title: String,
    val description:String,
    @DrawableRes val image: Int
)


//onboarding pages
val pages = listOf(
    Page(
        title = "Stay Informed and Empowered",
        description = "Our news app delivers the latest, most reliable information right to your fingertips, helping you make informed decisions and stay ahead of the curve.",
        image = R.drawable.a_blockchain_ba_0
    ),
    Page(
        title = "Tailored News Just for You",
        description = "Customize your news feed to receive the stories that matter most to you, ensuring a personalized and engaging news experience every time.",
        image = R.drawable.a_picture_of_th_0
    ),
    Page(
        title = "Effortless and Ad-Free",
        description = " Enjoy a seamless news browsing experience without intrusive ads, pop-ups, or subscriptions. Dive into the news without distraction.",
        image = R.drawable.the_seven_wonde_1
    )
)