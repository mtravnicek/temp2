package cz.muni.pa165.pneuservis.service;

import cz.muni.pa165.pneuservis.persistence.domain.AdditionalService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by peter on 11/18/16.
 */
public interface AdditionalServiceService {
    AdditionalService save(AdditionalService service);
    AdditionalService findOne(Long id);
    List<AdditionalService> findAll();
    List<AdditionalService> findByNameContaining(String name);
    void delete(Long id);
}
