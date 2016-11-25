package cz.muni.pa165.pneuservis.facade;

import cz.muni.pa165.pneuservis.api.dto.AdditionalServiceDTO;
import cz.muni.pa165.pneuservis.api.facade.AdditionalServiceFacade;
import cz.muni.pa165.pneuservis.persistence.domain.AdditionalService;
import cz.muni.pa165.pneuservis.service.AdditionalServiceService;
import cz.muni.pa165.pneuservis.service.BeanMappingService;
import cz.muni.pa165.pneuservis.service.config.ServiceConfiguration;
import cz.muni.pa165.pneuservis.service.util.Utils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import static org.mockito.Mockito.verify;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Michal Travnicek xtravni2
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class AdditionalServiceFacadeTest extends AbstractTestNGSpringContextTests {
    @Mock
    private AdditionalServiceService serviceService;

    @Mock
    BeanMappingService mapper;

    @Inject
    @InjectMocks
    private AdditionalServiceFacade serviceFacade;
    
    //AdditionalServiceDTO serviceDTO;
    List<AdditionalService> serviceList = new ArrayList<>();
    List<AdditionalServiceDTO> serviceDTOList = new ArrayList<>();
    final int serviceCount = 4;
    String serviceName = "Service";
    String serviceTAG = "SPECIAL SERVICE";

    @BeforeClass
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        AdditionalServiceFacade unwrappedProxy = (AdditionalServiceFacade) Utils.unwrapProxy(serviceFacade);
        ReflectionTestUtils.setField(unwrappedProxy, "additionalServiceService", serviceService);
            
        for (int i = 0; i < serviceCount; i++) {
            serviceList.add(createService(i+1, serviceName + i+1));
            serviceDTOList.add(createServiceDTO(i+1, serviceName + i+1));            
        }     
        
    }
    
    public AdditionalService createService(int id, String name) {
        AdditionalService service = new AdditionalService();
        service.setId(Long.valueOf(id));
        service.setName(name);
        service.setDescription(serviceTAG + id);
        service.setPrice(BigDecimal.valueOf(id * 10));
        return service;
    }
    
    public AdditionalServiceDTO createServiceDTO(int id, String name) {
        AdditionalServiceDTO service = new AdditionalServiceDTO();
        service.setId(Long.valueOf(id));
        service.setName(name);
        service.setDescription(serviceTAG + id);
        service.setPrice(BigDecimal.valueOf(id * 10));
        return service;
    }
    
    @Test
    public void save() {
        Random rand = new Random();
        int  random = rand.nextInt(serviceCount);
        serviceFacade.save(serviceDTOList.get(random));
        verify(serviceService).save(serviceList.get(random));
    }
    @Test
    public void findOne() {
        Random rand = new Random();
        int  random = rand.nextInt(serviceCount);
        serviceFacade.findOne(serviceDTOList.get(random).getId());
        verify(serviceService).findOne(serviceList.get(random).getId());
    }
    @Test
    public void findByNameContaining() {
        serviceFacade.findByNameContaining(serviceName);
        verify(serviceService).findByNameContaining(serviceName);
    }
    @Test
    public void findAll() {
        serviceFacade.findAll();
        verify(serviceService).findAll();
    }
    @Test
    public void delete() {
        Random rand = new Random();
        int  random = rand.nextInt(serviceCount);
        serviceFacade.delete(serviceDTOList.get(random).getId());
        verify(serviceService).delete(serviceList.get(random).getId());
    }
}
