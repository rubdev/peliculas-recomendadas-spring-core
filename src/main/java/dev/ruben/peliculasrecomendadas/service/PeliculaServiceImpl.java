package dev.ruben.peliculasrecomendadas.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import dev.ruben.peliculasrecomendadas.dao.PeliculaDao;
import dev.ruben.peliculasrecomendadas.model.Pelicula;

/**
 * <h1>Clase PeliculaServiceImpl</h1>
 * <p>
 * Implementación del servicio de consulta sobre el repositorio de películas.
 * </p>
 * 
 * @author rsegr
 *
 */
public class PeliculaServiceImpl implements PeliculaQueryService {

	@Autowired
	PeliculaDao dao;

	private Predicate<Pelicula> predicado;

	public void init() {
		predicado = null;
	}

	/**
	 * <p>Obtiene una colección con todas las películas del fichero de películas</p>
	 */
	@Override
	public Collection<Pelicula> exec() {
		// @formatter:off
		return dao.buscarTodas().stream().filter(predicado).collect(Collectors.toList());
		// @formatter:on
	}
	
	/**
	 * <p>Reliza una búsqueda de películas filtrando por género</p>
	 */
	@Override
	public PeliculaQueryService algunGenero(String... generos) {
		Predicate<Pelicula> peliculaAlgunGenero = (pelicula -> Arrays.stream(generos)
				.anyMatch(pelicula.getGeneros()::contains));
		predicado = (predicado == null) ? peliculaAlgunGenero : predicado.and(peliculaAlgunGenero);
		return this;
	}

	/**
	 * <p>Reliza una búsqueda de películas filtrando por los géneros</p>
	 */
	@Override
	public PeliculaQueryService todosGeneros(String... generos) {
		Predicate<Pelicula> peliculasTodosGeneros = (pelicula -> Arrays.stream(generos)
				.allMatch(pelicula.getGeneros()::contains));
		predicado = (predicado == null) ? peliculasTodosGeneros : predicado.and(peliculasTodosGeneros);
		return this;
	}

	/**
	 * <p>Reliza una búsqueda de películas filtrando por año</p>
	 */
	@Override
	public PeliculaQueryService año(String año) {
		Predicate<Pelicula> peliculasAño = (pelicula -> pelicula.getAño().equalsIgnoreCase(año));
		predicado = (predicado == null) ? peliculasAño : predicado.and(peliculasAño);
		return this;
	}

	/**
	 * <p>Reliza una búsqueda de películas filtrando por una rango de fechas</p>
	 */
	@Override
	public PeliculaQueryService rangoAños(String desde, String hasta) {
		Predicate<Pelicula> peliculasEntreAños = (pelicula -> {
			LocalDate desdeA = LocalDate.of(Integer.parseInt(desde), 1, 1);
			LocalDate hastaA = LocalDate.of(Integer.parseInt(hasta), 1, 3);
			LocalDate añoPelicula = LocalDate.of(Integer.parseInt(pelicula.getAño()), 1, 2);

			return añoPelicula.isAfter(desdeA) && añoPelicula.isBefore(hastaA);
		});

		predicado = (predicado == null) ? peliculasEntreAños : predicado.and(peliculasEntreAños);

		return this;
	}

	/**
	 * <p>Reliza una búsqueda de películas filtrando por el nombre</p>
	 */
	@Override
	public PeliculaQueryService porTitulo(String titulo) {
		Predicate<Pelicula> peliculasPotTitulo = (pelicula -> pelicula.getNombre().toLowerCase()
				.contains(titulo.toLowerCase()));
		predicado = (predicado == null) ? peliculasPotTitulo : predicado.and(peliculasPotTitulo);

		return this;
	}

}
