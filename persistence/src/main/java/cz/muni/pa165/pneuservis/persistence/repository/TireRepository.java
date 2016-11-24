package cz.muni.pa165.pneuservis.persistence.repository;

import cz.muni.pa165.pneuservis.persistence.domain.Tire;
import cz.muni.pa165.pneuservis.persistence.enums.TireType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Martin Spisiak <spisiak@mail.muni.cz> on 30/10/2016.
 */
public interface TireRepository extends JpaRepository<Tire, Long> {
    List<Tire> findByTireType(TireType type);
}
