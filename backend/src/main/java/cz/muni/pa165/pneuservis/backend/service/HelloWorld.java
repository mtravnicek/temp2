package cz.muni.pa165.pneuservis.backend.service;

import cz.muni.pa165.pneuservis.backend.domain.Car;
import cz.muni.pa165.pneuservis.backend.domain.User;
import cz.muni.pa165.pneuservis.backend.repository.CarRepository;
import cz.muni.pa165.pneuservis.backend.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;

/**
 * Created by Peter on 10/19/2016.
 */
@Named
public class HelloWorld {

    @Inject
    private CarRepository repository;

    @Inject
    private UserRepository userDao;

    public void sayHello() {
        System.out.println("COOL");
        Car c = new Car();
        c.setTest("test");
        repository.save(c);

        repository.findAll().forEach(e -> System.out.println("CARR" + e.getId() + " " + e.getTest()));
    }

    public void insertUser() {
        User user = new User();
        user.setEmail("mich.krajcovic@gmail.com");
        user.setName("Michal Krajčovič");
        user.setPassword("B1C788ABAC15390DE987AD17B65AC73C9B475D428A51F245C645A442FDDD078B");
        user.setRoles(Arrays.asList(User.Role.ADMIN, User.Role.CUSTOMER));

        userDao.save(user);
    }

    @Transactional
    public void getUser() {
        User u = userDao.findAll().get(0);
        System.out.println(u.getName());
        System.out.println(u.getEmail());
        System.out.println(u.getPassword());
        for (User.Role role : u.getRoles()) {
            System.out.println(role);
        }
    }
}
