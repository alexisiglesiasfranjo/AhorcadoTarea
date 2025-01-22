import DibujoAhorcado
import ReproductorMidi
import java.util.Scanner

fun main() {

    val scanner = Scanner(System.`in`)
    val palabras = listOf("platano","manzana","pera","piña","kiwi","sandia","melon","fresa",
        "papaya","limon","lima")
    val intentoMax = 7//intentos maximos
    var intentoActual = 1//intento actual

    val posicionRandom = (0..palabras.lastIndex).random()
    val palabraRandom = palabras[posicionRandom] //palabra seleccionada aleatoriamente

    var palabraOculta = ""

    for (char in palabraRandom){
        palabraOculta += '*'
    }//palabra oculta con *
    val rm = ReproductorMidi("pugnodollari.mid")
    println("Cargando juego...")
    Thread.sleep(20000)

    while (intentoActual <= intentoMax){

        println("Intento $intentoActual :")
        println(palabraOculta)

        val entrada = scanner.nextLine()//entrada del usuario
        if (entrada == palabraRandom){
            println("Has ganado!")
            break
        }
        val letra = entrada.toCharArray()[0]
        println("la letra es la $letra")

        if (letra in palabraRandom){
            palabraOculta = actualizarPalabra(palabraOculta, palabraRandom, letra)
        }else{
            println("la letra no es correcta")
            DibujoAhorcado.dibujar(intentoActual)
            Thread.sleep(10000)
        }

        if (palabraOculta==palabraRandom){
            println("Has ganado!")
            break
            }else if (intentoActual == intentoMax){
                println("Has perdido, la palabra es $palabraRandom")
            }
        intentoActual++
    }
    rm.cerrar()
}

fun actualizarPalabra(palabraActual : String, palabraRandom : String, char : Char): String{

    var sb = StringBuilder(palabraActual)
    for (i in palabraRandom.indices){
        if (palabraRandom[i] == char){
            sb.setCharAt(i,char)
            }
    }
    return sb.toString()
}
