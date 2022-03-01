package com.example.flickraiexemple.data.dao

import androidx.room.*
import com.example.flickraiexemple.data.model.Photo

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Photo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(note: List<Photo>)

    @Query("SELECT * FROM photos")
    fun photos(): List<Photo>
}