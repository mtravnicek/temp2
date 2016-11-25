package cz.muni.pa165.pneuservis.api.facade;

import cz.muni.pa165.pneuservis.api.dto.UserDTO;

import java.util.List;

/**
 * @author Michal Krajcovic <mkrajcovic@mail.muni.cz>
 */
public interface UserFacade {
    /**
     * Saves a given UserDTO. Use the returned instance for further operations
     * as the save operation might have changed the entity instance completely.
     *
     * @param user UserDTO to save
     * @return the saved UserDTO
     */
    UserDTO save(UserDTO user);

    /**
     * Retrieves a UserDTO by its id.
     *
     * @param id must not be null.
     * @return the UserDTO with the given id or null if none found
     */
    UserDTO findOne(Long id);

    /**
     * Retrieves a UserDTO by its email.
     *
     * @param email email to search by
     * @return the UserDTO with the given email or null if none found
     */
    UserDTO findByEmail(String email);

    /**
     * Returns all instances of the UserDTO.
     *
     * @return all entities
     */
    List<UserDTO> findAll();

    /**
     * Deletes the UserDTO with the given id.
     *
     * @param id - must not be null.
     */
    void delete(Long id);

    /**
     * Returns all UserDTOs which created an Order in last seven days
     *
     * @return List of entities
     */
    List<UserDTO> findUsersWithOrdersLastSevenDays();
}
