package cz.muni.pa165.pneuservis.api.facade;

import cz.muni.pa165.pneuservis.api.dto.AdditionalServiceDTO;

import java.util.List;

/**
 * Created by peter on 11/18/16.
 */
public interface AdditionalServiceFacade {
    AdditionalServiceDTO save(AdditionalServiceDTO dto);
    AdditionalServiceDTO findOne(Long id);
    List<AdditionalServiceDTO> findAll();
    List<AdditionalServiceDTO> findByNameContaining(String name);
    void delete(Long id);
}
