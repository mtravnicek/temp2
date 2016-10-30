package cz.muni.pa165.pneuservis.backend.test.dao;

import cz.muni.pa165.pneuservis.backend.domain.User;
import cz.muni.pa165.pneuservis.backend.enums.Role;
import cz.muni.pa165.pneuservis.backend.repository.UserRepository;
import cz.muni.pa165.pneuservis.backend.test.config.DaoTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * @author Michal Krajcovic <mkrajcovic@mail.muni.cz>
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(classes = DaoTestConfig.class)
public class UserDaoTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testPersist() {
        User user = new User();
        user.setEmail("mich.krajcovic@gmail.com");
        user.setName("Michal Krajčovič");
        user.setPassword("B1C788ABAC15390DE987AD17B65AC73C9B475D428A51F245C645A442FDDD078B");
        user.setRoles(Arrays.asList(Role.ADMIN, Role.CUSTOMER));

        userRepository.save(user);

        Assert.assertNotNull(user.getId());
    }
}
