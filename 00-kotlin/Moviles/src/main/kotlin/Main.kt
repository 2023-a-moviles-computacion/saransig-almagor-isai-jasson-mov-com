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
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    //ARREGLOS

    //Tipos de Arreglos
    //Estaticos
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)

    //Dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9)
    println(arregloDinamico)
    arregloDinamico.add(10)
    arregloDinamico.add(11)
    println(arregloDinamico)

    //OPERADORES -> Sirven para arreglos estaticos y dinamicos
    //For each -> Unit
    //Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach{valorActual: Int ->
            println("Valor Actual: ${valorActual}" )

        }
    arregloDinamico.forEach{println(it)} //Significa el elemento actual

    arregloEstatico
        .forEachIndexed{indice: Int, valorActual: Int ->
            println("Valor: ${valorActual}  Indice: ${indice}")
        }
    println(respuestaForEach)

    //MAP -> Muta el arreglo
    // 1. Enviamos el nuevo valor de la iteracion
    // 2. Nos devuelve un NUEVO ARREGLO con los valores modificados
    val respuestaMap: List<Double> = arregloDinamico
        .map {valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map { it + 15 }

    //Filter -> Filtrar el Arreglo
    // 1. Devolver una expresion (TRUE o FALSE)
    // 2. Nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayorACinco: Boolean = valorActual > 5 //Condicion
            return@filter mayorACinco
        }

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }

    println(respuestaFilter)
    println(respuestaFilterDos)


    // ANY ALL
    // OR -> ANY (Se cumple alguno)
    // AND -> ALL (Se cumplen todos)

    val respuestaAny: Boolean = arregloDinamico
        .any{valorActual: Int ->
            return@any (valorActual>5)
        }
    println(respuestaAny) //true

    val respuestaAll: Boolean = arregloDinamico
        .all{valorActual: Int ->
            return@all (valorActual>5)
        }
    println(respuestaAll) //false

    //REDUCE -> Valor Acumulado
    //Valor acumulado = 0  <- inicializa en 0
    // [1,2,3,4,5] -> Sumeme todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 =  0 + 1 = 1
    // valorIteracion2 = valorIteracion1 + 2 =  1 + 2 = 3
    // valorIteracion3 = valorIteracion2 + 3 =  3 + 3 = 6
    // valorIteracion4 = valorIteracion3 + 4 =  6 + 4 = 10
    // valorIteracion5 = valorIteracion4 + 5 =  10 + 5 = 15

    val respuestaReduce: Int = arregloDinamico
        .reduce{
            acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual)
        }

    println(respuestaReduce)
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

    class  Suma( //Constructor Primario Suma
        uno: Int,  //Parametro
        dos: Int  //Parametro

    ) : Numeros(uno, dos){ // <- constructor del padre
        init { //bloque constructor primario
            this.numeroUno; numeroUno;
            this.numeroDos; numeroDos;
        }

    }
    constructor( //Segundo Constructor
        uno: Int?, //parametros
        dos: Int //parametros
    ): this ( //llamado al constructor Primario
        if (uno == null) 0 else uno,
        dos
    ){ //si necesitamos bloque de codigo lo usamos
        numeroUno;
    }
    constructor( // tercer constructor
        uno: Int,  //parametros
        dos: Int?   // parametros
    ): this ( //llamada al constructor primario
        uno,
        if (dos == null) 0 else dos

    ) //si no necesitamos bloque de codigo omitimos '{}'

    constructor( // cuarto constructor
        uno: Int?,  //parametros
        dos: Int?   // parametros
    ): this ( //llamada al constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos

    ) //si no necesitamos bloque de codigo omitimos '{}'
    public fun sumar(): Int{
        val total = numeroUno + numeroDos;
        agregarHistorial(total)
        return total
    }

    companion object{
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int
        {
            return num*num;
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
    }


}




