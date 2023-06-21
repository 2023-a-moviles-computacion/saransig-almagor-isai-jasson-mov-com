import java.io.*
import java.time.LocalDate
import java.util.*


// Main.kt
fun main(){
    var flag = true
    while (flag){
        println("Seleccione una Opción")
        println("0. Salir")
        println("1. Universidades")
        println("2. Facultades")
        var opcion = readln().toInt()
        when (opcion){
            (0) -> {
                flag = false
            }
            (1) -> {
                flag = true
                System.out.println("Has seleccionado Universidades");
                abrirMenuUniversidad()
            }
            (2) -> {
                flag = true
                System.out.println("Has seleccionado Facultades");
                abrirMenuFacultad()
            }
            else -> println("Opción no válida")
        }
    }
}

fun abrirMenuUniversidad(){
    var flag2 = true
    while(flag2){
        println("¿Qué desea hacer?")
        println("0. Salir")
        println("1. Crear una Universidad")
        println("2. Listar Universidades")
        println("3. Actualizar una Universidad")
        println("4. Eliminar una Universidad")
        var opcion = readln().toInt()
        when (opcion){
            (0) -> {
                flag2 = false
            }
            (1) -> {
                crearUniversidad()
            }
            (2) -> {
                leerUniversidades()
            }
            (3) -> {
                actualizarUniversidad()
            }
            (4) -> {
                eliminarUniversidad()
            }
            else -> println("Opción no válida")
        }
    }
}


fun abrirMenuFacultad(){
    var flag2 = true
    while(flag2){
        println("¿Qué desea hacer?")
        println("0. Salir")
        println("1. Crear una facultad")
        println("2. Listar facultades")
        println("3. Actualizar una facultad")
        println("4. Eliminar una facultad")
        var opcion = readln().toInt()
        when (opcion){
            (0) -> {
                flag2 = false
            }
            (1) -> {
                agregarFacultad()
            }
            (2) -> {
                leerFacultades()
                println("\n")
            }
            (3) -> {
                actualizarFacultad()
            }
            (4) -> {
                eliminarFacultad()
            }
            else -> println("Opción no válida")
        }
    }
}

