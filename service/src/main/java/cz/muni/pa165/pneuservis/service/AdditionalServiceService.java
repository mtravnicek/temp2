package cz.muni.pa165.pneuservis.service;

import cz.muni.pa165.pneuservis.persistence.domain.AdditionalService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by peter on 11/18/16.
 */
public interface AdditionalServiceService {
    /**
     * Saves a given AdditionalService. Use the returned instance for further operations
     * as the save operation might have changed the entity instance completely.
     *
     * @param service AdditionalService to save
     * @return the saved AdditionalService
     */
    AdditionalService save(AdditionalService service);

    /**
     * Retrieves an AdditionalService by its id.
     *
     * @param id must not be null.
     * @return the AdditionalService with the given id or null if none found
     */
    AdditionalService findOne(Long id);

    /**
     * Returns all instances of the AdditionalService.
     *
     * @return all entities
     */
    List<AdditionalService> findAll();

    /**
     * Returns all instances of the AdditionalService, which names contains given name
     *
     * @param name to search by
     * @return all entities containing given name in name
     */
    List<AdditionalService> findByNameContaining(String name);

    /**
     * Deletes the AdditionalService with the given id.
     *
     * @param id - must not be null.
     */
    void delete(Long id);
}
