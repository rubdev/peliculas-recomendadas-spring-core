package dev.ruben.peliculasrecomendadas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import dev.ruben.peliculasrecomendadas.model.Pelicula;
import dev.ruben.peliculasrecomendadas.service.PeliculaQueryService;

/**
 * Este componente tiene el ciclo de ejecución del programa, el cuerpo
 * principal. En una aplicación web, seguramente hubiera sido anotada
 * con @Controller.
 * 
 * El método run recibe los argumentos que se han pasado por la línea de
 * comandos, y en función de cuantos y cuáles son, verifica si la sintaxis es
 * correcta o no, y ejecuta la consulta correspondiente.
 * 
 * @author rsegr
 *
 */
public class PeliculasRecomendadasRunApp {

	@Autowired
	PeliculaQueryService peliculaQueryService;

	public void run(String[] args) {

		if (args.length < 1) {
			System.out.println("Error de sintaxis");
		} else {
			List<String[]> argumentos = new ArrayList<>();

			for (int i = 0; i < args.length; i += 2) {
				argumentos.add(new String[] { args[i], args[i + 1] });
			}

			boolean error = false;

			for (String[] argumento : argumentos) {
				switch (argumento[0].toLowerCase()) {
				case "-ag":
					peliculaQueryService.algunGenero(argumento[1].split(","));
					break;
				case "-tg":
					peliculaQueryService.todosGeneros(argumento[1].split(","));
					break;
				case "-y":
					peliculaQueryService.año(argumento[1]);
					break;
				case "-b":
					String[] years = argumento[1].split(",");
					peliculaQueryService.rangoAños(years[0], years[1]);
					break;
				case "-t":
					peliculaQueryService.porTitulo(argumento[1]);
					break;
				default:
					error = true;
					System.out.println("Error de sintaxis");
				}

			}

			if (!error) {
				Collection<Pelicula> result = peliculaQueryService.exec();
				System.out.printf("%s\t%-50s\t%s\t%s\n", "ID", "Título", "Año", "Géneros");
				if (result != null) {
					result.forEach(f -> System.out.printf("%s\t%-50s\t%s\t%s\n", f.getId(), f.getNombre(), f.getAño(),
							f.getGeneros().stream().collect(Collectors.joining(", "))));
				} else {
					System.out.println("No hay películas que cumplan esos criterios. Lo sentimos");
				}
			}
		}

	}

}
