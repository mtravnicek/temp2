package cz.muni.pa165.pneuservis.api.facade;

import cz.muni.pa165.pneuservis.api.dto.TireDTO;
import cz.muni.pa165.pneuservis.api.dto.TireTypeDTO;

import java.util.List;

/**
 * @author Martin Spisiak <spisiak@mail.muni.cz> on 23/11/2016.
 */
public interface TireFacade {
    /**
     * Saves a given TireDTO. Use the returned instance for further operations
     * as the save operation might have changed the entity instance completely.
     *
     * @param tireDTO TireDTO to save
     * @return the saved TireDTO
     */
    TireDTO save(TireDTO tireDTO);

    /**
     * Retrieves a TireDTO by its id.
     *
     * @param id must not be null.
     * @return the TireDTO with the given id or null if none found
     */
    TireDTO findOne(Long id);

    /**
     * Returns all instances of the TireDTO.
     *
     * @return all entities
     */
    List<TireDTO> findAll();

    /**
     * Returns all instances of the TireDTO of given type.
     *
     * @param tireType must not be null
     * @return all entities of given type
     */
    List<TireDTO> findByTireType(TireTypeDTO tireType);

    /**
     * Return three best selling TireDTOs
     *
     * @return List of three most selling Tires
     */
    List<TireDTO> findThreeBestSelling();

    /**
     * Deletes the TireDTO with the given id.
     *
     * @param id - must not be null.
     */
    void delete(Long id);
}
