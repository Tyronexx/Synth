package com.example.synth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synth.domain.usecases.app_entry.AppEntryUseCases
import com.example.synth.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel(){

//    Determine start destination based on value of appentry key freom datastore preferences
//    if true, go to news navigatorScreen else go to OnBoardingScreens

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set


    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->

            if (shouldStartFromHomeScreen){
                startDestination = Route.NewsNavigation.route
            }else{
                //    else start from onBoardingScreen
                startDestination = Route.AppStartNavigation.route
            }
            delay(200)
            splashCondition = false    //determine when splash screen should exit composition

        }.launchIn(viewModelScope)     //collect the flow
    }
}