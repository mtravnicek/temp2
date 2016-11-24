package cz.muni.pa165.pneuservis.service;

import cz.muni.pa165.pneuservis.persistence.domain.Tire;
import cz.muni.pa165.pneuservis.persistence.enums.TireType;

import java.util.List;

/**
 * @author Martin Spisiak <spisiak@mail.muni.cz> on 23/11/2016.
 */
public interface TireService {
    Tire save(Tire tire);
    Tire findOne(Long id);
    List<Tire> findAll();
    List<Tire> findByTireType(TireType tireType);
    List<Tire> findThreeBestSelling();
    void delete(Long id);
}
