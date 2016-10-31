package cz.muni.pa165.pneuservis.backend.repository;

import cz.muni.pa165.pneuservis.backend.domain.Tire;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Martin Spisiak <spisiak@mail.muni.cz> on 30/10/2016.
 */
public interface TireRepository extends JpaRepository<Tire, Long> {
}
