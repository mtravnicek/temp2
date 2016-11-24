package cz.muni.pa165.pneuservis.service.impl;

import cz.muni.pa165.pneuservis.persistence.domain.Order;
import cz.muni.pa165.pneuservis.persistence.domain.Tire;
import cz.muni.pa165.pneuservis.persistence.enums.TireType;
import cz.muni.pa165.pneuservis.persistence.repository.TireRepository;
import cz.muni.pa165.pneuservis.service.OrderService;
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
public class TireServiceImpl implements TireService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    TireRepository tireRepository;
    @Inject
    OrderService orderService;

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

    public List<Tire> findThreeBestSelling(){
        List<Order> orders = orderService.findAll();
        Map<Long, Integer> orderedQuantity = new HashMap<>();
        for (Order order:orders) {
            Long tireId = order.getTire().getId();
            Integer quantity = order.getTireQuantity();
            if (!orderedQuantity.containsKey(tireId)){
                orderedQuantity.put(tireId, quantity);
            } else {
                orderedQuantity.put(tireId, quantity + orderedQuantity.get(tireId));
            }
        }
        List<Map.Entry<Long, Integer>> list = new LinkedList<>(orderedQuantity.entrySet());
        Collections.sort(list, (o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));

        List<Tire> threeTires = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            threeTires.add(findOne(list.get(i).getKey()));
        }
        return threeTires;
    }

    public void delete(Long id){
        logger.info("Requested to delete AdditionalService with id: {} ", id);
        tireRepository.delete(id);
    }
}
