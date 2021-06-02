package ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomHourly (
        @PrimaryKey var dt: Int?,
        var temp: Double?,
        var icon: String?
        )


