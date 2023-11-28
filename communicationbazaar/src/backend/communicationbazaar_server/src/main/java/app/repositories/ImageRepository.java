/**
 * This is an interface that will provide functions for the ImageJPARepository.
 *
 * @author Seyma Kaya
 */
package app.repositories;

import java.util.List;

public interface ImageRepository<E> {

    /**
     * Function to find all the items in a specific array.
     *
     * @return gives back all the items in an array.
     * @author Seyma Kaya
     */
    List<E> findAll();

    /**
     * Function to find one specific item in an array.
     *
     * @param id the id of the item.
     * @param version the version of the item
     * @return gives back the found item.
     * @author Seyma Kaya
     */
    E findById(int id, String version);

    /**
     * Function to save a model in the array of items.
     *
     * @param model the model that has to be saved.
     * @return the saved model.
     * @author Seyma Kaya
     */
    E save(E model);

    /**
     * Function to delete one specific item in the array
     *
     * @param id the id of the item.
     * @param version the version of the item
     * @return gives back the deleted item.
     * @author Seyma Kaya
     */
    E deleteById(int id, String version);
}

