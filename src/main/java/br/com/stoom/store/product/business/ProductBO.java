package br.com.stoom.store.product.business;

import br.com.stoom.store.brand.business.BrandBO;
import br.com.stoom.store.brand.model.Brand;
import br.com.stoom.store.category.business.CategoryBO;
import br.com.stoom.store.category.model.Category;
import br.com.stoom.store.common.enums.EStatus;
import br.com.stoom.store.common.exceptions.ValidationException;
import br.com.stoom.store.product.business.interfaces.IProductBO;
import br.com.stoom.store.product.dto.ProductRequest;
import br.com.stoom.store.product.dto.ProductResponse;
import br.com.stoom.store.product.model.Product;
import br.com.stoom.store.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static br.com.stoom.store.common.validators.Validator.validateStatus;

@Service
public class ProductBO implements IProductBO {

    private static final String NOT_FOUND_BY_ID = "Product not found by ID: ";

    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryBO categoryService;
    @Autowired
    private BrandBO brandService;

    @Override
    @Transactional
    public ProductResponse save(ProductRequest request) {
        Product product = new Product();
        mapping(request, product);
        Product mappedProduct = Product.converFrom(request, product);
        return ProductResponse.of(repository.save(mappedProduct));
    }

    @Override
    public ProductResponse findById(Long id) {
        return repository.findById(id)
            .map(ProductResponse::of)
            .orElseThrow(() -> new ValidationException(NOT_FOUND_BY_ID + id));
    }

    @Override
    @Transactional
    public ProductResponse activate(Long id) {
        return Optional.of(repository.save(changeProductStatus(id, EStatus.A)))
                .map(ProductResponse::of)
                .orElseThrow(() -> new ValidationException(NOT_FOUND_BY_ID + id));
    }

    @Override
    @Transactional
    public ProductResponse inactivate(Long id) {
        return Optional.of(repository.save(changeProductStatus(id, EStatus.I)))
                .map(ProductResponse::of)
                .orElseThrow(() -> new ValidationException(NOT_FOUND_BY_ID + id));
    }

    @Override
    @Transactional
    public ProductResponse editProduct(Long id, ProductRequest request) {
        Product product = Product.converFromResponse(findById(id));
        mapProduct(request, product);
        return ProductResponse.of(product);
    }

    @Override
    public List<ProductResponse> findAll(){
        return repository.findAll().stream()
            .map(ProductResponse::of)
            .toList();
    }

    @Override
    public List<ProductResponse> findAllByStatus(EStatus status) {
        if (!validateStatus(status)) {
            throw new ValidationException("Invalid status. Allowed values are A or I");
        }
        return repository.findByStatus(status).stream()
            .map(ProductResponse::of)
            .toList();
    }

    @Override
    public List<ProductResponse> findAllByCategoryId(Long categoryId) {
        return repository.findAllByCategoryId(categoryId).stream()
            .map(ProductResponse::of)
            .toList();
    }

    @Override
    public List<ProductResponse> findAllByBrandId(Long brandId) {
        return repository.findAllByBrandId(brandId).stream()
            .map(ProductResponse::of)
            .toList();
    }

    @Override
    public List<ProductResponse> findAllActivatedProducts() {
        return repository.findAllActivatedProducts(EStatus.A).stream()
            .map(ProductResponse::of)
            .toList();
    }

    private Category getCategoryById(Long id) {
        return Category.convertFromResponse(categoryService.findById(id));
    }

    private Brand getBrandById(Long id) {
        return Brand.convertFromResponse(brandService.findById(id));
    }

    private void mapping(ProductRequest request, Product product) {
        Brand brand = getBrandById(request.getBrandId());
        Category category = getCategoryById(request.getCategoryId());

        mappingBrand(brand, product);
        mappingCategory(category, product);
    }

    private void mappingCategory(Category category, Product product) {
        product.setCategory(category);
    }

    private void mappingBrand(Brand brand, Product product) {
        product.setBrand(brand);
    }

    private Product changeProductStatus(Long id, EStatus status) {
        Product product = Product.converFromResponse(findById(id));
        product.setStatus(status);
        return product;
    }

    private void mapProduct(ProductRequest request, Product product) {
        ModelMapper mapper = new ModelMapper();
        mapper.map(request, product);
    }
}
