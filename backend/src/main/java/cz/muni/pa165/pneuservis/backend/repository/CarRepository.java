package cz.muni.pa165.pneuservis.backend.repository;

import cz.muni.pa165.pneuservis.backend.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Peter on 10/21/2016.
 */

public interface CarRepository extends JpaRepository<Car, Long> {
}
