package com.Brandon.androidmaster.kotlin

fun main(){
    println(getMonth(11))
    println(getTrimester(5))
    println(getSemester(8))
    result(true)
}

fun result(value:Any){
    when(value){
        is Int -> println(value+value)
        is String -> println(value)
        is Boolean -> if(value) println(value)
    }
}

fun getSemester(month: Int):String{
    return when(month){
        in 1..6-> "Primer semestre"
        in 7..12-> "Segundo semestre"
        else -> "No es un semestre valido"
    }
}

fun getTrimester(month:Int):String{
    val result=when(month){
        1, 2, 3->"Primer trimestre"
        4, 5, 6->"Segundo trimestre"
        7, 8, 9-> "Tercer trimestre"
        10, 11, 12-> "Cuarto trimestre"
        else ->  "No es un trimestre valido"
    }
    return result
}

fun getMonth(month:Int):String{
    when(month){
        1->return "Enero"
        2->return "Febrero"
        3->return "Marzo"
        4->return "Abril"
        5->return "Mayo"
        6->return "Junio"
        7->return "Julio"
        8->return "Agosto"
        9->return "Septiembre"
        10->return "Octubre"
        11->{
            print("vkasfljashbl ")
            1+2
            return "Noviembre"
        }
        12->return "Diciembre"
        else -> return "No es un mes valido"
    }
}
