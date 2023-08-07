package com.Brandon.androidmaster.kotlin

fun main(){
   // inmutableList()
    mutableList()
}

fun mutableList(){
    var weekDays= mutableListOf<String>("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado", "Domingo")
    weekDays.add(0,"eeeeeeeee")
    println(weekDays)
    if(weekDays.isNotEmpty()){
        weekDays.forEach { println(it) }
    }
    weekDays.last()
}

fun inmutableList(){
    val readOnly=listOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado", "Domingo")
    println(readOnly)
    println(readOnly.first())
    println(readOnly.last())
    val example=readOnly.filter { it.contains("a") }
    println(example)
    readOnly.forEach { weekDay->
        println(weekDay)
    }
}