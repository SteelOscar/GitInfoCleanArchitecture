package ru.steeloscar.gitinfocleanarchitecture

import junit.framework.Assert.assertEquals
import org.junit.Test
import ru.steeloscar.gitinfocleanarchitecture.commons.formatDate
import ru.steeloscar.gitinfocleanarchitecture.commons.formatSha

class UtilTest {
    @Test
    fun testFormatDate(){
        val date = formatDate("1234-56-78T90:12:34Z")
        assertEquals("78.56.1234", date)
    }

    @Test
    fun testFormatSha() {
        val sha = formatSha("123456789qwerty123456789qwerty1234567890")
        assertEquals("1234567", sha)
    }
}