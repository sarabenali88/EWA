package app.repositories;

import java.util.List;

public interface Repository<E> {
    List<E> findAll();
    E findById(int id);
    E save(E model);
    E deleteById(int id);
}
