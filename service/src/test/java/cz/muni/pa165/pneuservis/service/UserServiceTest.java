package cz.muni.pa165.pneuservis.service;

import cz.muni.pa165.pneuservis.config.TestConfiguration;
import cz.muni.pa165.pneuservis.persistence.domain.Order;
import cz.muni.pa165.pneuservis.persistence.domain.Tire;
import cz.muni.pa165.pneuservis.persistence.domain.User;
import cz.muni.pa165.pneuservis.persistence.enums.OrderState;
import cz.muni.pa165.pneuservis.persistence.enums.Role;
import cz.muni.pa165.pneuservis.persistence.repository.OrderRepository;
import cz.muni.pa165.pneuservis.persistence.repository.UserRepository;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Michal Krajcovic <mkrajcovic@mail.muni.cz>
 */
@ContextConfiguration(classes = TestConfiguration.class)
public class UserServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @Autowired
    @InjectMocks
    private UserService userService;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindUsersWithOrdersLastSevenDays() {
        User user = createUser("test@test.com");
        Order order = createOrder(user);

        when(orderRepository.findByDateCreatedBetween(any(Date.class), any(Date.class))).thenReturn(Collections.singletonList(order));

        Set<User> usersWithOrdersLastSevenDays = userService.findUsersWithOrdersLastSevenDays();

        Assert.assertTrue(usersWithOrdersLastSevenDays.contains(user));
    }

    private User createUser(String email) {
        User user = new User();
        user.setEmail(email);
        user.setName("Customer 5430");
        user.setPassword("cGFzc3dvcmQ=");
        user.setRoles(Collections.singletonList(Role.CUSTOMER));
        return user;
    }

    private Order createOrder(User user) {
        Order order = new Order();
        order.setUser(user);
        order.setAddress("Botanicka 68, 60200 Brno");
        order.setPhone("+420 156 123 749");
        order.setPrice(new BigDecimal(3000));
        order.setTire(new Tire());
        order.setTireQuantity(4);
        order.setDateCreated(Calendar.getInstance().getTime());
        order.setState(OrderState.RECEIVED);
        return order;
    }


}
