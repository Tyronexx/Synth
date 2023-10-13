package com.example.synth.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.synth.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {

    //    convert source object to primitive datatype
    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }

    //    convert primitive data type to source object
    @TypeConverter
    fun stringToSource(source: String): Source {
//        take the string and split it by the comma. Create a  Source from result
        return source.split(",").let { sourceArray ->
            Source(id = sourceArray[0], name = sourceArray[2])
        }
    }

}