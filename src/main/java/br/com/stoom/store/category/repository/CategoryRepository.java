package br.com.stoom.store.category.repository;

import br.com.stoom.store.category.model.Category;
import br.com.stoom.store.common.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByStatus(EStatus status);
}
