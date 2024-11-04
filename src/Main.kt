import kotlin.random.Random
const val BG_RED = "\u001B[41m"
const val BG_GREEN = "\u001B[42m"
const val RESET = "\u001B[0m"

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
    var numeroAleatorio = numeroAleatorio()
    for (j in 0 until 10){
        print("Teclea un numero de 4 cifras sin números repetidos: ")
        var numero= readln()
        compararNumeros(numeroAleatorio,numero)

    }

}

fun historial(){
    println("Historial")
}

fun numeroAleatorio(): String {
    val digitos = (0..6).toMutableList()
    digitos.shuffle()
    return digitos.take(4).joinToString("")
}

fun compararNumeros(numeroAleatorio: String, numero: String){
    var acierto = 0
    var fallo = 0
    for (i in 0 until 4) {
        if (numero[i] == numeroAleatorio[i]) {
            acierto++
            if (acierto == 4) {
                println("Felicidades, has acertado el número!")
                return
            }
        } else {
            fallo++
        }
    }
    println(numero + " " + numeroAleatorio +  "    ${BG_GREEN}  $acierto  ${BG_RED}  $fallo  ${RESET}")
}



