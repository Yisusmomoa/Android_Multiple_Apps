package com.Brandon.androidmaster.kotlin

fun main(){
    val weekDays= arrayOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado", "Domingo")
    /*println(weekDays[0])
    println(weekDays[6])
    println(weekDays)
    println(weekDays.size)
    if(weekDays.size<8){
        weekDays.set(1, "12")
        println(weekDays[1])
    }
    else{
        println("No hay valores en el array")
    }*/
    //bucles
    for (position in weekDays.indices){
        println(weekDays[position])
    }
    println("***********************************")
    for((position, values) in weekDays.withIndex()){
        println("Posición $position, valor: $values")
    }
    println("***********************************")

    for(weekDay in weekDays){
        println(weekDay)
    }
}