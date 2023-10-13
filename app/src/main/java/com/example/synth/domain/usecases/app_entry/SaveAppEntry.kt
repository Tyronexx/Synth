package com.example.synth.domain.usecases.app_entry

import com.example.synth.domain.manager.LocalUserManager

class SaveAppEntry (
//    pass interface not impl
    private val localUserManager: LocalUserManager
){

    suspend operator fun invoke(){
//        save app entry
        localUserManager.saveAppEntry()
    }
}