package br.com.stoom.store.product.business.interfaces;

import br.com.stoom.store.common.enums.EStatus;
import br.com.stoom.store.product.dto.ProductRequest;
import br.com.stoom.store.product.dto.ProductResponse;
import br.com.stoom.store.product.model.Product;

import java.util.List;

public interface IProductBO {

    ProductResponse save(ProductRequest request);
    ProductResponse findById(Long id);
    ProductResponse activate(Long id);
    ProductResponse inactivate(Long id);
    ProductResponse editProduct(Long id, ProductRequest request);
    List<ProductResponse> findAll();
    List<ProductResponse> findAllByStatus(EStatus status);
    List<ProductResponse> findAllByCategoryId(Long categoryId);
    List<ProductResponse> findAllByBrandId(Long brandId);
    List<ProductResponse> findAllActivatedProducts();
}
