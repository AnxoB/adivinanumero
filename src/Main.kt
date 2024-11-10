import java.io.File
const val BG_RED = "\u001B[41m"
const val BG_GREEN = "\u001B[42m"
const val RESET = "\u001B[0m"
const val BG_YELLOW = "\u001B[43m"

fun main(){
    do {
        println("1. Jugar")
        println("2. Ver traza de ultimo intento")
        println("3. Salir")
        print("Opcion: ")
        val opcion = readln().toInt()
        when(opcion){
            1 -> jugar()
            2 -> historial()
            3 -> println("Gracias por jugar")
        }
    } while (opcion != 3)
}

fun jugar() {
    val numeroAleatorio = numeroAleatorio()
    var historial = "Número secreto: $numeroAleatorio\n"
    var acierta = false

    for (j in 0 until 10) {
        var numero: String
        do {
            print("Teclea un número de 4 cifras sin números repetidos: ")
            numero = readln()
            if (numero.length != 4 || !esUnico(numero)) {
                println("Error: El número debe tener 4 cifras y no contener dígitos repetidos.")
            }
        } while (numero.length != 4 || !esUnico(numero))

        val (acierto, coincidencia, fallo) = compararNumeros(numeroAleatorio, numero)
        historial += "Intento ${j + 1}: $numero, Aciertos: $acierto, Coincidencias: $coincidencia, Fallos: $fallo\n"

        if (acierto == 4) {
            println("Felicidades, has acertado el número!")
            acierta = true
            break
        }
    }

    if (!acierta) {
        println("Lo siento, no has logrado acertar el número en 10 intentos. El número era: $numeroAleatorio")
    }

    guardarHistorial(historial)
}

fun historial() {
    val file = File("mifichero.txt")
    if (file.exists()) {
        println("Historial de la última jugada:")
        println(file.readText())
    } else {
        println("No hay historial disponible.")
    }
}

fun guardarHistorial(historial: String) {
    File("mifichero.txt").writeText(historial)
}

fun numeroAleatorio(): String {
    val digitos = (0..6).toMutableList()
    digitos.shuffle()
    return digitos.take(4).joinToString("")
}

fun compararNumeros(numeroAleatorio: String, numero: String): Triple<Int, Int, Int> {
    var acierto = 0
    var coincidencia = 0
    var fallo = 0

    for (i in 0 until 4) {
        when {
            numero[i] == numeroAleatorio[i] -> acierto++
            numeroAleatorio.contains(numero[i]) -> coincidencia++
            else -> fallo++
        }
    }

    println(numero + "    ${BG_GREEN}  $acierto  ${BG_YELLOW}  $coincidencia  ${BG_RED}  $fallo  ${RESET}")
    return Triple(acierto, coincidencia, fallo)
}

fun esUnico(numero: String): Boolean {
    return numero.toSet().size == numero.length
}
