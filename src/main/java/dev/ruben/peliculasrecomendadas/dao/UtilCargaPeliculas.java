package dev.ruben.peliculasrecomendadas.dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.ResourceUtils;

import dev.ruben.peliculasrecomendadas.model.Pelicula;

/**
 * <h1>Clase UtilCargaPeliculas</h1>
 * <p>
 * Clase de utilidad, que incluye un método estático para la lectura y
 * procesamiento del fichero CSV que incluye todos los datos.
 * </p>
 * 
 * @author rsegr
 *
 */
public class UtilCargaPeliculas {

	/**
	 * <p>Lectura y procesamiento de fichero CSV con los datos de las películas</p>
	 * @param path
	 * @param separator
	 * @param listSeparator
	 * @return List<Pelicula>
	 */
	public static List<Pelicula> leerArchivo(final String path, final String separator, final String listSeparator) {
		List<Pelicula> result = new ArrayList<>();

		try {
			// @formatter:off
			result = Files.lines(Paths.get(ResourceUtils.getFile(path).toURI())).skip(1).map(line -> {
				String[] values = line.split(separator);
				return new Pelicula(Long.parseLong(values[0]), values[1], values[2],
						Arrays.asList(values[3].split(listSeparator)));
			}).collect(Collectors.toList());
			// @formatter:on

		} catch (Exception e) {
			System.err.println("Error de lectura del fichero de datos: imdb_data");
			System.exit(-1);
		}

		return result;

	}
}
