package io.whatap.assignment.product;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.whatap.assignment.cmm.aop.ExecutionTimeChecker;
import io.whatap.assignment.cmm.exception.ErrorResponse.ValidationError;
import io.whatap.assignment.cmm.validation.RequestEntityValidator;
import io.whatap.assignment.product.dto.ProductRequest;
import io.whatap.assignment.product.dto.ProductResponse;
import io.whatap.assignment.product.exception.InvalidRequestException;
import io.whatap.assignment.product.exception.ProductError;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="제품 REST API", description = "제품 CRUD용 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

  private final ProductService service;

  private final RequestEntityValidator<ProductRequest> productRequestVlidator;

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
  @ResponseStatus(HttpStatus.CREATED)
  public ProductResponse addProduct(@RequestBody @Valid final ProductRequest request) {

    List<ValidationError> validationErrorList = productRequestVlidator.validate(request);

    if(!validationErrorList.isEmpty()) {
      throw new InvalidRequestException(ProductError.PRODUCT_REQ_NOT_VALID, validationErrorList);
    }

    return service.addProduct(request);
  }

  @ExecutionTimeChecker
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ProductResponse updateProduct(@PathVariable final Long id, @RequestBody @Valid final ProductRequest request) {
    return service.modifyProduct(id,request);
  }

  @ExecutionTimeChecker
  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteProduct(@PathVariable final Long id){
    service.deleteProduct(id);
  }
}
