package com.tricakrawala.batikpedia.data.resource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataId

@Database(entities = [WisataId::class], version = 1, exportSchema = false)
abstract class WisataDatabase : RoomDatabase() {
    abstract fun wisataDao(): WisataDao
}