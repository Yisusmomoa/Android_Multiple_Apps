package com.Brandon.androidmaster.kotlin

fun main(){
    var name:String?= "Brandon"
    //print(name!![0])
    print(name?.get(0) ?: "Es nulo")
}