/**
 * This is a controller for the images that will catch the calls from the front end.
 *
 * @author Jasper Fernhout
 */
package app.rest;

import app.exceptions.PreConditionFailedException;
import app.exceptions.ResourceNotFoundException;
import app.models.Image;
import app.models.ViewClasses;
import app.repositories.ImageRepository;
import app.repositories.Repository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    Repository<Image> imageList;

    @GetMapping(path = "", produces = "application/json")
    public List<Image> getAllImages() {
        return this.imageList.findAll();
    }

    @JsonView(ViewClasses.Summary.class)
    @GetMapping(path = "/summary", produces = "application/json")
    public List<Image> getSummaryImages() {
        return this.imageList.findAll();
    }

    @GetMapping(path = "{ean}", produces = "application/json")
    public ResponseEntity<Image> getOneImage(@PathVariable() int ean) throws ResourceNotFoundException {
        Image image = this.imageList.findById(ean);

        if (image == null) {
            throw new ResourceNotFoundException("Cannot provide image with ean= " + ean);
        }

        return ResponseEntity.ok().body(image);
    }

    @DeleteMapping(path = "{ean}", produces = "application/json")
    public Image deleteOneImage(@PathVariable() int ean) throws ResourceNotFoundException {
        Image image = this.imageList.deleteById(ean);

        if (image == null) {
            throw new ResourceNotFoundException("Cannot delete a image with ean= " + ean);
        }

        return image;
    }

    @PostMapping(path = "", produces = "application/json")
    public Image addOneImage(@RequestBody Image image) throws Exception {

        if (this.imageList.findById(image.getLaptop().getEan()) != null) {
            throw new Exception("Image already exist with ean= " + image.getLaptop().getEan());
        }

        image = this.imageList.save(image);

        return image;
    }

    @PutMapping(path = "{ean}", produces = "application/json")
    public Image updateOneImage(@PathVariable() int ean, @RequestBody Image targetImage) throws PreConditionFailedException {

        if (ean != targetImage.getLaptop().getEan()) {
            throw new PreConditionFailedException("Image-ean=" + targetImage.getLaptop().getEan() + " does not match path parameter=" + ean);
        } else {
            targetImage = this.imageList.save(targetImage);
        }

        return targetImage;
    }
}
