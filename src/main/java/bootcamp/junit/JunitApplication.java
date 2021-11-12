package bootcamp.junit;

import bootcamp.junit.bbdd.BaseDatosServiceI;
import bootcamp.junit.model.Articulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JunitApplication implements CommandLineRunner {

    @Autowired
    BaseDatosServiceI baseDatosService;
    
    public static void main(String[] args) {
        SpringApplication.run(JunitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        baseDatosService.initDB();
        Articulo articulo = new Articulo("Calcetines", 5.95);
        baseDatosService.insertarArticulo(articulo);
        baseDatosService.findArticuloById(9);
        //baseDatosService.findArticuloById(baseDatosService.lastIndex());
    }

}
