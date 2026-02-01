package ies.sequeros.dam.ad.orm.infraestructure.repositories;

import ies.sequeros.dam.ad.orm.domain.IProductoRepository;
import ies.sequeros.dam.ad.orm.domain.Producto;
import ies.sequeros.dam.ad.orm.infraestructure.entities.ProductoJPA;
import ies.sequeros.dam.ad.orm.infraestructure.mappers.ProductoMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class JPAProductoRepository implements IProductoRepository {
    private final EntityManagerFactory emf;

    public JPAProductoRepository(final EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public @NotNull List<Producto> all() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT p FROM ProductoJPA p";
            List<ProductoJPA> items = em.createQuery(jpql, ProductoJPA.class).getResultList();
            return items.stream().map(ProductoMapper::toProducto).toList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void create(@NotNull Producto item) {
        final EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            // dominio a entidad JPA
            ProductoJPA producto = ProductoMapper.toJpa(item);

            tx.begin();
            em.persist(producto);
            tx.commit();
        } catch (final RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void update(@NotNull Producto item) {
        final EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            ProductoJPA producto = ProductoMapper.toJpa(item);

            tx.begin();
            em.merge(producto);
            tx.commit();
        } catch (final RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void delete(@Nullable Producto item) {
        if (item == null) return;

        final EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            //convertimos a JPA para poder hacer el merge correctamente
            ProductoJPA jpa = ProductoMapper.toJpa(item);
            //aseguramos que está en el contexto de persistencia antes de borrar
            ProductoJPA elemento = em.merge(jpa);
            em.remove(elemento);
            tx.commit();
        } catch (final RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void delete(@NotNull UUID id) {
        final EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            ProductoJPA ref = em.getReference(ProductoJPA.class, id);
            em.remove(ref);
            tx.commit();
        } catch (final RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public @Nullable Producto findById(@NotNull UUID id) {
        final EntityManager em = this.emf.createEntityManager();
        try {
            final ProductoJPA p = em.find(ProductoJPA.class, id);
            return ProductoMapper.toProducto(p);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public boolean existsById(@NotNull UUID id) {
        final EntityManager em = this.emf.createEntityManager();
        try {
            return em.find(ProductoJPA.class, id) != null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}