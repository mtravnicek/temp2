package cz.muni.pa165.pneuservis.persistence.repository;

import cz.muni.pa165.pneuservis.persistence.domain.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by peter on 10/31/16.
 */
public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, Long> {
    public List<AdditionalService> findByNameContaining(String name);
}
