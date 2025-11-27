import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("EJERCICIO 1");
        List<Alumno> alumnos = List.of(
                new Alumno("maria", 9.5, "economia"),
                new Alumno("sofia", 4.0, "biologia"),
                new Alumno("carlos", 7.8, "matematica"),
                new Alumno("lucas", 6.0, "matematica"),
                new Alumno("rocio", 8.2, "economia"),
                new Alumno("tomas", 5.5, "so")
        );

        List<String> aprobados = alumnos.stream()
                .filter(a -> a.getNota() >= 7 && a.getNota() <= 10)
                .map(a -> a.getNombre().toUpperCase())
                .sorted()
                .toList();
        System.out.println("alumnos aprobados mayor a 7 ");
        System.out.println(aprobados);

        double promedio = alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0);
        System.out.println("promedio de alumnos");
        System.out.println(promedio);

        Map<String, List<Alumno>> agrupados = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));
        agrupados.forEach((curso, lista) -> {
            System.out.println(curso + ": " + lista);
        });
        System.out.println();

        List<Alumno> top3 = alumnos.stream()
                .sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
                .limit(3)
                .toList();
        System.out.println("TOP 3 mejores promedios de alumnos ");
        top3.forEach(System.out::println);


        System.out.println("EJERCICIO 2");
        List<Producto> productos = List.of(
                new Producto("arroz", "cereal", 150, 10),
                new Producto("fideos", "cereal", 220, 30),
                new Producto("yogur", "lacteo", 180, 40),
                new Producto("queso", "lacteo", 450, 15)
        );

        List<Producto> proXprecio = productos.stream()
                .filter(p -> p.getPrecio() > 100)
                .sorted(Comparator.comparing(Producto::getPrecio))
                .collect(toList());
        proXprecio.forEach(System.out::println);

        System.out.println("Cantidad de stock por categoria ");
        Map<String, Integer> stockXcategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)
                ));
        stockXcategoria.forEach((categoria, stock) ->
                System.out.println(categoria + ":" + stock));

        System.out.println("Nombre y precio del Producto");
        String nombreCONprecio = productos.stream()
                .map(p -> p.getNombre() + " $" + p.getPrecio())
                .collect(Collectors.joining(";"));
        System.out.println(nombreCONprecio);

        System.out.println("Promedio de precio por categoría");

        double promedioGeneral = productos.stream()
                .collect(Collectors.averagingDouble(Producto::getPrecio));

        System.out.println("Precio promedio general: $" + promedioGeneral);

        Map<String, Double> promedioPORcategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));

        promedioPORcategoria.forEach((categoria, prom) -> {
            // acá antes imprimías "promedio" en vez de "prom"
            System.out.println(categoria + ": $" + prom);
        });


        System.out.println("EJERCICIO 3");
        List<Libro> libros = List.of(
                new Libro("EL BOSQUE AZUL", "Ana", 450, 1500),
                new Libro("NOCHES DE INVIERNO", "Martin", 320, 2800),
                new Libro("CAMINOS CRUZADOS", "Ana", 250, 1900.50),
                new Libro("LUCES DEL SUR", "Pedro", 610, 2200)
        );

        System.out.println("Libros con mas de 300 pag");
        List<String> pag = libros.stream()
                .filter(a -> a.getPaginas() > 300)
                .sorted(Comparator.comparing(Libro::getTitulo))
                .map(Libro::getTitulo)
                .toList();
        System.out.println(pag);

        System.out.println("promedio de paginas de TODOS los libros");
        double promedioLibros = libros.stream()
                .collect(Collectors.averagingDouble(Libro::getPaginas));
        System.out.println(promedioLibros);

        System.out.println("Libros X Autor ");
        Map<String, Long> cantidadPorAutor = libros.stream()
                .collect(Collectors.groupingBy(Libro::getAutor, Collectors.counting()));

        cantidadPorAutor.forEach((autor, cantidad) -> {
            System.out.println(autor + " tiene " + cantidad + " libros.");
        });

        System.out.println("LIBRO MAS CARO");
        Optional<Libro> libroMasCaro = libros.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Libro::getPrecioLibro)));

        System.out.println(libroMasCaro);

        System.out.println("EJERCICIO 4");
        List<Empleado> empleados = List.of(
                new Empleado("julia", "sistemas", 9000.0, 26),
                new Empleado("franco", "recursos", 3500.0, 29),
                new Empleado("marcos", "sistemas", 7200.0, 22),
                new Empleado("paula", "recursos", 1500.0, 35),
                new Empleado("noelia", "industria", 2100.0, 27)
        );

        System.out.println("Empleados con salrio mayor a 2000");
        List<Empleado> mayorSalario = empleados.stream()
                .filter(a -> a.getSalario() > 2000)
                .sorted(Comparator.comparing(Empleado::getSalario))
                .collect(toList());
        System.out.println(mayorSalario);

        System.out.println("Promedio general");
        double promedioSalario = empleados.stream()
                .collect(Collectors.averagingDouble(Empleado::getSalario));
        System.out.println(promedioSalario);

        System.out.println("Empleado por departamento y su sueldo");
        Map<String, Double> empleadoXdepto = empleados.stream()
                .collect(groupingBy(
                        Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)
                ));

        empleadoXdepto.forEach((departamento, salario) -> {
            System.out.println(departamento + " " + salario);
        });

        System.out.println("mas jovenes");
        List<Empleado> masJoven = empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                .limit(2)
                .collect(toList());

        System.out.println(masJoven);
    }
}
