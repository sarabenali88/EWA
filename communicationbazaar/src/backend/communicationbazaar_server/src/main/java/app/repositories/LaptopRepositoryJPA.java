/**
 * This is a class that will give the repository functions an action for the laptops.
 * Using JPA.
 *
 * @author Seyma Kaya
 */
package app.repositories;

import app.models.Image;
import app.models.Laptop;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@org.springframework.stereotype.Repository
@Transactional
public class LaptopRepositoryJPA implements Repository<Laptop>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Laptop> findAll() {
        TypedQuery<Laptop> query =
                this.entityManager.createQuery(
                        "select b from Laptop b", Laptop.class);
        return query.getResultList();
    }

    @Override
    public Laptop findById(long id) {
        return this.entityManager.find(Laptop.class, id);
    }

    @Override
    public Laptop save(Laptop model) {
        return entityManager.merge(model);
    }

    @Override
    public Laptop deleteById(long id) {
        Laptop laptop = findById(id);
        if (laptop != null){
            entityManager.remove(laptop);
        }
        return laptop;
    }
}
