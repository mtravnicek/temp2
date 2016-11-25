package cz.muni.pa165.pneuservis.service;

import cz.muni.pa165.pneuservis.persistence.domain.AdditionalService;
import cz.muni.pa165.pneuservis.persistence.repository.AdditionalServiceRepository;
import cz.muni.pa165.pneuservis.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Michal Travnicek xtravni2
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class AdditionalServiceServiceTest extends AbstractTestNGSpringContextTests {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Mock
    AdditionalServiceRepository repository;

    @Autowired
    @InjectMocks
    AdditionalServiceService serviceService;

    List<AdditionalService> serviceList = new ArrayList<>();
    final int serviceCount = 4;
    String serviceName = "Service";

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        for (int i = 0; i < serviceCount; i++) {
            serviceList.add(createService(i+1, serviceName + i+1));
            when(repository.save(serviceList.get(i))).thenReturn(serviceList.get(i));
            when(repository.findOne(serviceList.get(i).getId())).thenReturn(serviceList.get(i));
        }
        when(repository.findAll()).thenReturn(serviceList);
        when(repository.findByNameContaining(serviceName)).thenReturn(serviceList);
    }

    public AdditionalService createService(int id, String name) {
        AdditionalService service = new AdditionalService();
        service.setId(Long.valueOf(id));
        service.setName(name);
        service.setDescription("SPECIAL SERVICE" + id);
        service.setPrice(BigDecimal.valueOf(id * 10));
        return service;
    }

    @Test
    public void save() {
        AdditionalService savedService;
        for (int i = 0; i < serviceCount; i++) {
                savedService = serviceService.save(serviceList.get(i));
                Assert.assertEquals(savedService, serviceList.get(i), "Saving failed");
            }
    }

    @Test
    public void findOne() {
        Random rand = new Random();
        int  random = rand.nextInt(serviceCount);
        Assert.assertEquals(serviceService.findOne(Long.valueOf(random+1)), serviceList.get(random), "FindOne failed");
    }

    @Test
    public void findAll() {
        Assert.assertEquals(serviceService.findAll(), serviceList, "FindAll failed");
    }
    
    @Test
    public void findByNameContaining() {
        Assert.assertEquals(serviceService.findByNameContaining(serviceName), serviceList, "FindByName failed");
    }
    
    @Test
    public void delete() {
        serviceService.delete(1L);
        verify(repository).delete(1L);
    }

}
