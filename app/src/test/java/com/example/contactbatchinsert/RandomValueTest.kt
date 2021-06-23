package com.example.contactbatchinsert

import org.junit.Test

import org.junit.Assert.*

class RandomValueTest {

    @Test
    fun getName() {
        val name = RandomValue.getName(2,5)
        println("Name: $name")
    }

    @Test
    fun getTel() {
        val tel = RandomValue.getTel()
        println("Tel: $tel")
    }

    @Test
    fun getUAETel() {
        val tel = RandomValue.getUAETel()
        println("UAE Tel: $tel")
    }
}