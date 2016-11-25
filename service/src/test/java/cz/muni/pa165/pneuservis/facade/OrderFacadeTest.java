package cz.muni.pa165.pneuservis.facade;

import cz.muni.pa165.pneuservis.api.dto.*;
import cz.muni.pa165.pneuservis.api.facade.OrderFacade;
import cz.muni.pa165.pneuservis.persistence.domain.Order;
import cz.muni.pa165.pneuservis.persistence.domain.Tire;
import cz.muni.pa165.pneuservis.persistence.domain.User;
import cz.muni.pa165.pneuservis.persistence.enums.OrderState;
import cz.muni.pa165.pneuservis.persistence.enums.Role;
import cz.muni.pa165.pneuservis.persistence.enums.TireType;
import cz.muni.pa165.pneuservis.service.BeanMappingService;
import cz.muni.pa165.pneuservis.service.OrderService;
import cz.muni.pa165.pneuservis.service.config.ServiceConfiguration;
import cz.muni.pa165.pneuservis.service.util.Utils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Martin Spisiak <spisiak@mail.muni.cz> on 25/11/2016.
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class OrderFacadeTest extends AbstractTestNGSpringContextTests {
    @Mock
    private OrderService orderService;

    @Mock
    private BeanMappingService mapper;

    @Inject
    @InjectMocks
    private OrderFacade orderFacade;

    private OrderDTO orderDTO;
    private Order order;

    @BeforeClass
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        OrderFacade unwrappedProxy = (OrderFacade) Utils.unwrapProxy(orderFacade);
        ReflectionTestUtils.setField(unwrappedProxy, "orderService", orderService);
    }

    @BeforeMethod
    public void initDTO(){
        createOrderDTO();
        createOrder();

        List<Order> orders = Collections.singletonList(order);
        List<OrderDTO> orderDTOs = Collections.singletonList(orderDTO);

        when(mapper.mapTo(order, OrderDTO.class)).thenReturn(orderDTO);
        when(mapper.mapTo(orders, OrderDTO.class)).thenReturn(orderDTOs);
        when(mapper.mapTo(orderDTO, Order.class)).thenReturn(order);
        when(mapper.mapTo(orderDTOs, Order.class)).thenReturn(orders);
    }

    @Test
    public void testSaveOrder(){
        orderFacade.save(orderDTO);
        verify(orderService).save(order);
    }

    @Test
    public void testFindOneOrder(){
        orderFacade.findOne(1L);
        verify(orderService).findOne(1L);
    }

    @Test
    public void testFindAllOrders(){
        orderFacade.findAll();
        verify(orderService).findAll();
    }

    @Test
    public void testDeleteOrder(){
        orderFacade.delete(1L);

        verify(orderService).delete(1L);
    }

    private void createOrderDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("customer1@mail.muni.cz");
        userDTO.setName("Customer 1");
        userDTO.setPassword("cGFzc3dvcmQ=");
        userDTO.setRoles(Collections.singletonList(RoleDTO.CUSTOMER));

        TireDTO tireDTO = new TireDTO();
        tireDTO.setName("Pneumatika 3000");
        tireDTO.setManufacturer("Michelin");
        tireDTO.setSize("21");
        tireDTO.setTireType(TireTypeDTO.AllSeason_Passenger);
        tireDTO.setPrice(new BigDecimal("3000"));
        tireDTO.setVehicleType("velke modre");

        orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setUser(userDTO);
        orderDTO.setAddress("Botanicka 68, 60200 Brno");
        orderDTO.setPhone("+420 156 123 749");
        orderDTO.setPrice(new BigDecimal(3000));
        orderDTO.setTire(tireDTO);
        orderDTO.setTireQuantity(4);
        orderDTO.setDateCreated(Calendar.getInstance().getTime());
        orderDTO.setState(OrderStateDTO.RECEIVED);
    }

    private void createOrder(){
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

        order = new Order();
        order.setId(1L);
        order.setUser(user);
        order.setAddress("Botanicka 68, 60200 Brno");
        order.setPhone("+420 156 123 749");
        order.setPrice(new BigDecimal(3000));
        order.setTire(tire);
        order.setTireQuantity(4);
        order.setDateCreated(Calendar.getInstance().getTime());
        order.setState(OrderState.RECEIVED);
    }
}
