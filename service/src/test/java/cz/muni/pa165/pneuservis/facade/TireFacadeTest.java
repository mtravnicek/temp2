package cz.muni.pa165.pneuservis.facade;

import cz.muni.pa165.pneuservis.api.dto.TireDTO;
import cz.muni.pa165.pneuservis.api.dto.TireTypeDTO;
import cz.muni.pa165.pneuservis.api.facade.TireFacade;
import cz.muni.pa165.pneuservis.persistence.domain.Tire;
import cz.muni.pa165.pneuservis.persistence.enums.TireType;
import cz.muni.pa165.pneuservis.service.BeanMappingService;
import cz.muni.pa165.pneuservis.service.TireService;
import cz.muni.pa165.pneuservis.service.config.ServiceConfiguration;
import cz.muni.pa165.pneuservis.service.impl.TireFacadeImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Michal Krajcovic <mkrajcovic@mail.muni.cz>
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class TireFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private TireService tireService;

    @Mock
    private BeanMappingService mapper;

    @InjectMocks
    private TireFacade tireFacade = new TireFacadeImpl();

    private TireDTO tireDTO;
    
    private Tire tire;

    @BeforeMethod
    public void createDTO() {
        tireDTO = new TireDTO();
        tireDTO.setId(1L);
        tireDTO.setPrice(new BigDecimal("1000"));
        tireDTO.setManufacturer("Adidas");
        tireDTO.setTireType(TireTypeDTO.AllSeason_LightTruck);
        tireDTO.setName("Pneumatika 9001");
        tireDTO.setVehicleType("modre auto");
        tireDTO.setSize("21");

        tire = new Tire();
        tire.setId(1L);
        tire.setPrice(new BigDecimal("1000"));
        tire.setManufacturer("Adidas");
        tire.setTireType(TireType.AllSeason_LightTruck);
        tire.setName("Pneumatika 9001");
        tire.setVehicleType("modre auto");
        tire.setSize("21");

        List<Tire> tires = Collections.singletonList(tire);
        List<TireDTO> tireDTOs = Collections.singletonList(tireDTO);

        when(mapper.mapTo(tire, TireDTO.class)).thenReturn(tireDTO);
        when(mapper.mapTo(tires, TireDTO.class)).thenReturn(tireDTOs);
        when(mapper.mapTo(tireDTO, Tire.class)).thenReturn(tire);
        when(mapper.mapTo(tireDTOs, Tire.class)).thenReturn(tires);
        when(mapper.mapTo(TireTypeDTO.AllSeason_LightTruck, TireType.class)).thenReturn(TireType.AllSeason_LightTruck);
        when(mapper.mapTo(TireType.AllSeason_LightTruck, TireTypeDTO.class)).thenReturn(TireTypeDTO.AllSeason_LightTruck);
    }

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveTire() {
        tireFacade.save(tireDTO);

        verify(tireService).save(tire);
    }

    @Test
    public void testFindOneTire() {
        tireFacade.findOne(1L);

        verify(tireService).findOne(1L);
    }

    @Test
    public void testFindAllTires() {
        tireFacade.findAll();

        verify(tireService).findAll();
    }

    @Test
    public void testFindByTireType() {
        tireFacade.findByTireType(TireTypeDTO.AllSeason_LightTruck);

        verify(tireService).findByTireType(TireType.AllSeason_LightTruck);
    }

    @Test
    public void testFindThreeBestSellingTires() {
        tireFacade.findThreeBestSelling();

        verify(tireService).findThreeBestSelling();
    }

    @Test
    public void testDeleteTire() {
        tireFacade.delete(1L);

        verify(tireService).delete(1L);
    }
}