fun crearUniversidad(){
    var miUniversidad = Universidad()
    var archivo: File?
    var fw: FileWriter? = null
    var pw: PrintWriter?
    // Obtener los datos del Album
    print("Escriba el id de la universidad: ")
    miUniversidad.id_u = readln().toInt()
    print("Escriba la fecha en la que se fundo la universidad (yyyy-mm-dd): ")
    miUniversidad.fechaCreacion = LocalDate.parse(readln())
    print("Escriba el nombre de la Universidad: ")
    miUniversidad.nombre = readln()
    print("Escriba V si la Universidad es Pública, caso contrario, escriba F: : ")
    miUniversidad.esPublica = readln() == "V"
    print("Escriba el promedio de notas de la universidad: ")
    miUniversidad.promedioNotas = readln().toFloat()
    print("Escriba el número de estudiantes de la universidad:")
    miUniversidad.numeroEstudiantes = readln().trim().toInt()
    miUniversidad.listaFacultades = agregarListaFacultades()
    try {
        archivo = File("universidades.txt")
        fw = FileWriter(archivo, true)
        pw = PrintWriter(fw)
        //
        pw.println(miUniversidad.obtenerAtributos())

    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            fw?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun leerUniversidades(){
    val listaUniversidad = mutableListOf<Universidad>()
    try {
        val file = File("universidades.txt")
        val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
        reader.lines().forEach{
            var miListaFacultades = mutableListOf<Facultad>()
            val tokens = StringTokenizer(it, ",")
            var dato: String = tokens.nextToken()
            val nuevaUniversidad = Universidad()
            nuevaUniversidad.id_u = dato.toInt()
            dato = tokens.nextToken()
            nuevaUniversidad.fechaCreacion = LocalDate.parse(dato)
            dato = tokens.nextToken()
            nuevaUniversidad.nombre = dato
            dato = tokens.nextToken()
            nuevaUniversidad.esPublica = dato.toBoolean()
            dato = tokens.nextToken()
            nuevaUniversidad.promedioNotas = dato.toFloat()
            dato = tokens.nextToken()
            nuevaUniversidad.numeroEstudiantes = dato.trim().toInt()
            // aquí se vuelve a realizar una búsqueda por tokens
            while(tokens.hasMoreTokens()){
                dato = tokens.nextToken()
                miListaFacultades.add(obtenerFacultadPorId(dato.toInt()))
            }
            nuevaUniversidad.listaFacultades = miListaFacultades
            listaUniversidad.add(nuevaUniversidad)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    listaUniversidad.forEach{
        println(it.obtenerAtributos())
    }
}

fun agregarListaFacultades(): MutableList<Facultad>{
    var listaFacultadesNuevas = mutableListOf<Facultad>()
    var aux: Facultad
    var flag = true
    while (flag) {
        println("¿Quiere agregar una facultad a la Universidad?")
        println("Y / N")
        if (readln().equals("N")){
            return listaFacultadesNuevas
        }else{
            println("Ingrese el ID de una Facultad")
            aux = obtenerFacultadPorId(readln().toInt())
            listaFacultadesNuevas.add(aux)
        }
    }
    return listaFacultadesNuevas
}

fun obtenerFacultadPorId(id: Int): Facultad {
    val listaFacultades = mutableListOf<Facultad>()
    var facultad = Facultad()
    try {
        val file = File("facultades.txt")
        val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
        reader.lines().forEach {
            val tokens = StringTokenizer(it, ",")
            var dato: String = tokens.nextToken()
            val nuevaFacultad = Facultad()
            nuevaFacultad.id_f = dato.toInt()
            dato = tokens.nextToken()
            nuevaFacultad.fechaCreacion = LocalDate.parse(dato)
            dato = tokens.nextToken()
            nuevaFacultad.nombre = dato
            dato = tokens.nextToken()
            nuevaFacultad.numeroProfesores = dato.toInt()
            dato = tokens.nextToken()
            nuevaFacultad.poseeAsociacionDeEstudiantes = dato.toBoolean()
            dato = tokens.nextToken()
            nuevaFacultad.promedioInvestigativo = dato.toFloat()
            listaFacultades.add(nuevaFacultad)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    listaFacultades.forEach {
        if(it.id_f == id){
            facultad = it
        }
    }
    return facultad
}

fun actualizarUniversidad(){
    val listaUniversidad = mutableListOf<Universidad>()
    println("Introduzca el ID de la universidad que desea actualizar")
    val id = readln().toInt()
    // Lee el archivo y genera una lista con las universidades actuales
    val file = File("universidades.txt")
    val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    reader.lines().forEach {
        var miListaFacultades = mutableListOf<Facultad>()
        val tokens = StringTokenizer(it, ",")
        var dato: String = tokens.nextToken()
        val nuevaUniversidad = Universidad()
        nuevaUniversidad.id_u = dato.toInt()
        dato = tokens.nextToken()
        nuevaUniversidad.fechaCreacion = LocalDate.parse(dato)
        dato = tokens.nextToken()
        nuevaUniversidad.nombre = dato
        dato = tokens.nextToken()
        nuevaUniversidad.esPublica = dato.toBoolean()
        dato = tokens.nextToken()
        nuevaUniversidad.promedioNotas = dato.toFloat()
        dato = tokens.nextToken()
        nuevaUniversidad.numeroEstudiantes = dato.trim().toInt()
        // aquí se vuelve a realizar una búsqueda por tokens
        while(tokens.hasMoreTokens()){
            dato = tokens.nextToken()
            miListaFacultades.add(obtenerFacultadPorId(dato.toInt()))
        }
        nuevaUniversidad.listaFacultades = miListaFacultades
        listaUniversidad.add(nuevaUniversidad)
    }
    var universidadAux = Universidad()
    listaUniversidad.forEach {
        if (it.id_u == id){
            universidadAux = it
        }
    }
    val indiceUniversidad = listaUniversidad.indexOf(universidadAux)
    // Eliminamos la universidad anterior
    listaUniversidad.remove(universidadAux)
    // Actualizamos la universidad
    print("Escriba el nuevo nombre de la universidad: ")
    universidadAux.nombre = readln()
    print("Escriba la nueva fecha de fundación de la universidad: ")
    universidadAux.fechaCreacion = LocalDate.parse(readln())
    print("Escriba el nuevo promedio de notas de la universidad: ")
    universidadAux.promedioNotas = readln().toFloat()
    print("Escriba V si la universidad es pública. Caso contrario, escriba F: ")
    universidadAux.esPublica = readln() == "V"
    print("Escriba el nuevo nùmero de estudiantes de la universidad: ")
    universidadAux.numeroEstudiantes = readln().toInt()

    // Añadimos la universidad actualizada
    listaUniversidad.add(indiceUniversidad, universidadAux)

    // Vamos a reescribir toodo el archivo
    var archivo: File?
    var fw: FileWriter? = null
    var pw: PrintWriter?
    try {
        archivo = File("universidades.txt")
        fw = FileWriter(archivo)
        pw = PrintWriter(fw)
        listaUniversidad.forEach {
            pw.println(it.obtenerAtributos())
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            fw?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun eliminarUniversidad(){
    val listaUniversidades = mutableListOf<Universidad>()
    println("Introduzca el ID de la universidad que desea eliminar")
    val id = readln().toInt()
    // Lee el archivo y genera una lista con las universidades actuales
    val file = File("universidades.txt")
    val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    reader.lines().forEach {
        var miListaFacultades = mutableListOf<Facultad>()
        val tokens = StringTokenizer(it, ",")
        var dato: String = tokens.nextToken()
        val nuevaUniversidad = Universidad()
        nuevaUniversidad.id_u = dato.toInt()
        dato = tokens.nextToken()
        nuevaUniversidad.fechaCreacion = LocalDate.parse(dato)
        dato = tokens.nextToken()
        nuevaUniversidad.nombre = dato
        dato = tokens.nextToken()
        nuevaUniversidad.esPublica = dato.toBoolean()
        dato = tokens.nextToken()
        nuevaUniversidad.promedioNotas = dato.toFloat()
        dato = tokens.nextToken()
        nuevaUniversidad.numeroEstudiantes= dato.toInt()
        // aquí se vuelve a realizar una búsqueda por tokens
        while(tokens.hasMoreTokens()){
            dato = tokens.nextToken()
            miListaFacultades.add(obtenerFacultadPorId(dato.toInt()))
        }
        nuevaUniversidad.listaFacultades = miListaFacultades
        listaUniversidades.add(nuevaUniversidad)
    }
    var universidadAux = Universidad()
    listaUniversidades.forEach {
        if (it.id_u == id){
            universidadAux = it
        }
    }
    listaUniversidades.remove(universidadAux)
    // Ya se tiene una lista sin el elemento
    // Vamos a reescribir toodo el archivo
    var archivo: File?
    var fw: FileWriter? = null
    var pw: PrintWriter?
    try {
        archivo = File("universidades.txt")
        fw = FileWriter(archivo)
        pw = PrintWriter(fw)
        listaUniversidades.forEach {
            pw.println(it.obtenerAtributos())
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            fw?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun agregarFacultad(){
    var miFacultad = Facultad()
    var archivo: File?
    var fw: FileWriter? = null
    var pw: PrintWriter?
    // Obtener los datos de la canción
    print("Escriba el id de la facultad ")
    miFacultad.id_f = readln().toInt()
    print("Escriba la fecha de fundación de la facultad (yyyy-mm-dd): ")
    miFacultad.fechaCreacion = LocalDate.parse(readln())
    print("Escriba el nombre de la facultad: ")
    miFacultad.nombre = readln()
    print("Escriba el número de profesores de la facultad: ")
    miFacultad.numeroProfesores = readln().toInt()
    print("Escriba V si la facultad tiene asociación de estudiantes. Caso contrario, escriba F: ")
    miFacultad.poseeAsociacionDeEstudiantes = readln() == "V"
    print("Escriba el promedio de investigación de la Facultad: ")
    miFacultad.promedioInvestigativo = readln().toFloat()
    try {
        archivo = File("facultades.txt")
        fw = FileWriter(archivo, true)
        pw = PrintWriter(fw)
        //
        pw.println(miFacultad.obtenerAtributos())

    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            fw?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun leerFacultades(){
    val listaFacultad = mutableListOf<Facultad>()
    try {
        val file = File("facultades.txt")
        val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
        reader.lines().forEach {
            val tokens = StringTokenizer(it, ",")
            var dato: String = tokens.nextToken()
            val nuevaFacultad = Facultad()
            nuevaFacultad.id_f = dato.toInt()
            dato = tokens.nextToken()
            nuevaFacultad.fechaCreacion = LocalDate.parse(dato)
            dato = tokens.nextToken()
            nuevaFacultad.nombre = dato
            dato = tokens.nextToken()
            nuevaFacultad.numeroProfesores = dato.toInt()
            dato = tokens.nextToken()
            nuevaFacultad.poseeAsociacionDeEstudiantes = dato.toBoolean()
            dato = tokens.nextToken()
            nuevaFacultad.promedioInvestigativo = dato.toFloat()
            listaFacultad.add(nuevaFacultad)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    listaFacultad.forEach{
        println(it.toString())
    }
}

fun actualizarFacultad(){
    val listaFacultad = mutableListOf<Facultad>()
    println("Introduzca el ID de la facultad que desea actualizar")
    val id = readln().toInt()
    // Lee el archivo y genera una lista con las facultades actuales
    val file = File("facultades.txt")
    val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    reader.lines().forEach {
        val tokens = StringTokenizer(it, ",")
        var dato: String = tokens.nextToken()
        val nuevaFacultad = Facultad()
        nuevaFacultad.id_f = dato.toInt()
        dato = tokens.nextToken()
        nuevaFacultad.fechaCreacion = LocalDate.parse(dato)
        dato = tokens.nextToken()
        nuevaFacultad.nombre = dato
        dato = tokens.nextToken()
        nuevaFacultad.numeroProfesores = dato.toInt()
        dato = tokens.nextToken()
        nuevaFacultad.poseeAsociacionDeEstudiantes = dato.toBoolean()
        dato = tokens.nextToken()
        nuevaFacultad.promedioInvestigativo = dato.toFloat()
        listaFacultad.add(nuevaFacultad)
    }
    var facultadAux = Facultad()
    listaFacultad.forEach {
        if (it.id_f == id){
            facultadAux = it
        }
    }
    val indiceFacultad = listaFacultad.indexOf(facultadAux)
    // Eliminamos la facultad anterior
    listaFacultad.remove(facultadAux)
    // Actualizamos la facultad

    print("Escriba el nuevo id de la facultad ")
    facultadAux.id_f = readln().toInt()
    print("Escriba la nueva fecha de fundación de la facultad (yyyy-mm-dd): ")
    facultadAux.fechaCreacion = LocalDate.parse(readln())
    print("Escriba el nuevo nombre de la facultad: ")
    facultadAux.nombre = readln()
    print("Escriba el nuevo número de profesores de la facultad: ")
    facultadAux.numeroProfesores = readln().toInt()
    print("Escriba V si la facultad tiene asociación de estudiantes. Caso contrario, escriba F: ")
    facultadAux.poseeAsociacionDeEstudiantes = readln() == "V"
    print("Escriba el nuevo promedio de investigación de la Facultad: ")
    facultadAux.promedioInvestigativo = readln().toFloat()

    listaFacultad.add(indiceFacultad, facultadAux)

    //println(listaCanciones)

    // Vamos a reescribir toodo el archivo
    var archivo: File?
    var fw: FileWriter? = null
    var pw: PrintWriter?
    try {
        archivo = File("facultades.txt")
        fw = FileWriter(archivo)
        pw = PrintWriter(fw)
        listaFacultad.forEach {
            pw.println(it.obtenerAtributos())
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            fw?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun eliminarFacultad(){
    val listaFacultad = mutableListOf<Facultad>()
    println("Introduzca el ID de la facultad que desea eliminar")
    val id = readln().toInt()
    // Lee el archivo y genera una lista con las facultades actuales
    val file = File("facultades.txt")
    val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    reader.lines().forEach {
        val tokens = StringTokenizer(it, ",")
        var dato: String = tokens.nextToken()
        val nuevaFacultad = Facultad()
        nuevaFacultad.id_f = dato.toInt()
        dato = tokens.nextToken()
        nuevaFacultad.fechaCreacion = LocalDate.parse(dato)
        dato = tokens.nextToken()
        nuevaFacultad.nombre = dato
        dato = tokens.nextToken()
        nuevaFacultad.numeroProfesores = dato.toInt()
        dato = tokens.nextToken()
        nuevaFacultad.poseeAsociacionDeEstudiantes = dato.toBoolean()
        dato = tokens.nextToken()
        nuevaFacultad.promedioInvestigativo = dato.toFloat()
        listaFacultad.add(nuevaFacultad)
    }
    var facultadAux = Facultad()
    listaFacultad.forEach {
        if (it.id_f == id){
            facultadAux = it
        }
    }
    listaFacultad.remove(facultadAux)
    // Ya se tiene una lista sin el elemento
    // Vamos a reescribir toodo el archivo
    var archivo: File?
    var fw: FileWriter? = null
    var pw: PrintWriter?
    try {
        archivo = File("facultades.txt")
        fw = FileWriter(archivo)
        pw = PrintWriter(fw)
        listaFacultad.forEach {
            pw.println(it.obtenerAtributos())
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            fw?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

