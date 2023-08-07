package com.Brandon.androidmaster.kotlin

fun main(){
    val result= ifBasico()
    println(result)
}

fun ifBasico():Boolean{
    val name="Brandon"
    if (name.toLowerCase()=="brandon"){
        return true
    }
    return false
}

fun whenBasico(){

}