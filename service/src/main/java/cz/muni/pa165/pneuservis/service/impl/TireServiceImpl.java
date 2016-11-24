package cz.muni.pa165.pneuservis.service.impl;

import cz.muni.pa165.pneuservis.persistence.domain.Tire;
import cz.muni.pa165.pneuservis.persistence.enums.TireType;
import cz.muni.pa165.pneuservis.persistence.repository.TireRepository;
import cz.muni.pa165.pneuservis.service.TireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Martin Spisiak <spisiak@mail.muni.cz> on 23/11/2016.
 */
@Service
@Transactional
public class TireServiceImpl implements TireService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    TireRepository tireRepository;

    public Tire save(Tire tire){
        logger.info("Requested to save Tire : {}", tire);
        return tireRepository.save(tire);
    }

    public Tire findOne(Long id){
        logger.info("Requested to find Tire with id : {}", id);
        return tireRepository.findOne(id);
    }

    public List<Tire> findAll(){
        logger.info("Requested to find all tires : {}");
        return tireRepository.findAll();
    }

    public List<Tire> findByTireType(TireType tireType){
        logger.info("Requested to find Tires with tire type : {}", tireType);
        return tireRepository.findByTireType(tireType);
    }

    public void delete(Long id){
        logger.info("Requested to delete AdditionalService with id: {} ", id);
        tireRepository.delete(id);
    }
}
