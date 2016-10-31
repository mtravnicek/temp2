package cz.muni.pa165.pneuservis.backend.service;

import cz.muni.pa165.pneuservis.backend.domain.Tire;
import cz.muni.pa165.pneuservis.backend.domain.User;
import cz.muni.pa165.pneuservis.backend.enums.Role;
import cz.muni.pa165.pneuservis.backend.enums.TireType;
import cz.muni.pa165.pneuservis.backend.repository.TireRepository;
import cz.muni.pa165.pneuservis.backend.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by Peter on 10/19/2016.
 */
@Named
public class HelloWorld {

    @Inject
    private UserRepository userDao;

    @Inject
    private TireRepository tireRepo;

    public void insertUser() {
        User user = new User();
        user.setEmail("mich.krajcovic@gmail.com");
        user.setName("Michal Krajčovič");
        user.setPassword("B1C788ABAC15390DE987AD17B65AC73C9B475D428A51F245C645A442FDDD078B");
        user.setRoles(Arrays.asList(Role.ADMIN, Role.CUSTOMER));

        userDao.save(user);
    }

    @Transactional
    public void getUser() {
        User u = userDao.findAll().get(0);
        System.out.println(u.getName());
        System.out.println(u.getEmail());
        System.out.println(u.getPassword());
        for (Role role : u.getRoles()) {
            System.out.println(role);
        }
    }

    public void createTire() {
        Tire t = new Tire();
        t.setId(1L);
        t.setName("TestPneu");
        t.setTireType(TireType.AllSeason_Passenger);
        t.setSize("235R19");
        t.setManufacturer("Dunlop");
        t.setVehicleType("Neviem");
        t.setValue(new BigDecimal(10.0));
        tireRepo.save(t);
    }

    public void testTire(){
        tireRepo.findAll().forEach(e -> System.out.println(e.toString()));
    }
}
