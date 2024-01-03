/**
 * This is a class that will give the repository functions an action for the accounts.
 * Using the H2 console and database.
 *
 * @author Jasper Fernhout
 */
package app.repositories;

import app.models.Account;
import app.models.Image;
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
        model.setPassword(model.getPassword());
        return entityManager.merge(model);
    }

    @Override
    public Account deleteById(long id) {
        Account account = findById(id);
        if (account != null){
            entityManager.remove(account);
        }
        return account;
    }

    @Override
    public List<Account> findByQuery(String jpqlName, Object... params) {
        TypedQuery<Account> query =
                this.entityManager.createNamedQuery(jpqlName, Account.class);

        for (int i = 0; i < params.length; i++) {
            query.setParameter(i+1, params[i]);
        }
        return query.getResultList();
    }

    public List<Image> getImagesFromAccount(long id) {

        Account account = findById(id);

        if (account != null) {
            return  account.getImages();
        }

        return null;
    }
}
