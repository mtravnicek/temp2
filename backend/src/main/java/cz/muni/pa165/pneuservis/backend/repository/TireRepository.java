package cz.muni.pa165.pneuservis.backend.repository;

import cz.muni.pa165.pneuservis.backend.domain.Tire;
import cz.muni.pa165.pneuservis.backend.enums.TireType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Martin Spisiak <spisiak@mail.muni.cz> on 30/10/2016.
 */
public interface TireRepository extends JpaRepository<Tire, Long> {
    List<Tire> findByName(String name);
    List<Tire> findByTireType(TireType tireType);
    List<Tire> findByVehicleType(String vehicleType);
}
