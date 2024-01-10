package io.whatap.assignment.product.dto;

import io.whatap.assignment.product.domain.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductRequest {

    private String name;

    private Integer amount;

    private Integer quantity;

    @Builder
    public ProductRequest(String name, Integer amount, Integer quantity) {
      this.name = name;
      this.amount = amount;
      this.quantity = quantity;
    }

    public Product toEntity() {
      return Product.builder().
          name(this.name).
          amount(this.amount).
          quantity(this.quantity).
          build();
    }

}
