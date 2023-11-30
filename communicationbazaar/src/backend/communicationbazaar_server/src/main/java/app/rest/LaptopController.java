/**
 * This is a controller for the laptops that will catch the calls from the front end.
 *
 * @author Jasper Fernhout
 */
package app.rest;

import app.exceptions.PreConditionFailedException;
import app.exceptions.ResourceNotFoundException;
import app.models.Laptop;
import app.models.ViewClasses;
import app.repositories.Repository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laptops")
public class LaptopController {
    @Autowired
    Repository<Laptop> laptopList;

    @GetMapping(path = "", produces = "application/json")
    public List<Laptop> getAllLaptops() {
        return this.laptopList.findAll();
    }

    @JsonView(ViewClasses.Summary.class)
    @GetMapping(path = "/summary", produces = "application/json")
    public List<Laptop> getSummaryLaptops() {
        return this.laptopList.findAll();
    }

    @GetMapping(path = "{ean}", produces = "application/json")
    public ResponseEntity<Laptop> getOneLaptop(@PathVariable() long ean) throws ResourceNotFoundException {
        Laptop laptop = this.laptopList.findById(ean);

        if (laptop == null) {
            throw new ResourceNotFoundException("Cannot provide laptop with ean= " + ean);
        }

        return ResponseEntity.ok().body(laptop);
    }

    @DeleteMapping(path = "{ean}", produces = "application/json")
    public Laptop deleteOneLaptop(@PathVariable() long ean) throws ResourceNotFoundException {
        Laptop laptop = this.laptopList.deleteById(ean);

        if (laptop == null) {
            throw new ResourceNotFoundException("Cannot delete a laptop with ean= " + ean);
        }

        return laptop;
    }

    @PostMapping(path = "", produces = "application/json")
    public Laptop addOneLaptop(@RequestBody Laptop laptop) throws Exception {

        if (this.laptopList.findById(laptop.getEan()) != null) {
            throw new Exception("Laptop already exist with ean= " + laptop.getEan());
        }

        laptop = this.laptopList.save(laptop);

        return laptop;
    }

    @PutMapping(path = "{ean}", produces = "application/json")
    public Laptop updateOneLaptop(@PathVariable() long ean, @RequestBody Laptop targetLaptop) throws PreConditionFailedException {

        if (ean != targetLaptop.getEan()) {
            throw new PreConditionFailedException("Laptop-ean=" + targetLaptop.getEan() + " does not match path parameter=" + ean);
        } else {
            targetLaptop = this.laptopList.save(targetLaptop);
        }

        return targetLaptop;
    }
}
