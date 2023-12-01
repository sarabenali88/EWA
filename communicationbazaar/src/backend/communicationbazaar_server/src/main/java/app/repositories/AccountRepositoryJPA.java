package app.repositories;

import app.models.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
@Transactional
public class AccountRepositoryJPA implements app.repositories.Repository<Account> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Account> findAll() {
        TypedQuery<Account> query = entityManager.createQuery(
                "select a from Account a", Account.class
        );
        return query.getResultList();
    }

    @Override
    public Account findById(long id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public Account save(Account model) {
        if (model.getPersonalNumber() == 0){
            model.setPassword(model.getPassword());
            return entityManager.merge(model);
        }
        else {
            model.setPassword(model.getPassword());
            return entityManager.merge(model);
        }
    }

    @Override
    public Account deleteById(long id) {
        Account account = findById(id);
        if (account != null){
            entityManager.remove(account);
        }
        return account;
    }
}
