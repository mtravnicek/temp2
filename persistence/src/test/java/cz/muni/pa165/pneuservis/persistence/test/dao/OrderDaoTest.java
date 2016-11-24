package cz.muni.pa165.pneuservis.persistence.test.dao;

import cz.muni.pa165.pneuservis.persistence.domain.Order;
import cz.muni.pa165.pneuservis.persistence.domain.Tire;
import cz.muni.pa165.pneuservis.persistence.domain.User;
import cz.muni.pa165.pneuservis.persistence.enums.OrderState;
import cz.muni.pa165.pneuservis.persistence.enums.Role;
import cz.muni.pa165.pneuservis.persistence.enums.TireType;
import cz.muni.pa165.pneuservis.persistence.repository.OrderRepository;
import cz.muni.pa165.pneuservis.persistence.repository.TireRepository;
import cz.muni.pa165.pneuservis.persistence.repository.UserRepository;
import cz.muni.pa165.pneuservis.persistence.test.config.DaoTestConfig;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.persistence.RollbackException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.isA;

/**
 * @author Martin Spisiak <spisiak@mail.muni.cz> on 31/10/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoTestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OrderDaoTest {

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private TireRepository tireRepository;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testCreate() {
        Order order = createOrder();

        orderRepository.save(order);

        Long id = order.getId();
        Assert.assertNotNull(id);

        Order result = orderRepository.findOne(id);

        User expectedCustomer = createCustomer();
        expectedCustomer.setId(result.getUser().getId());

        Tire expectedTire = createTire();
        expectedTire.setId(result.getTire().getId());

        Assert.assertEquals(expectedCustomer, result.getUser());
        Assert.assertEquals("Botanicka 68, 60200 Brno", result.getAddress());
        Assert.assertEquals("+420 156 123 749", result.getPhone());
        Assert.assertTrue(new BigDecimal("3000").compareTo(result.getPrice()) == 0);
        Assert.assertEquals(expectedTire, result.getTire());
        Assert.assertEquals(new Integer(4), result.getTireQuantity());
        Assert.assertEquals(OrderState.RECEIVED, result.getState());
    }

    @Test
    public void testCreateWithNullUser() {
        Order order = createOrder();
        order.setUser(null);

        exception.expectCause(isA(RollbackException.class));

        orderRepository.save(order);
    }

    @Test
    public void testCreateWithNullAddress() {
        Order order = createOrder();
        order.setAddress(null);

        exception.expectCause(isA(RollbackException.class));

        orderRepository.save(order);
    }

    @Test
    public void testCreateWithNullPhone() {
        Order order = createOrder();
        order.setPhone(null);

        exception.expectCause(isA(RollbackException.class));

        orderRepository.save(order);
    }

    @Test
    public void testCreateWithNullPrice() {
        Order order = createOrder();
        order.setPrice(null);

        exception.expectCause(isA(RollbackException.class));

        orderRepository.save(order);
    }

    @Test
    public void testCreateWithNullTire() {
        Order order = createOrder();
        order.setTire(null);

        exception.expectCause(isA(RollbackException.class));

        orderRepository.save(order);
    }

    @Test
    public void testCreateWithNullTireQuantity() {
        Order order = createOrder();
        order.setTireQuantity(null);

        exception.expectCause(isA(RollbackException.class));

        orderRepository.save(order);
    }

    @Test
    public void testCreateWithNullDate() {
        Order order = createOrder();
        order.setDateCreated(null);

        exception.expectCause(isA(RollbackException.class));

        orderRepository.save(order);
    }

    @Test
    public void testCreateWithNullOrderState() {
        Order order = createOrder();
        order.setState(null);

        exception.expectCause(isA(RollbackException.class));

        orderRepository.save(order);
    }

    @Test
    public void testUpdateOrder() {
        Order savedOrder = orderRepository.save(createOrder());
        savedOrder.setState(OrderState.DONE);
        Order updatedOrder = orderRepository.save(savedOrder);
        compareOrdersWithChangedState(updatedOrder, savedOrder);
    }

    @Test
    public void testDeleteOrder() {
        Order order = createOrder();
        orderRepository.save(order);
        Assert.assertEquals("Number of orders", 1, orderRepository.findAll().size());
        orderRepository.delete(order.getId());
        Assert.assertEquals("Number of orders", 0, orderRepository.findAll().size());
    }

    @Test
    public void testFindByCreatedDateBetween() {
        Order o1 = createOrder();
        User u2 = createCustomer();
        u2.setEmail("test@test.com");
        Order o2 = createOrder(u2);
        User u3 = createCustomer();
        u3.setEmail("test2@test.com");
        Order o3 = createOrder(u3);

        Calendar cal1 = Calendar.getInstance();
        cal1.set(2000, 11, 1);
        o1.setDateCreated(cal1.getTime());

        Calendar cal2 = Calendar.getInstance();
        cal2.set(2000, 11, 4);
        o2.setDateCreated(cal2.getTime());

        Calendar cal3 = Calendar.getInstance();
        cal3.set(2000, 11, 8);
        o3.setDateCreated(cal3.getTime());

        orderRepository.save(o1);
        orderRepository.save(o2);
        orderRepository.save(o3);

        Calendar start = Calendar.getInstance();
        start.set(2000, 11, 3);
        Calendar end = Calendar.getInstance();
        end.set(2000, 11, 9);

        List<Order> byDateCreatedBetween = orderRepository.findByDateCreatedBetween(start.getTime(), end.getTime());

        Assert.assertTrue(byDateCreatedBetween.contains(o2));
        Assert.assertTrue(byDateCreatedBetween.contains(o3));
        Assert.assertFalse(byDateCreatedBetween.contains(o1));
    }

    private User createCustomer() {
        User user = new User();
        user.setEmail("customer@mail.muni.cz");
        user.setName("Customer 5430");
        user.setPassword("cGFzc3dvcmQ=");
        user.setRoles(Collections.singletonList(Role.CUSTOMER));
        return user;
    }

    private Tire createTire() {
        Tire tire = new Tire();
        tire.setName("Pneumatika 3000");
        tire.setManufacturer("Michelin");
        tire.setSize("21");
        tire.setTireType(TireType.AllSeason_Passenger);
        tire.setPrice(new BigDecimal("3000"));
        tire.setVehicleType("velke modre");
        return tire;
    }

    private Order createOrder(){
        User customer = createCustomer();
        userRepository.save(customer);

        Tire tire = createTire();
        tireRepository.save(tire);

        Order order = new Order();
        order.setUser(customer);
        order.setAddress("Botanicka 68, 60200 Brno");
        order.setPhone("+420 156 123 749");
        order.setPrice(new BigDecimal(3000));
        order.setTire(tire);
        order.setTireQuantity(4);
        order.setDateCreated(Calendar.getInstance().getTime());
        order.setState(OrderState.RECEIVED);
        return order;
    }

    private Order createOrder(User customer){
        userRepository.save(customer);

        Tire tire = createTire();
        tireRepository.save(tire);

        Order order = new Order();
        order.setUser(customer);
        order.setAddress("Botanicka 68, 60200 Brno");
        order.setPhone("+420 156 123 749");
        order.setPrice(new BigDecimal(3000));
        order.setTire(tire);
        order.setTireQuantity(4);
        order.setDateCreated(Calendar.getInstance().getTime());
        order.setState(OrderState.RECEIVED);
        return order;
    }

    private void compareOrdersWithChangedState(Order expected, Order actual) {
        Assert.assertNotNull("Order must have an id", actual.getId());
        Assert.assertEquals("ID must be the same", expected.getId(), actual.getId());
        Assert.assertEquals("Users must be the same", expected.getUser(), actual.getUser());
        Assert.assertEquals("Addresses must be the same", expected.getAddress(), actual.getAddress());
        Assert.assertEquals("Phone numbers must be the same", expected.getPhone(), actual.getPhone());
        Assert.assertTrue(expected.getPrice().compareTo(actual.getPrice()) == 0);
        Assert.assertEquals("Tires must be the same", expected.getTire(), actual.getTire());
        Assert.assertEquals("Tire quantities must be the same", expected.getTireQuantity(), actual.getTireQuantity());
        Assert.assertEquals("Order states must be the same", expected.getState(), actual.getState());
    }
}
