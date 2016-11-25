package cz.muni.pa165.pneuservis.service;

import cz.muni.pa165.pneuservis.persistence.domain.User;

import java.util.List;

/**
 * @author Michal Krajcovic <mkrajcovic@mail.muni.cz>
 */
public interface UserService {
    /**
     * Saves a given User. Use the returned instance for further operations
     * as the save operation might have changed the entity instance completely.
     *
     * @param user User to save
     * @return the saved User
     */
    User save(User user);

    /**
     * Retrieves a User by its id.
     *
     * @param id must not be null.
     * @return the User with the given id or null if none found
     */
    User findOne(Long id);

    /**
     * Retrieves a User by its email.
     *
     * @param email email to search by
     * @return the User with the given email or null if none found
     */
    User findByEmail(String email);

    /**
     * Returns all instances of the User.
     *
     * @return all entities
     */
    List<User> findAll();

    /**
     * Deletes the User with the given id.
     *
     * @param id - must not be null.
     */
    void delete(Long id);

    /**
     * Returns all Users which created an Order in last seven days
     *
     * @return List of users
     */
    List<User> findUsersWithOrdersLastSevenDays();
}
