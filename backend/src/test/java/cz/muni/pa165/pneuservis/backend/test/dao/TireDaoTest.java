package cz.muni.pa165.pneuservis.backend.test.dao;

import cz.muni.pa165.pneuservis.backend.domain.Tire;
import cz.muni.pa165.pneuservis.backend.enums.TireType;
import cz.muni.pa165.pneuservis.backend.repository.TireRepository;
import cz.muni.pa165.pneuservis.backend.test.config.DaoTestConfig;
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

import static org.hamcrest.CoreMatchers.isA;

/**
 * @author Michal Krajcovic <mkrajcovic@mail.muni.cz>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoTestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TireDaoTest {

    @Inject
    private TireRepository tireRepository;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testCreate() {
        Tire tire = newTire();

        tireRepository.save(tire);

        Long id = tire.getId();
        Assert.assertNotNull(id);

        Tire result = tireRepository.findOne(id);

        Assert.assertEquals("Pneumatika 3000", result.getName());
        Assert.assertEquals("Michelin", tire.getManufacturer());
        Assert.assertEquals("velke modre", result.getVehicleType());
        Assert.assertEquals("21", result.getSize());
        Assert.assertEquals(TireType.AllSeason_Passenger, result.getTireType());
        Assert.assertTrue(new BigDecimal("3000").compareTo(result.getPrice()) == 0);
    }

    @Test
    public void testCreateWithNullName() {
        Tire tire = newTire();
        tire.setName(null);

        exception.expectCause(isA(RollbackException.class));

        tireRepository.save(tire);
    }

    @Test
    public void testCreateWithNullTireType() {
        Tire tire = newTire();
        tire.setTireType(null);

        exception.expectCause(isA(RollbackException.class));

        tireRepository.save(tire);
    }

    @Test
    public void testCreateWithNullSize() {
        Tire tire = newTire();
        tire.setSize(null);

        exception.expectCause(isA(RollbackException.class));

        tireRepository.save(tire);
    }

    @Test
    public void testCreateWithNullManufacturer() {
        Tire tire = newTire();
        tire.setManufacturer(null);

        exception.expectCause(isA(RollbackException.class));

        tireRepository.save(tire);
    }

    @Test
    public void testCreateWithNullPrice() {
        Tire tire = newTire();
        tire.setPrice(null);

        exception.expectCause(isA(RollbackException.class));

        tireRepository.save(tire);
    }

    @Test
    public void testCreateWithNullVehicleType() {
        Tire tire = newTire();
        tire.setVehicleType(null);

        exception.expectCause(isA(RollbackException.class));

        tireRepository.save(tire);
    }

    @Test
    public void testUpdate() {
        Tire tire = newTire();

        tireRepository.save(tire);

        Long id = tire.getId();
        Assert.assertNotNull(id);

        tire.setName("Pneu 80");
        tire.setManufacturer("Yokohoma");
        tire.setVehicleType("male cervene");
        tire.setSize("17");
        tire.setTireType(TireType.OffRoad_LightTruck);
        tire.setPrice(new BigDecimal("8000"));

        tireRepository.save(tire);

        Tire result = tireRepository.findOne(id);

        Assert.assertEquals("Pneu 80", result.getName());
        Assert.assertEquals("Yokohoma", tire.getManufacturer());
        Assert.assertEquals("male cervene", result.getVehicleType());
        Assert.assertEquals("17", result.getSize());
        Assert.assertEquals(TireType.OffRoad_LightTruck, result.getTireType());
        Assert.assertTrue(new BigDecimal("8000").compareTo(result.getPrice()) == 0);
    }

    @Test
    public void testDelete() {
        Tire tire = newTire();

        tireRepository.save(tire);

        Long id = tire.getId();
        Assert.assertNotNull(id);

        tireRepository.delete(id);

        Tire result = tireRepository.findOne(id);

        Assert.assertNull(result);
    }

    public Tire newTire() {
        Tire tire = new Tire();
        tire.setName("Pneumatika 3000");
        tire.setManufacturer("Michelin");
        tire.setSize("21");
        tire.setTireType(TireType.AllSeason_Passenger);
        tire.setPrice(new BigDecimal("3000"));
        tire.setVehicleType("velke modre");
        return tire;
    }
}
