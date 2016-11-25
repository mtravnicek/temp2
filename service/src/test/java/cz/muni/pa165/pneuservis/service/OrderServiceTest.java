package cz.muni.pa165.pneuservis.service;

import cz.muni.pa165.pneuservis.persistence.domain.Order;
import cz.muni.pa165.pneuservis.persistence.domain.Tire;
import cz.muni.pa165.pneuservis.persistence.domain.User;
import cz.muni.pa165.pneuservis.persistence.enums.OrderState;
import cz.muni.pa165.pneuservis.persistence.enums.Role;
import cz.muni.pa165.pneuservis.persistence.enums.TireType;
import cz.muni.pa165.pneuservis.persistence.repository.OrderRepository;
import cz.muni.pa165.pneuservis.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * @author Martin Spisiak <spisiak@mail.muni.cz> on 25/11/2016.
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class OrderServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private OrderRepository orderRepository;

    @Inject
    @InjectMocks
    private OrderService orderService;

    private Order order1;
    private Order order2;
    private Order order3;
    private Order invalidOrder;

    @BeforeClass
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void createOrder() {
        createOrder1();
        createOrder2();
        createOrder3();
        createInvalidOrder();
    }

    @Test
    public void testSaveOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(order1);

        Order testOrder = orderService.save(order1);

        Assert.assertEquals(testOrder, order1);
    }

    @Test
    public void testFindOneOrder() {
        when(orderRepository.findOne(eq(1L))).thenReturn(order1);
        when(orderRepository.findOne(eq(2L))).thenReturn(order2);
        when(orderRepository.findOne(eq(3L))).thenReturn(order3);

        Order testOrder1 = orderService.findOne(order1.getId());
        Assert.assertEquals(testOrder1, order1);

        Order testOrder2 = orderService.findOne(order2.getId());
        Assert.assertEquals(testOrder2, order2);

        Order testOrder3 = orderService.findOne(order3.getId());
        Assert.assertEquals(testOrder3, order3);
    }

    @Test
    public void testFindAllOrders() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2, order3));

        List<Order> allOrders = Arrays.asList(order1, order2, order3);
        List<Order> foundOrders = orderService.findAll();

        Assert.assertEquals(allOrders.size(), foundOrders.size());
        foundOrders.forEach(order -> {
            Assert.assertNotNull(order.getId());
            Assert.assertTrue(allOrders.contains(order));
        });
    }

    @Test
    public void testFindNonExistingOrder() {
        long nonExistingOrderId = 4L;
        when(orderRepository.findOne(eq(nonExistingOrderId))).thenReturn(null);

        Order testOrder = orderService.findOne(nonExistingOrderId);
        Assert.assertNull(testOrder);
    }

    @Test(expectedExceptions = {DataAccessException.class})
    public void testSaveInvalidOrder() {
        when(orderRepository.save(invalidOrder)).thenThrow(new DataAccessException("User & Tire not specified") {});

        orderService.save(invalidOrder);
    }

    @Test
    public void testDeleteOrder() {
        when(orderRepository.findOne(eq(order1.getId()))).thenReturn(order1).thenReturn(null);
        Assert.assertNotNull(orderService.findOne(order1.getId()));
        orderService.delete(order1.getId());
        Assert.assertNull(orderService.findOne(order1.getId()));
    }

    @Test(expectedExceptions = {DataAccessException.class})
    public void testDeleteNonExistingOrder() {
        long nonExistingOrder = 4L;
        doThrow(new DataAccessException("User & Tire not specified") {}).when(orderRepository).delete(eq(nonExistingOrder));

        orderService.delete(nonExistingOrder);
    }

    private void createOrder1(){
        User user = new User();
        user.setEmail("customer1@mail.muni.cz");
        user.setName("Customer 1");
        user.setPassword("cGFzc3dvcmQ=");
        user.setRoles(Collections.singletonList(Role.CUSTOMER));

        Tire tire = new Tire();
        tire.setName("Pneumatika 3000");
        tire.setManufacturer("Michelin");
        tire.setSize("21");
        tire.setTireType(TireType.AllSeason_Passenger);
        tire.setPrice(new BigDecimal("3000"));
        tire.setVehicleType("velke modre");

        order1 = new Order();
        order1.setId(1L);
        order1.setUser(user);
        order1.setAddress("Botanicka 68, 60200 Brno");
        order1.setPhone("+420 156 123 749");
        order1.setPrice(new BigDecimal(3000));
        order1.setTire(tire);
        order1.setTireQuantity(4);
        order1.setDateCreated(Calendar.getInstance().getTime());
        order1.setState(OrderState.RECEIVED);
    }

    private void createOrder2(){
        User user = new User();
        user.setEmail("customer2@gmail.com");
        user.setName("Customer 2");
        user.setPassword("666666");
        user.setRoles(Collections.singletonList(Role.CUSTOMER));

        Tire tire = new Tire();
        tire.setName("Sport");
        tire.setManufacturer("Dunlop");
        tire.setSize("19");
        tire.setTireType(TireType.Summer_Passenger);
        tire.setPrice(new BigDecimal("5000"));
        tire.setVehicleType("sportove cierne");

        order2 = new Order();
        order2.setId(2L);
        order2.setUser(user);
        order2.setAddress("Nadrazni 9, 60200 Brno");
        order2.setPhone("+420 666 666 666");
        order2.setPrice(new BigDecimal(20000));
        order2.setTire(tire);
        order2.setTireQuantity(4);
        order2.setDateCreated(Calendar.getInstance().getTime());
        order2.setState(OrderState.DONE);
    }

    private void createOrder3(){
        User user = new User();
        user.setEmail("customer3@seznam.cz");
        user.setName("Customer 3");
        user.setPassword("g2qc65x7");
        user.setRoles(Collections.singletonList(Role.CUSTOMER));

        Tire tire = new Tire();
        tire.setName("Work");
        tire.setManufacturer("Dunlop");
        tire.setSize("20");
        tire.setTireType(TireType.OffRoad_LightTruck);
        tire.setPrice(new BigDecimal("4000"));
        tire.setVehicleType("pickup");

        order3 = new Order();
        order3.setId(3L);
        order3.setUser(user);
        order3.setAddress("Nadrazni 7, 60200 Brno");
        order3.setPhone("+420 789 123 465");
        order3.setPrice(new BigDecimal(16000));
        order3.setTire(tire);
        order3.setTireQuantity(4);
        order3.setDateCreated(Calendar.getInstance().getTime());
        order3.setState(OrderState.RECEIVED);
    }

    private void createInvalidOrder(){
        invalidOrder = new Order();
        invalidOrder.setAddress("Neznama adresa");
        invalidOrder.setPhone("+420 999 999 999");
        invalidOrder.setPrice(new BigDecimal(0));
        invalidOrder.setTireQuantity(0);
        invalidOrder.setDateCreated(Calendar.getInstance().getTime());
        invalidOrder.setState(OrderState.CANCELED);
    }
}
