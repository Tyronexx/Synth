package com.example.synth.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.synth.domain.model.Article


//abstract cause room will implement it for us

@Database(entities = [Article::class], version = 2,)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract val newsDao : NewsDao

}