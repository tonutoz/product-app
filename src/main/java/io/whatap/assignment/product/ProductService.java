package io.whatap.assignment.product;

import io.whatap.assignment.cmm.aop.MethodLogWriter;
import io.whatap.assignment.cmm.exception.RestApiException;
import io.whatap.assignment.product.domain.Product;
import io.whatap.assignment.product.dto.ProductRequest;
import io.whatap.assignment.product.dto.ProductResponse;
import io.whatap.assignment.product.exception.ProductError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;

  @MethodLogWriter
  public ProductResponse getProduct(final Long id) {
    Product entity = productRepository.findById(id)
        .orElseThrow(() -> new RestApiException(ProductError.PRODUCT_NOT_FOUND));
    return ProductResponse.of(entity);
  }

  @MethodLogWriter
  public Page<ProductResponse> getProductPage(final Pageable pageable) {
    Page<Product> pageResults = productRepository.findAll(pageable);
    return pageResults.map(ProductResponse::of);
  }

  @MethodLogWriter
  @Transactional
  public ProductResponse addProduct(final ProductRequest product) {

    if (productRepository.existsByName(product.getName())) {
      throw new RestApiException(ProductError.PRODUCT_ALREADY_EXIST);
    }

    Product result = productRepository.save(product.toEntity());
    log.info("Add Product {}", result.toString());
    return ProductResponse.of(result);
  }

  @MethodLogWriter
  @Transactional
  public ProductResponse modifyProduct(final Long id, final ProductRequest request) {
    Product target = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(request.getName()));
    target.update(request);
    log.info("update Product {}", target.toString());
    return ProductResponse.of(target);
  }

  @MethodLogWriter
  @Transactional
  public void deleteProduct(final Long id) {
    if (!productRepository.existsById(id)) {
      throw new RestApiException(ProductError.PRODUCT_NOT_FOUND);
    }
    log.info("Delete Product ID {}", id);
    productRepository.deleteById(id);
  }

}
