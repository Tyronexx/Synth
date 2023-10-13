package com.example.synth.domain.usecases.app_entry

import com.example.synth.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry (
//    pass interface not impl
    private val localUserManager: LocalUserManager
){

    operator fun invoke(): Flow<Boolean> {
//        save app entry
        return localUserManager.readAppEntry()
    }
}