package dev.ruben.peliculasrecomendadas.service;

import java.util.Collection;

import dev.ruben.peliculasrecomendadas.model.Pelicula;

/**
 * <h1>Intefaz del servicio de consulta sobre el repositorio de películas.</h1>
 * <p> Para ejecutar la consulta, se invoca en último lugar el método exec</p>
 * 
 * @author rsegr
 *
 */
public interface PeliculaQueryService {

	public Collection<Pelicula> exec();
	
	public PeliculaQueryService algunGenero(String... generos);

	public PeliculaQueryService todosGeneros(String... generos);

	public PeliculaQueryService año(String año);

	public PeliculaQueryService rangoAños(String desde, String hasta);

	public PeliculaQueryService porTitulo(String titulo);
	
}
