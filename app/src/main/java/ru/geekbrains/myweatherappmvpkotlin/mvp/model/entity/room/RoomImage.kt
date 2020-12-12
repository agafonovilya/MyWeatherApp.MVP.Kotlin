package ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomImage(
        @PrimaryKey val url: String,
        val localPath: String
)