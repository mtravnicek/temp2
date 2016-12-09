package cz.muni.pa165.pneuservis.backend.test.dao;

import cz.muni.pa165.pneuservis.backend.domain.AdditionalService;
import cz.muni.pa165.pneuservis.backend.repository.AdditionalServiceRepository;
import cz.muni.pa165.pneuservis.backend.test.config.DaoTestConfig;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;
import java.util.ListIterator;
import javax.persistence.RollbackException;
import static org.hamcrest.CoreMatchers.isA;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * @author  Michal Travnicek 31/10/2016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoTestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdditionalServiceDaoTest {

    @Inject
    private AdditionalServiceRepository addServiceRepo;
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    String testName = "CARWASH";
    BigDecimal testPrice = BigDecimal.valueOf(1500.50);
    String testDescription = "Car is clean and shiny";

    public AdditionalService createTestService() {

        AdditionalService service = new AdditionalService();
        service.setName(testName);
        service.setPrice(testPrice);
        service.setDescription(testDescription);
        return service;

    }
    public AdditionalService createTestService(String name, BigDecimal price) {

        AdditionalService service = new AdditionalService();
        service.setName(name);
        service.setPrice(price);
        service.setDescription("DUMMY");
        return service;

    }

    public boolean checkSameContent(AdditionalService one, AdditionalService two) {
        return one.getName().equals(two.getName())
                && one.getPrice().compareTo(two.getPrice()) == 0
                && one.getDescription().equals(two.getDescription());
    }

    @Test
    public void saveOne() {

        AdditionalService service = createTestService();
        addServiceRepo.save(service);
        ListIterator list = addServiceRepo.findAll().listIterator();
        AdditionalService tempService = (AdditionalService) list.next();
        Assert.assertFalse("ONLY ONE", list.hasNext());       
        Assert.assertEquals(tempService, service);
        Assert.assertTrue("EQUAL CONTENT", checkSameContent(service, tempService));
    }

    @Test
    public void updateOne() {

        AdditionalService service = createTestService();
        addServiceRepo.save(service);
        service.setName("CHANGED NAME");
        service.setDescription("Something else");
        service.setPrice(new BigDecimal("2500"));
        addServiceRepo.save(service);
        ListIterator list = addServiceRepo.findAll().listIterator();
        AdditionalService tempService = (AdditionalService) list.next();
        Assert.assertFalse("ONLY ONE", list.hasNext());
        Assert.assertTrue("EQUAL CONTENT", checkSameContent(service, tempService));
    }

    @Test
    public void deleteOne() {

        AdditionalService service = createTestService();
        addServiceRepo.save(service);        
        addServiceRepo.delete(service);
        ListIterator list = addServiceRepo.findAll().listIterator();
        Assert.assertFalse("NO ELEMENTS", list.hasNext());
        Assert.assertNull("DELETED = NULL", addServiceRepo.findOne(service.getId()));
        
    }

    @Test
    public void findOne() {

        AdditionalService service = createTestService();
        addServiceRepo.save(service);
        AdditionalService tempService = addServiceRepo.findOne(service.getId());
        Assert.assertNotNull("FIND FINDS SOMETHING", tempService);
        Assert.assertEquals(service, tempService);

    }

    @Test
    public void findAll() {
                
        addServiceRepo.save(createTestService("A",BigDecimal.ONE));
        addServiceRepo.save(createTestService("B",BigDecimal.TEN));
        addServiceRepo.save(createTestService("C",BigDecimal.TEN));
                
        List<AdditionalService> found = addServiceRepo.findAll();
        Assert.assertEquals(3, found.size());
    }
    
    @Test
    public void persistWithNegativePrice() {
        
        exception.expectCause(isA(RollbackException.class));
        addServiceRepo.save(createTestService("A",BigDecimal.valueOf(-150)));
        
    }
    
    @Test
    public void persistWithNullName() {
        
        exception.expectCause(isA(RollbackException.class));
        addServiceRepo.save(createTestService(null,BigDecimal.valueOf(1500)));
        
    }
    
    @Test
    public void persistWithNullPrice() {
        
        exception.expectCause(isA(RollbackException.class));
        addServiceRepo.save(createTestService("A",null));
        
    }

}
