/**
 * This is a class that will give the repository functions an action for the images.
 * Using JPA.
 *
 * @author Seyma Kaya
 */
package app.repositories;

import app.models.Image;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NavigableMap;

@Primary
@Repository
@Transactional
public class ImageRepositoryJPA implements ImageRepository<Image>{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Image> findAll() {
        TypedQuery<Image> query =
                this.entityManager.createQuery(
                        "select b from Image b", Image.class);
        return query.getResultList();
    }

    @Override
    public Image findById(int id, String version) {
        return this.entityManager.find(Image.class, id);
    }


    @Override
    public Image save(Image model) {
        return entityManager.merge(model);
    }

    @Override
    public Image deleteById(int id, String version) {
        Image image = findById(id, version);
        if (image != null){
            entityManager.remove(image);
        }
        return image;
    }
}
