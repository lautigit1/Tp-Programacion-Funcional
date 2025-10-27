import ejercicio1.Alumno;
import ejercicio2.Producto;
import ejercicio3.Libro;
import ejercicio4.Empleado;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n=============== EJERCICIO 1 ===============");
        List<Alumno> listaEstudiantes = Arrays.asList(
                new Alumno("Roberto",8.5, "Java"),
                new Alumno("Beatriz",6.0, "Python"),
                new Alumno("David",9.0, "Java"),
                new Alumno("Elena",5.5, "JavaScript"),
                new Alumno("Fernando",3.5, "Java"),
                new Alumno("Gabriela",10.0, "Python")
        );

        System.out.println("\n1. Alumnos Aprobados:");
        List<String> estudiantesAprobados = listaEstudiantes.stream()
                .filter(a -> a.getNota() >= 7)
                .map(a -> a.getNombre().toUpperCase())
                .sorted()
                .collect(Collectors.toList());

        estudiantesAprobados.forEach(System.out::println);


        System.out.println("\n2. Promedio general de notas:");
        double promedioNotasGeneral = listaEstudiantes.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0.0);

        System.out.println("El promedio es: " + promedioNotasGeneral);


        System.out.println("\n3. Alumnos agrupados por curso");
        Map<String, List<Alumno>> estudiantesPorCurso = listaEstudiantes.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));

        estudiantesPorCurso.forEach((curso, lista) -> {
            System.out.println("Curso: " + curso);
            lista.forEach(alumno -> System.out.println(" - " + alumno.getNombre()));
        });


        System.out.println("\n4. Los 3 mejores promedios:");
        List<Double> mejoresCalificaciones = listaEstudiantes.stream()
                .map(Alumno::getNota)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Las 3 mejores notas son: " + mejoresCalificaciones);

        System.out.println("\n=============== EJERCICIO 2 ===============");
        List<Producto> listaDeProductos = Arrays.asList(
                new Producto("Computadora Portatil", "Tecnología", 1200.50, 10),
                new Producto("Ratón", "Tecnología", 25.00, 50),
                new Producto("Teclado Mecánico", "Tecnología", 75.99, 30),
                new Producto("Camiseta", "Ropa", 20.00, 100),
                new Producto("Vaqueros", "Ropa", 110.00, 80)
        );

        System.out.println("\n1. Productos con precio >100, ordenados por precio descendente:");
        List<Producto> productosDeAltoPrecio = listaDeProductos.stream()
                .filter(p -> p.getPrecio() > 100)
                .sorted(Comparator.comparing(Producto::getPrecio).reversed())
                .collect(Collectors.toList());

        productosDeAltoPrecio.forEach(System.out::println);


        System.out.println("\n2. Agrupar por categoría y calcular el stock total:");
        Map<String, Integer> inventarioPorCategoria = listaDeProductos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)
                ));

        System.out.println("Stock por categoría: " + inventarioPorCategoria);


        System.out.println("\n3. String con nombre y precio:");
        String informeDeProductos = listaDeProductos.stream()
                .map(p -> p.getNombre() + ";" + p.getPrecio())
                .collect(Collectors.joining(" | "));

        System.out.println(informeDeProductos);


        System.out.println("\n4. Precio promedio general y por categoría:");
        //General
        double precioPromedioTotal = listaDeProductos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);
        System.out.println("Precio promedio general: " + precioPromedioTotal);

        // Por categoría
        Map<String, Double> precioPromedioPorCategoria = listaDeProductos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));
        System.out.println("Promedio por categoría: " + precioPromedioPorCategoria);


        System.out.println("\n=============== EJERCICIO 3 ===============");

        List<Libro> coleccionDeLibros = Arrays.asList(
                new Libro("El Señor de los Anillos", "J.R.R. Martin", 1200, 25.50),
                new Libro("Cien Años de Soledad", "Isabel Allende", 450, 18.00),
                new Libro("Don Quijote de la Mancha", "Arturo Pérez-Reverte", 863, 22.99),
                new Libro("1984", "Aldous Huxley", 328, 15.75),
                new Libro("Ficciones", "Ernesto Sabato", 224, 12.50),
                new Libro("Rayuela", "Mario Vargas Llosa", 600, 20.00)
        );

        System.out.println("\n1. Títulos de libros con más de 300 páginas:");
        List<String> librosExtensos = coleccionDeLibros.stream()
                .filter(libro -> libro.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted()
                .collect(Collectors.toList());

        librosExtensos.forEach(System.out::println);


        System.out.println("\n2. Promedio de páginas:");
        double promedioDePaginas = coleccionDeLibros.stream()
                .mapToInt(Libro::getPaginas)
                .average()
                .orElse(0.0);

        System.out.println("El promedio de páginas es: " + promedioDePaginas);


        System.out.println("\n3. Cantidad de libros por autor:");
        Map<String, Long> conteoLibrosPorAutor = coleccionDeLibros.stream()
                .collect(Collectors.groupingBy(
                        Libro::getAutor,
                        Collectors.counting()
                ));

        conteoLibrosPorAutor.forEach((autor, cantidad) ->
                System.out.println(autor + ": " + cantidad + " libro(s)"));


        System.out.println("\n4. Libro más caro:");
        Optional<Libro> libroDeMayorPrecio = coleccionDeLibros.stream()
                .max(Comparator.comparing(Libro::getPrecio));

        libroDeMayorPrecio.ifPresent(libro -> System.out.println("El libro más caro es: " + libro.getTitulo()));


        System.out.println("\n=============== EJERCICIO 4 ===============");

        List<Empleado> plantillaDeEmpleados = Arrays.asList(
                new Empleado("Valeria", "Ventas", 2500, 30),
                new Empleado("Ricardo", "IT", 3200, 25),
                new Empleado("Paula", "Ventas", 1900, 35),
                new Empleado("Sergio", "IT", 2800, 22),
                new Empleado("Daniela", "RRHH", 2100, 28)
        );

        System.out.println("\n1. Empleados con salario mayor a 2000:");
        List<Empleado> empleadosConSalarioAlto = plantillaDeEmpleados.stream()
                .filter(e -> e.getSalario() > 2000)
                .sorted(Comparator.comparingDouble(Empleado::getSalario).reversed())
                .collect(Collectors.toList());

        empleadosConSalarioAlto.forEach(System.out::println);


        System.out.println("\n2. Salario promedio general:");
        double salarioPromedioGeneral = plantillaDeEmpleados.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);

        System.out.println("El salario promedio es: " + salarioPromedioGeneral);


        System.out.println("\n3. Suma de salarios por departamento:");
        Map<String, Double> sumaSalariosPorDepto = plantillaDeEmpleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)
                ));

        sumaSalariosPorDepto.forEach((depto, suma) ->
                System.out.println("Departamento " + depto + ": $" + suma));


        System.out.println("\n4. Los 2 empleados más jóvenes:");
        List<String> empleadosDeMenorEdad = plantillaDeEmpleados.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                .limit(2)
                .map(Empleado::getNombre)
                .collect(Collectors.toList());

        System.out.println("Los más jóvenes son: " + empleadosDeMenorEdad);
    }
}
