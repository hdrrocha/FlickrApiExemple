package com.example.flickraiexemple.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photos")
data class Photo(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") val id: String,
    @ColumnInfo(name = "owner")
    @SerializedName("owner") val owner: String,
    @ColumnInfo(name = "secret")
    @SerializedName("secret") val secret: String,
    @ColumnInfo(name = "server")
    @SerializedName("server") val server: Int,
    @ColumnInfo(name = "farm")
    @SerializedName("farm") val farm: Int,
    @ColumnInfo(name = "title")
    @SerializedName("title") val title: String,
    @ColumnInfo(name = "ispublic")
    @SerializedName("ispublic") val ispublic: Int,
    @ColumnInfo(name = "isfriend")
    @SerializedName("isfriend") val isfriend: Int,
    @ColumnInfo(name = "isfamily")
    @SerializedName("isfamily") val isfamily: Int,
)
