package ru.geekbrains.myweatherappmvpkotlin.mvp.model.repo.location

import ru.geekbrains.myweatherappmvpkotlin.mvp.model.entity.location.Location

class LocationRepo {
    val locationArray = listOf(Location("Moscow","55.76","37.60"),
                                Location("Saint-Petersburg", "59.89", "30.26"),
                                Location("Glazov", "58.13", "52.65"))
}
