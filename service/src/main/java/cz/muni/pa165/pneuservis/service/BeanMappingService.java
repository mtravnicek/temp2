package cz.muni.pa165.pneuservis.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by peter on 11/18/16.
 */
public interface BeanMappingService {
    /**
     * Maps given Collection to List of given class
     *
     * @param objects Collection to map
     * @param mapToClass target class
     * @param <T> class type
     * @return mapped List
     */
    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    /**
     * Maps given object to instance of given given class
     *
     * @param u object to map
     * @param mapToClass target class
     * @param <T> class type
     * @return mapped List
     */
    <T> T mapTo(Object u, Class<T> mapToClass);

    /**
     * Return dozer mapper
     *
     * @return mapper
     */
    Mapper getMapper();
}
