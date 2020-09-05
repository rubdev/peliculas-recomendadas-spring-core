package dev.ruben.peliculasrecomendadas;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.ruben.peliculasrecomendadas.config.AppConfig;

/**
 * <h1>Clase principal del programa</h1>
 * 
 * <p>Tan solo carga el contexto a partir de la clase de configuración. 
 * Tras esto, lanza el componente que ejecuta realmente el ciclo 
 * del programa.</p>
 * 
 * @author rsegr
 *
 */
public class PeliculasRecomendasApp {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		
		PeliculasRecomendadasRunApp runApp = applicationContext.getBean(PeliculasRecomendadasRunApp.class);
		
		runApp.run(args);
		
		((AnnotationConfigApplicationContext) applicationContext).close();

	}

}
