package cz.muni.pa165.pneuservis.service.facade;

import cz.muni.pa165.pneuservis.api.dto.UserDTO;
import cz.muni.pa165.pneuservis.api.facade.UserFacade;
import cz.muni.pa165.pneuservis.persistence.domain.User;
import cz.muni.pa165.pneuservis.persistence.enums.Role;
import cz.muni.pa165.pneuservis.service.BeanMappingService;
import cz.muni.pa165.pneuservis.service.UserService;
import cz.muni.pa165.pneuservis.service.config.ServiceConfiguration;
import cz.muni.pa165.pneuservis.service.util.Utils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * @author Michal Krajcovic <mkrajcovic@mail.muni.cz>
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserFacadeTest extends AbstractTestNGSpringContextTests {
    @Mock
    private UserService service;

    @Inject
    BeanMappingService mappingService;

    @Inject
    @InjectMocks
    private UserFacade userFacade;

    private User admin;
    private User customer;
    private User guest;
    private UserDTO adminDTO;
    private UserDTO customerDTO;
    private UserDTO guestDTO;

    @BeforeClass
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        UserFacade unwrappedProxy = (UserFacade) Utils.unwrapProxy(userFacade);
        ReflectionTestUtils.setField(unwrappedProxy, "service", service);
    }

    @BeforeMethod
    public void createUsers() {
        createAdmin();
        createCustomer();
        createGuest();
    }

    @Test
    public void testFindAllUsers() {
        when(service.findAll()).thenReturn(Arrays.asList(admin, customer, guest));

        List<UserDTO> allUsers = Arrays.asList(adminDTO, customerDTO, guestDTO);
        List<UserDTO> foundUsers = userFacade.findAll();
        Assert.assertEquals(allUsers.size(), foundUsers.size());
        foundUsers.forEach(user -> {
            Assert.assertNotNull(user.getId());
            Assert.assertTrue(allUsers.contains(user));
        });
    }

    @Test
    public void testFindOneUser() {
        when(service.findOne(eq(1L))).thenReturn(admin);
        when(service.findOne(eq(2L))).thenReturn(customer);
        when(service.findOne(eq(3L))).thenReturn(guest);

        UserDTO u = userFacade.findOne(adminDTO.getId());
        Assert.assertEquals(u, adminDTO);

        u = userFacade.findOne(customerDTO.getId());
        Assert.assertEquals(u, customerDTO);

        u = userFacade.findOne(guestDTO.getId());
        Assert.assertEquals(u, guestDTO);
    }

    @Test
    public void testSaveUser() {
        when(service.save(any(User.class))).thenReturn(admin);
        UserDTO u = userFacade.save(adminDTO);
        Assert.assertEquals(adminDTO, u);
    }

    @Test
    public void testFindUserByEmail() {
        when(service.findByEmail(eq(admin.getEmail()))).thenReturn(admin);
        UserDTO u = userFacade.findByEmail(adminDTO.getEmail());
        Assert.assertEquals(adminDTO, u);
    }

    @Test
    public void testDeleteUser() {
        when(service.findOne(eq(admin.getId()))).thenReturn(admin).thenReturn(null);
        Assert.assertNotNull(userFacade.findOne(adminDTO.getId()));
        userFacade.delete(admin.getId());
        Assert.assertNull(userFacade.findOne(admin.getId()));
    }

    @Test
    public void testFindUsersWithOrdersLastSevenDays() {
        when(service.findUsersWithOrdersLastSevenDays()).thenReturn(Arrays.asList(admin, customer));
        List<UserDTO> expectedUsers = Arrays.asList(adminDTO, customerDTO);
        List<UserDTO> foundUsers = userFacade.findUsersWithOrdersLastSevenDays();

        Assert.assertEquals(foundUsers.size(), expectedUsers.size());
        Assert.assertTrue(expectedUsers.containsAll(foundUsers));
    }

    private void createAdmin() {
        admin = new User();
        admin.setId(1L);
        admin.setEmail("admin@admin.com");
        admin.setName("Admin");
        admin.setPassword("password");
        admin.setRoles(Collections.singletonList(Role.ADMIN));
        adminDTO = mappingService.mapTo(admin, UserDTO.class);
    }

    private void createCustomer() {
        customer = new User();
        customer.setId(2L);
        customer.setEmail("customer@customer.com");
        customer.setName("Customer");
        customer.setPassword("l;asdkklsadj");
        customer.setRoles(Collections.singletonList(Role.CUSTOMER));
        customerDTO = mappingService.mapTo(customer, UserDTO.class);
    }

    private void createGuest() {
        guest = new User();
        guest.setId(3L);
        guest.setEmail("guest@guest.com");
        guest.setName("Guest");
        guest.setPassword("iuogaopdf4asd");
        guestDTO = mappingService.mapTo(guest, UserDTO.class);
    }
}
