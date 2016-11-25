package cz.muni.pa165.pneuservis.service;

import cz.muni.pa165.pneuservis.persistence.domain.Tire;
import cz.muni.pa165.pneuservis.persistence.enums.TireType;

import java.util.List;

/**
 * @author Martin Spisiak <spisiak@mail.muni.cz> on 23/11/2016.
 */
public interface TireService {
    /**
     * Saves a given Tire. Use the returned instance for further operations
     * as the save operation might have changed the entity instance completely.
     *
     * @param tire Tire to save
     * @return the saved Tire
     */
    Tire save(Tire tire);

    /**
     * Retrieves a Tire by its id.
     *
     * @param id must not be null.
     * @return the Tire with the given id or null if none found
     */
    Tire findOne(Long id);

    /**
     * Returns all instances of the Tire.
     *
     * @return all entities
     */
    List<Tire> findAll();

    /**
     * Returns all instances of the Tire of given type.
     *
     * @param tireType must not be null
     * @return all entities of given type
     */
    List<Tire> findByTireType(TireType tireType);

    /**
     * Return three best selling Tires
     *
     * @return List of three most selling Tires
     */
    List<Tire> findThreeBestSelling();

    /**
     * Deletes the Tire with the given id.
     *
     * @param id - must not be null.
     */
    void delete(Long id);
}
