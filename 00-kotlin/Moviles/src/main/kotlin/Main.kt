import java.util.*

fun main(args: Array<String>) {

    println("Hello World!")



    // Try adding program arguments via Run/Debug configuration.

    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.

    //Tipos de variables



    //inmutables no se reasigna

    val inmutable: String = "Saransig";

    //inmutable = Saransig";

    //Mutables - se reasignan

    var mutable: String = "Isai";

    mutable  = "Jasson";



    // se prefiere el val antes que el var



    //duck typing

    val ejemploVariable = "Isai Saransig";
    val edadEjemplo: Int = 17;
    ejemploVariable.trim()
    //ejemploVariable = edadEjemplo;
    //variables primitivas
    val nombreProfesor: String = "Isai Saransig";
    val sueldo: Double = 1.2;
    val estadoCivil: Char = 'C';
    val mayorEdad: Boolean = true;
    //Clase Java
    val fechaNacimiento: Date = Date()

    //Switch

    val estadoCivilWhen = "C"

    when (estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        ("S") -> {
            println("Soltero")
        }else -> {
        println("No sabemos");
    }
    }
    val coqueteo = if(estadoCivilWhen == "S") "Si" else "No"

    calcularSueldo(10.00)
    calcularSueldo(10.00, 12.00, 20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00) //Parametros nombrados
    calcularSueldo(bonoEspecial = 20.00 , sueldo = 10.00, tasa = 14.00)
}



fun imprimirNombre(nombre: String): Unit{
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00,
    bonoEspecial: Double? = null,
)
: Double{
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) + bonoEspecial
    }
}


abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int,
        dos: Int
    ){ //Bloque de codigo del constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

abstract class Numeros( //Constructor primario
    //Ejemplo
    // uno: Int, (Parametro sin modificador de acceso
    //private var uno: Int, propiedad de la clase
    // var uno: Int, //Propiedad de la clase
    protected val numeroUno: Int,
    protected val numeroDos: Int
){
    // var cedula: String = "" --> public valor por defecto
    // private valorCalculado: Int = 0 --> private
    //BLOQUE DE CODIGO
    init {
        this.numeroUno; this.numeroDos;
        numeroUno; numeroDos;
        println("Inicializando")
    }
}