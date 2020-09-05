package dev.ruben.peliculasrecomendadas.dao;

import java.util.Collection;

import dev.ruben.peliculasrecomendadas.model.Pelicula;

/**
 * <h1>Interfaz PeliculaDao</h1> 
 * <p>Declaración de métodos de acceso a datos para la clase Pelicula</p>
 * 
 * @author rsegr
 *
 */
public interface PeliculaDao {

	public Pelicula buscarPorId(long id);
	public Collection<Pelicula> buscarTodas();
	public void insertar(Pelicula pelicula);
	public void editar(Pelicula pelicula);
	public void borrar (long id);
	
}
