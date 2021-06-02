package ru.geekbrains.myweatherappmvpkotlin

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.geekbrains.myweatherappmvpkotlin.ui.format.formatUnixUtcDdMmmm
import ru.geekbrains.myweatherappmvpkotlin.ui.format.formatUnixUtcHhMm

class FormatDataTest {
    @Test
    fun formatUnixUtcHhMm_ReturnsTrue() {
        assertEquals(formatUnixUtcHhMm(1622653963), "20:12")
    }

    @Test
    fun formatUnixUtcDdMmmm_ReturnsTrue() {
        assertEquals(formatUnixUtcDdMmmm(1622653963), "02 июня")
    }
}