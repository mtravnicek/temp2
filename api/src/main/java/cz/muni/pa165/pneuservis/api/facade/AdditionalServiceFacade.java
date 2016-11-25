package cz.muni.pa165.pneuservis.api.facade;

import cz.muni.pa165.pneuservis.api.dto.AdditionalServiceDTO;

import java.util.List;

/**
 * Created by peter on 11/18/16.
 */
public interface AdditionalServiceFacade {
    /**
     * Saves a given AdditionalServiceDTO. Use the returned instance for further operations
     * as the save operation might have changed the entity instance completely.
     *
     * @param dto AdditionalServiceDTO to save
     * @return the saved AdditionalServiceDTO
     */
    AdditionalServiceDTO save(AdditionalServiceDTO dto);

    /**
     * Retrieves an AdditionalServiceDTO by its id.
     *
     * @param id must not be null.
     * @return the AdditionalServiceDTO with the given id or null if none found
     */
    AdditionalServiceDTO findOne(Long id);

    /**
     * Returns all instances of the AdditionalServiceDTO.
     *
     * @return all entities
     */
    List<AdditionalServiceDTO> findAll();

    /**
     * Returns all instances of the AdditionalServiceDTO, which names contains given name
     *
     * @param name to search by
     * @return all entities containing given name in name
     */
    List<AdditionalServiceDTO> findByNameContaining(String name);

    /**
     * Deletes the AdditionalServiceDTO with the given id.
     *
     * @param id - must not be null.
     */
    void delete(Long id);
}
