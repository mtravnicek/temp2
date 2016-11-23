package cz.muni.pa165.pneuservis.service;

import cz.muni.pa165.pneuservis.config.TestConfiguration;
import cz.muni.pa165.pneuservis.persistence.domain.AdditionalService;
import cz.muni.pa165.pneuservis.persistence.repository.AdditionalServiceRepository;
import cz.muni.pa165.pneuservis.service.impl.AdditionalServiceServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;

/**
 * Created by peter on 11/22/16.
 */
@ContextConfiguration(classes = TestConfiguration.class)
public class AdditionalServiceServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    AdditionalServiceRepository repository;

    @Autowired
    @InjectMocks
    AdditionalServiceService serviceService;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1() {
        MockitoAnnotations.initMocks(this);
        AdditionalService service = new AdditionalService();
        service.setName("Nazov");
        service.setDescription("Opis");
        service.setPrice(BigDecimal.TEN);
        serviceService.save(service);
    }
}
