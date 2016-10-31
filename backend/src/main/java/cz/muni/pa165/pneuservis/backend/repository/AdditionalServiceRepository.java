package cz.muni.pa165.pneuservis.backend.repository;

import cz.muni.pa165.pneuservis.backend.domain.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by peter on 10/31/16.
 */
public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, Long> {
}
