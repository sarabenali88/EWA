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
            images.add(Image.createSampleImage());
        }
    }

    @Override
    public List<Image> findAll() {
        return this.images;
    }

    @Override
    public Image findById(int id) {
        return this.images.stream().filter(image -> image.getLaptop().getEan() == id).findFirst().orElse(null);
    }

    @Override
    public Image save(Image model) {

        return model;
    }

    @Override
    public Image deleteById(int id) {
        Image deleteImage = this.findById(id);

        if (deleteImage != null) {
            this.images.remove(deleteImage);
        }

        return deleteImage;
    }
}
