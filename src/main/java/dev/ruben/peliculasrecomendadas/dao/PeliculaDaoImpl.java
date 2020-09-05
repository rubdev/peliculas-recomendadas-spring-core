package dev.ruben.peliculasrecomendadas.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.ruben.peliculasrecomendadas.config.AppConfig;
import dev.ruben.peliculasrecomendadas.model.Pelicula;

@Repository // indicamos que es repositorio
public class PeliculaDaoImpl implements PeliculaDao {
	
	@Autowired // inyectamos el fichero de configuración
	private AppConfig aplicationConfig;
	
	public List<Pelicula> peliculas = new ArrayList<>();
	
	/** 
	 * <p>Manejo del ciclo de vida del Bean a través de método init 
	 * en el que vamos a cargar las peliculas</p>
	 */
	public void init() {
		peliculas = UtilCargaPeliculas.leerArchivo(aplicationConfig.getFile(), aplicationConfig.getSeparator(), aplicationConfig.getListSeparator());
	}

	/**
	 * <p>Realiza una búsqueda de peliculas por id</p>
	 */
	public Pelicula buscarPorId(long id) {
		Optional<Pelicula> resultado = peliculas
				.stream()
				.filter(f -> f.getId() == id)
				.findFirst();
		return resultado.orElse(null);
	}

	/**
	 * <p>Devuelve una colección con todas las películas almacenadas en el fichero</p>
	 */
	public Collection<Pelicula> buscarTodas() {
		return peliculas;
	}

	/**
	 * <p>Inserta una nueva pelicula en el fichero de peliculas</p>
	 */
	public void insertar(Pelicula pelicula) {
		peliculas.add(pelicula);
	}

	/**
	 * <p>Edita los datos de una película del fichero con los nuevos pasados por parámetro</p>
	 */
	public void editar(Pelicula pelicula) {
		int index = getIndexOf(pelicula.getId());
		if (index != -1) {
			peliculas.set(index, pelicula);
		}
	}

	/**
	 * <p>Borra la película que coincida por el id en el fichero</p>
	 */
	public void borrar(long id) {
		int index = getIndexOf(id);
		if (index != -1) {
			peliculas.remove(index);
		}
	}
	
	/**
	 * <p>Devuelve el índice de una película dentro del listado de películas</p>
	 * @param id
	 * @return int indice
	 */
	private int getIndexOf(long id) {
		boolean encontrado = false;
		int indice = 0;
		while (!encontrado && indice < peliculas.size()) {
			if (peliculas.get(indice).getId() == id) {
				encontrado = true;
			} else {
				indice++;
			}
		}
		return (encontrado) ? indice : -1;
	}

}
