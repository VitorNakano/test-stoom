package br.com.stoom.store.product.repository;

import br.com.stoom.store.common.enums.EStatus;
import br.com.stoom.store.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByStatus(EStatus status);
    List<Product> findAllByCategoryId(Long id);
    List<Product> findAllByBrandId(Long id);
    @Query("SELECT p FROM Product p "
            + "LEFT JOIN p.category c "
            + "LEFT JOIN p.brand b "
            + "WHERE c.status = :status "
            + "AND b.status = :status")
    List<Product> findAllActivatedProducts(EStatus status);
}