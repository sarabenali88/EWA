/**
 * This is a class that will give the repository functions an action for the laptops.
 *
 * @author Jasper Fernhout
 */
package app.repositories;

import app.models.Laptop;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository("LAPTOPS.MOCK")
public class LaptopRepositoryMock implements Repository<Laptop> {
    private List<Laptop> laptops = new ArrayList<>();
    private final int LISTLENGTH = 7;
    private int eanNumber = 10000;

    public LaptopRepositoryMock() {
        for (int i = 0; i < LISTLENGTH; i++) {
            laptops.add(Laptop.createSampleLaptop());
            eanNumber += Math.ceil(Math.random() * 3);
        }
    }

    @Override
    public List<Laptop> findAll() {
        return this.laptops;
    }

    @Override
    public Laptop findById(long id) {
        return this.laptops.stream().filter(laptop -> laptop.getEan() == id).findFirst().orElse(null);
    }

    @Override
    public Laptop save(Laptop model) {
        if (model.getEan() <= 0) {
            model.setEan(eanNumber);
            eanNumber += Math.ceil(Math.random() * 3);
            this.laptops.add(model);
        } else if (this.findById(model.getEan()) != null) {
            int laptopIndex = this.laptops.indexOf(this.findById(model.getEan()));
            this.laptops.set(laptopIndex, model);
        } else {
            this.laptops.add(model);
        }

        return model;
    }

    @Override
    public Laptop deleteById(long id) {
        Laptop deleteLaptop = this.findById(id);

        if (deleteLaptop != null) {
            this.laptops.remove(deleteLaptop);
        }

        return deleteLaptop;
    }
}
