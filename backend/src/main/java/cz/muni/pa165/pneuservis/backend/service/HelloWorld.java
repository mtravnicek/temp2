package cz.muni.pa165.pneuservis.backend.service;

import cz.muni.pa165.pneuservis.backend.domain.Car;
import cz.muni.pa165.pneuservis.backend.repository.CarRepository;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Peter on 10/19/2016.
 */
@Named
public class HelloWorld {

  @Inject
  private CarRepository repository;

  public void sayHello() {
    System.out.println("COOL");
    Car c = new Car();
    c.setTest("test");
    repository.save(c);

    repository.findAll().forEach(e -> System.out.println("CARR" + e.getId() + " " + e.getTest()));
  }
}
