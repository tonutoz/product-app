package io.whatap.assignment.product.order.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOrderRequest {

  private Long id;

  private Long productId;

  private Integer oriQuantity;

  private Integer reqQuantity;

  @Builder
  public ProductOrderRequest(Long id, Long productId, Integer oriQuantity, Integer reqQuantity) {
    this.id = id;
    this.productId = productId;
    this.oriQuantity = oriQuantity;
    this.reqQuantity = reqQuantity;
  }
}
