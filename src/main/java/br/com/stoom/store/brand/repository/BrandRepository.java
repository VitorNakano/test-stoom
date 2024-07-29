package br.com.stoom.store.brand.repository;

import br.com.stoom.store.brand.model.Brand;
import br.com.stoom.store.common.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findByStatus(EStatus status);
}
