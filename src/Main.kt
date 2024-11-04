fun main(){
    do {
        println("1. Jugar")
        println("2. Ver traza de ultimo intento")
        println("3. Salir")
        print("Opcion: ")
        var opcion = readln().toInt()
        when(opcion){
            1 -> jugar()
            2 -> historial()
            3 -> println("Gracias por jugar")
        }
    } while (opcion!=3)

}

fun jugar(){
    println("Jugando")
}

fun historial(){
    println("Historial")
}

