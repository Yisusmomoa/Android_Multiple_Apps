package com.Brandon.androidmaster.kotlin

//https://developer.android.com/codelabs/basic-android-kotlin-compose-kotlin-fundamentals-practice-problems?hl=es_419#2
fun main(){
    //exercise1()
    exercise2()
}

fun exercise1(){
    //Notificaciones móviles
    val morningNotification = 51
    val eveningNotification = 135

    printNotificationSummary(morningNotification)
    printNotificationSummary(eveningNotification)
}
fun printNotificationSummary(numberOfMessages:Int){
    if(numberOfMessages<99){
        println("You have $numberOfMessages notifications")
    }else{
        println("Your phone is blowing up! You have 99+ notifications.")
    }
}

fun exercise2(){
    //Precio de la entrada de cine
    val child = 5
    val adult = 28
    val senior = 61

    val isMonday = false

    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")
}

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    // Fill in the code.
   return when(age){
       in 1..12->15
       in 13..60->{
          if(isMonday) return 25
           return 30
       }
       in 61..100->20
       else->-1
   }
}