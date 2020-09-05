package dev.ruben.peliculasrecomendadas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <h1>Clase AppConfig</h1>
 * <p>Es la encargada de gestionar la configuración del proyecto</p>
 * <p>Está anotada con @Configuration, que indica que es una clase con
 * aspectos de configuración; @ComponentScan, que nos permite buscar
 * a través de uno o más paquetes si hay clases estereotipadas; y
 * por último con @PropertySource, que nos permite cargar un 
 * fichero de propiedades, para inyectar sus valores, los cuales
 * podemos utilizar con la notación ${prop.from.propeties.files}</p>
 * 
 * @author rsegr
 *
 */
@Configuration // indicamos que es fichero de configuración
@ComponentScan(basePackages = "dev.ruben.peliculasrecomendadas") // escaneará todos los paquetes por debajo
@PropertySource("classpath:/peliculas.properties") // ruta de donde coge las properties
public class AppConfig {

	@Value("${file.path}")
	public String file;
	
	@Value("${file.csv.separator}")
	public String separator;
	
	@Value("${file.csv.list_separator}")
	public String listSeparator;
	
	public String getFile() {
		return file;
	}
	
	public String getSeparator() {
		return separator;
	}
	
	public String getListSeparator() {
		return listSeparator;
	}
	
}
