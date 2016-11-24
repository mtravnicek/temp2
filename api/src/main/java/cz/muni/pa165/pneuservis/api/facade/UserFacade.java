package cz.muni.pa165.pneuservis.api.facade;

import cz.muni.pa165.pneuservis.api.dto.UserDTO;

import java.util.List;

/**
 * @author Michal Krajcovic <mkrajcovic@mail.muni.cz>
 */
public interface UserFacade {
    UserDTO save(UserDTO user);

    UserDTO findOne(Long id);

    UserDTO findByEmail(String email);

    List<UserDTO> findAll();

    void delete(Long id);

    List<UserDTO> findUsersWithOrdersLastSevenDays();
}
