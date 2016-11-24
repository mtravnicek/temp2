package cz.muni.pa165.pneuservis.service;

import cz.muni.pa165.pneuservis.config.TestConfiguration;
import cz.muni.pa165.pneuservis.persistence.repository.TireRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Michal Krajcovic <mkrajcovic@mail.muni.cz>
 */
@ContextConfiguration(classes = TestConfiguration.class)
public class TireServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private TireRepository repository;

    @Autowired
    @InjectMocks
    private TireService tireService;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1() {

    }
}
