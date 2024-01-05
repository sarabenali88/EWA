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
import app.repositories.Repository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "{id}", produces = "application/json")
    public ResponseEntity<Image> getOneImage(@PathVariable() long id) throws ResourceNotFoundException {
        Image image = this.imageList.findById(id);

        if (image == null) {
            throw new ResourceNotFoundException("Cannot provide image with ean= " + id);
        }

        return ResponseEntity.ok().body(image);
    }

    @DeleteMapping(path = "{id}", produces = "application/json")
    public ResponseEntity<Image> deleteOneImage(@PathVariable() long id) throws ResourceNotFoundException {
        Image image = this.imageList.deleteById(id);

        if (image == null) {
            throw new ResourceNotFoundException("Cannot delete a image with ean= " + id);
        }

        return ResponseEntity.ok().body(image);
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Image> addOneImage(@RequestBody Image image) throws Exception {

        if (this.imageList.findById(image.getId()) != null){
            throw new Exception("Image already exist with id= " + image.getId());
        }

        image = this.imageList.save(image);

        return ResponseEntity.status(HttpStatus.CREATED).body(image);
    }

    @PutMapping(path = "{id}", produces = "application/json")
    public ResponseEntity<Image> updateOneImage(@PathVariable() long id, @RequestBody Image targetImage) throws PreConditionFailedException {

        if (id != targetImage.getId()) {
            throw new PreConditionFailedException("Image-id=" + targetImage.getId() + " does not match path parameter=" + id);
        } else {
            targetImage = this.imageList.save(targetImage);
        }

        return ResponseEntity.ok().body(targetImage);
    }
}
