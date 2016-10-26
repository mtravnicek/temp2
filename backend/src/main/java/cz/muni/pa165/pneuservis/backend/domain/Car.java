package cz.muni.pa165.pneuservis.backend.domain;

import javax.persistence.Entity;

/**
 * Created by Peter on 10/21/2016.
 */
@Entity
public class Car extends AbstractEntity {

  public Car() {}

  private String test;

  public String getTest() {
    return test;
  }

  public void setTest(String test) {
    this.test = test;
  }
}
