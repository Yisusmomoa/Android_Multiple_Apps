package com.Brandon.androidmaster.kotlin

fun sumar(num1:Int, num2:Int): Int {
    return num1+num2
}
fun div(num1:Int, num2:Int=1): Int {
    return num1/num2
}


fun main(){
    //instanciar cuando creas una referencia de un valor no lo puedes modificar
    val result= sumar(1,2)
    val resultDiv= div(10)
    println("Resultado: "+result)
    println("Resultado div: "+resultDiv)
    //numericas
    val age:Int=30
    var age2: Int =30
    age2=20
    val example:Long=1212312312
    val floatExample:Float= 30.5F

    //Double (soportan hasta 14 decimales)
    val doubleExample:Double= 2312.1231231212123

    //variables alfanumericas
    val charExample:Char='s'

    //string
    val stringExample:String="qwh bvri2352 743y5273 56u.gfgdgg"

    //boleanos
    val booleanExample:Boolean=true
    val booleanExample2:Boolean=false
    println("Age: $age2")
    println(charExample)
    println(stringExample)

}