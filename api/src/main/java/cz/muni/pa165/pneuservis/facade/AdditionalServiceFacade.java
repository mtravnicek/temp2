package cz.muni.pa165.pneuservis.facade;

import cz.muni.pa165.pneuservis.dto.AdditionalServiceDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by peter on 11/18/16.
 */
@Service
public interface AdditionalServiceFacade {
    AdditionalServiceDTO save(AdditionalServiceDTO dto);
    AdditionalServiceDTO findOne(Long id);
    List<AdditionalServiceDTO> findAll();
    List<AdditionalServiceDTO> findByNameContaining(String name);
    void delete(Long id);
}
