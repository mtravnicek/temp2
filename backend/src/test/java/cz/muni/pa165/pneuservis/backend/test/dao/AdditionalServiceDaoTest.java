package cz.muni.pa165.pneuservis.backend.test.dao;

import cz.muni.pa165.pneuservis.backend.domain.AdditionalService;
import cz.muni.pa165.pneuservis.backend.domain.User;
import cz.muni.pa165.pneuservis.backend.enums.Role;
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
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import org.junit.BeforeClass;

/**
 * Created by Michal Travnicek
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoTestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdditionalServiceDaoTest {

    @Inject
    private AdditionalServiceRepository addServiceRepo;

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
    
    public boolean checkSameContent(AdditionalService one, AdditionalService two) {
        return one.getName().equals(two.getName()) && 
               //one.getPrice().equals(two.getPrice()) &&
               one.getDescription().equals(two.getDescription());       
    }

    @Test
    public void persistOne() {

        AdditionalService service = createTestService();
        addServiceRepo.save(service);
        ListIterator list = addServiceRepo.findAll().listIterator();
        AdditionalService tempService = (AdditionalService) list.next();
        Assert.assertFalse("ONLY ONE", list.hasNext());
        System.out.println(service);
        System.out.println(tempService);
        Assert.assertEquals(tempService, service);
        Assert.assertTrue("EQUAL CONTENT", checkSameContent(service, tempService));
    }
    
        
    @Test
    public void updateOne() {

        AdditionalService service = createTestService();
        addServiceRepo.save(service);
        service.setDescription("Something else");
        addServiceRepo.save(service);
        ListIterator list = addServiceRepo.findAll().listIterator();
        AdditionalService tempService = (AdditionalService) list.next();
        Assert.assertFalse("ONLY ONE", list.hasNext());
        System.out.println(tempService);
        Assert.assertEquals(tempService, service);
        Assert.assertTrue("EQUAL CONTENT", checkSameContent(service, tempService));
    }

}
