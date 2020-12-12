package ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomCurrent (
        val temp: Double?,
        val description: String?,
        val icon: String?){
    @PrimaryKey val id: Int = 1
}