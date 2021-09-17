package base.course.dao.impl;

import base.course.dao.ProductDao;
import base.course.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDaoImpl implements ProductDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Product save(Product product) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can`t save product in DB!");
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can`t update product from DB with id="
                    + product.getId());
        }
        return product;
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Product productForDelete = get(id).get();
            session.remove(productForDelete);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can`t delete product from DB with id=" + id);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Product.class, id));
        }
    }

    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> getAll_query = session.createQuery("from Product", Product.class);
            return getAll_query.getResultList();
        }
    }

    @Override
    public List<Product> findAllByBrand(String brand) {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> getAllByBrand_query = session.createQuery("from Product where brand = :brand");
            getAllByBrand_query.setParameter("brand", brand);
            return getAllByBrand_query.getResultList();
        }
    }
}
