package cz.muni.pa165.pneuservis.backend;

import cz.muni.pa165.pneuservis.backend.service.HelloWorld;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Peter on 10/19/2016.
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        context.getBean(HelloWorld.class).sayHello();

    }
}
