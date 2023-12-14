/**
 * This is a class that will give the repository functions an action for the images.
 *
 * @author Jasper Fernhout
 */
package app.repositories;

import app.models.Image;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository("IMAGES.MOCK")
public class ImageRepositoryMock implements Repository<Image> {
    private List<Image> images = new ArrayList<>();
    private final int LISTLENGTH = 7;

    public ImageRepositoryMock() {
        for (int i = 0; i < LISTLENGTH; i++) {
            images.add(Image.createSampleImage(1000+i));
        }
    }

    @Override
    public List<Image> findAll() {
        return this.images;
    }

    @Override
    public Image findById(long id) {
        return this.images.stream().filter(image -> image.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Image save(Image model) {
        if (this.findById(model.getId()) != null) {
            int modelIndex = this.images.indexOf(this.findById(model.getId()));
            this.images.set(modelIndex, model);
        } else {
            this.images.add(model);
        }
        return model;
    }

    @Override
    public Image deleteById(long id) {
        Image deleteImage = this.findById(id);

        if (deleteImage != null) {
            this.images.remove(deleteImage);
        }

        return deleteImage;
    }

    @Override
    public List<Image> findByQuery(String jpqlName, Object... params) {
        return null;
    }
}
