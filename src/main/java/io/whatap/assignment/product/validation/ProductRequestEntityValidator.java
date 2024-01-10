package io.whatap.assignment.product.validation;

import io.whatap.assignment.cmm.exception.ErrorResponse.ValidationError;
import io.whatap.assignment.cmm.validation.RequestEntityValidator;
import io.whatap.assignment.product.dto.ProductRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**
 * 주문 요청 벨리데이터
 */
@Component
public class ProductRequestEntityValidator implements RequestEntityValidator<ProductRequest> {

  private static final String IS_NULL = "is null";

  public List<ValidationError> validate(final ProductRequest request) {

    List<ValidationError> errorList = new ArrayList<>();

    if (Objects.isNull(request.getName())) {
      errorList.add(ValidationError.builder().field("name").message(IS_NULL).build());
    }

    if (Objects.isNull(request.getAmount())) {
      errorList.add(ValidationError.builder().field("amount").message(IS_NULL).build());
    }

    if (Objects.isNull(request.getQuantity())) {
      errorList.add(ValidationError.builder().field("quantity").message(IS_NULL).build());
    }

    return errorList;
  }

}
