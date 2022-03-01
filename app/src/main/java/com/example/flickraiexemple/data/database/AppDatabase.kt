package com.example.flickraiexemple.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.flickraiexemple.data.dao.PhotoDao
import com.example.flickraiexemple.data.model.Photo

@Database(
    entities = [Photo::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao

//    companion object {
//        fun getInstance(context: Context): AppDatabase =
//            Room.databaseBuilder(context, AppDatabase::class.java, "photosdb")
//                .addCallback(object : RoomDatabase.Callback() {
//                    override fun onCreate(db: SupportSQLiteDatabase) {
//                        fillInDb(context)
//                    }
//                }).build()
//
//        /**
//         * fill database with list of notes so we can test pagination
//         */
//        private fun fillInDb(context: Context) {
//            // inserts in Room are executed on the current thread, so we insert in the background
//            ioThread {
//                AppDatabase.getInstance(context).photoDao().insertAll(
//                    getData().map { NoteEntity(id = 0, noteText = it) }
//                )
//            }
//        }
//    }
}