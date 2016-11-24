package cz.muni.pa165.pneuservis.service.impl;

import cz.muni.pa165.pneuservis.api.dto.TireDTO;
import cz.muni.pa165.pneuservis.api.dto.TireTypeDTO;
import cz.muni.pa165.pneuservis.api.facade.TireFacade;
import cz.muni.pa165.pneuservis.persistence.domain.Tire;
import cz.muni.pa165.pneuservis.persistence.enums.TireType;
import cz.muni.pa165.pneuservis.service.BeanMappingService;
import cz.muni.pa165.pneuservis.service.TireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;

/**
 * @author Martin Spisiak <spisiak@mail.muni.cz> on 23/11/2016.
 */
@Service
@Transactional
public class TireFacadeImpl implements TireFacade {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    public TireService tireService;

    @Inject
    public BeanMappingService mappingService;

    public TireDTO save(TireDTO dto) {
        logger.info("Requested to save Tire : {}", dto);
        Tire tire = mappingService.mapTo(dto, Tire.class);
        return mappingService.mapTo(tireService.save(tire), TireDTO.class);
    }

    public TireDTO findOne(Long id) {
        logger.info("Requested to find Tire with id : {}", id);
        return mappingService.mapTo(tireService.findOne(id), TireDTO.class);
    }

    public List<TireDTO> findAll() {
        logger.info("Requested to find all Tire");
        return mappingService.mapTo(tireService.findAll(), TireDTO.class);
    }

    public List<TireDTO> findByTireType(TireTypeDTO tireType) {
        logger.info("Requested to find all tires with tire type : {}", tireType);
        TireType type = mappingService.mapTo(tireType, TireType.class);
        return mappingService.mapTo(tireService.findByTireType(type), TireDTO.class);
    }

    public List<TireDTO> findThreeBestSelling(){
        logger.info("Requested to find three best selling tires, according to ordered quantity");
        return mappingService.mapTo(tireService.findThreeBestSelling(), TireDTO.class);
    }

    public void delete(Long id) {
        logger.info("Requested to delete Tire with id : {}", id);
        tireService.delete(id);
    }
}
