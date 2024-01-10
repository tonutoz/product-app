package io.whatap.assignment.product.exception;

import io.whatap.assignment.cmm.exception.ErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString()
@RequiredArgsConstructor
public enum ProductError implements ErrorType {

  PRODUCT_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "ERR-01-001", "이미 등록되었습니다."),
  PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, "ERR-01-002", "상품이 존재하지 않습니다."),
  PRODUCT_IS_SOLD_OUT(HttpStatus.BAD_REQUEST, "ERR-01-003", "상품이 품절 되었습니다."),
  PRODUCT_NOT_AVAILABLE(HttpStatus.BAD_REQUEST, "ERR-01-004", "상품이 재고가 충분치 않습니다."),
  ;

  private final HttpStatus httpStatus;

  private final String errorCode;

  private final String errorMsg;

}
