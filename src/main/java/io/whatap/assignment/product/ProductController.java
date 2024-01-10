package io.whatap.assignment.product;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.whatap.assignment.cmm.aop.ExecutionTimeChecker;
import io.whatap.assignment.product.dto.ProductRequest;
import io.whatap.assignment.product.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="제품 REST API", description = "제품 CRUD용 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService service;

  @ExecutionTimeChecker
  @GetMapping("/{id}")
  public ProductResponse getProduct(@PathVariable final Long id){
    return service.getProduct(id);
  }

  @ExecutionTimeChecker
  @GetMapping
  public Page<ProductResponse> getProductsByPagination(final Pageable pageable) {
    return service.getProductPage(pageable);
  }

  @ExecutionTimeChecker
  @PostMapping
  public ProductResponse addProduct(@RequestBody @Valid final ProductRequest request) {
    return service.addProduct(request);
  }

  @ExecutionTimeChecker
  @PutMapping("/{id}")
  public ProductResponse updateProduct(@PathVariable final Long id, @RequestBody @Valid final ProductRequest request) {
    return service.modifyProduct(id,request);
  }

  @ExecutionTimeChecker
  @DeleteMapping("{id}")
  public void deleteProduct(@PathVariable final Long id){
    service.deleteProduct(id);
  }
}
