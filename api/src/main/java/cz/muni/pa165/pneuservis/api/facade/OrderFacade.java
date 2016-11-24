package cz.muni.pa165.pneuservis.api.facade;

import cz.muni.pa165.pneuservis.api.dto.OrderDTO;

import java.util.List;

/**
 * @author Michal Travnicek xtravni2
 */
public interface OrderFacade {
    OrderDTO save(OrderDTO dto);
    OrderDTO findOne(Long id);
    List<OrderDTO> findAll();
    void delete(Long id);
}
