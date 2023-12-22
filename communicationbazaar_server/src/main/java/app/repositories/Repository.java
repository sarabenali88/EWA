/**
 * This is an interface that will provide functions for the repositrorymocks.
 *
 * @author Jasper Fernhout
 */
package app.repositories;

import java.util.List;

public interface Repository<E> {

    /**
     * Function to find all the items in a specific array.
     *
     * @return gives back all the items in an array.
     * @author Jasper Fernhout
     */
    List<E> findAll();

    /**
     * Function to find one specific item in an array.
     *
     * @param id the id of the item.
     * @return gives back the found item.
     * @author Jasper Fernhout
     */
    E findById(long id);

    /**
     * Function to save a model in the array of items.
     *
     * @param model the model that has to be saved.
     * @return the saved model.
     * @author Jasper Fernhout
     */
    E save(E model);

    /**
     * Function to delete one specific item in the array
     *
     * @param id the id of the item.
     * @return gives back the deleted item.
     * @author Jasper Fernhout
     */
    E deleteById(long id);

    /**
     * Function to delete one specific item in the array
     *
     * @param jpqlName name of the specific query that is being called.
     * @param params objects that are used inside of the query
     * @return response of the query.
     * @author Seyma Kaya
     */
    List<E> findByQuery(String jpqlName, Object... params);
}
