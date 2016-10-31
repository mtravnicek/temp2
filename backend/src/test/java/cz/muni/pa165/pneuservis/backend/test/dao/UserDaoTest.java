package cz.muni.pa165.pneuservis.backend.test.dao;

import cz.muni.pa165.pneuservis.backend.domain.User;
import cz.muni.pa165.pneuservis.backend.enums.Role;
import cz.muni.pa165.pneuservis.backend.repository.UserRepository;
import cz.muni.pa165.pneuservis.backend.test.config.DaoTestConfig;
import org.aspectj.bridge.IMessageHandler;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.jws.soap.SOAPBinding;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Peter on 10/30/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoTestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserDaoTest {

    @Inject
    private UserRepository userRepository;

    @Test
    public void persistSingleUser() {
        User user = createAdmin();
        userRepository.save(user);
        List<User> users = userRepository.findAll();
        Assert.assertEquals("Number of users", 1, users.size());
        compareUsers(user, users.get(0));
    }

    @Test
    public void persistMultipleUsers() {
        User admin = createAdmin();
        User customer = createCustomer();
        userRepository.save(Arrays.asList(admin, customer));
        Assert.assertEquals("Number of users", 2, userRepository.findAll().size());
        User foundAdmin = userRepository.findOne(admin.getId());
        compareUsers(admin, foundAdmin);
        User foundCustomer = userRepository.findOne(customer.getId());
        compareUsers(customer, foundCustomer);
    }

    @Test
    public void persistWithoutEmail() {
        User user = new User();
        user.setName("Only name");
        try {
            userRepository.save(user);
            Assert.fail("Cannot persist user without email");
        } catch (Exception e) {
            String errMsg = "Could not commit JPA transaction; nested exception is javax.persistence.RollbackException: Error while committing the transaction";
            Assert.assertEquals("Wrong exception message", errMsg, e.getMessage());
        }
    }

    @Test
    public void persistWithoutName() {
        User user = new User();
        user.setEmail("admin@pneuservis.muni.cz");
        try {
            userRepository.save(user);
            Assert.fail("Cannot persist user without name");
        } catch (Exception e) {
            String errMsg = "Could not commit JPA transaction; nested exception is javax.persistence.RollbackException: Error while committing the transaction";
            Assert.assertEquals("Wrong exception message", errMsg, e.getMessage());
        }
    }

    @Test
    public void persistWithWrongEmail() {
        User user = new User();
        user.setName("Admin admin");
        user.setEmail("aaa");
        try {
            userRepository.save(user);
            Assert.fail("Cannot persist user with invalid email");
        } catch (Exception e) {
            String errMsg = "Could not commit JPA transaction; nested exception is javax.persistence.RollbackException: Error while committing the transaction";
            Assert.assertEquals("Wrong exception message", errMsg, e.getMessage());
        }
    }

    @Test
    public void updateUser() {
        User savedUser = userRepository.save(createAdmin());
        savedUser.setEmail("another@pneuservis.muni.cz");
        savedUser.setName("Another name");
        savedUser.setPassword("SIMPLE_PASSWORD");
        User updatedUser = userRepository.save(savedUser);
        compareUsers(savedUser, updatedUser);
    }

    @Test
    public void deleteUser() {
        User user = createAdmin();
        userRepository.save(user);
        Assert.assertEquals("Number of users", 1, userRepository.findAll().size());
        userRepository.delete(user.getId());
        Assert.assertEquals("Number of users", 0, userRepository.findAll().size());
    }

    private User createAdmin() {
        User user = new User();
        user.setEmail("admin@pneuservis.muni.cz");
        user.setName("Admin");
        user.setPassword("B1C788ABAC15390DE987AD17B65AC73C9B475D428A51F245C645A442FDDD078B");
        user.setRoles(Arrays.asList(Role.ADMIN, Role.CUSTOMER));
        return user;
    }

    private User createCustomer() {
        User user = new User();
        user.setEmail("customer@mail.muni.cz");
        user.setName("Customer 5430");
        user.setPassword("cGFzc3dvcmQ=");
        user.setRoles(Collections.singletonList(Role.CUSTOMER));
        return user;
    }

    private void compareUsers(User expected, User actual) {
        Assert.assertNotNull("User must have an id", actual.getId());
        Assert.assertEquals("ID must be the same", expected.getId(), actual.getId());
        Assert.assertEquals("Email must be the same", expected.getEmail(), actual.getEmail());
        Assert.assertEquals("Password must be the same", expected.getPassword(), actual.getPassword());
        Assert.assertTrue("User roles must be same", expected.getRoles().containsAll(actual.getRoles()));
    }
}
