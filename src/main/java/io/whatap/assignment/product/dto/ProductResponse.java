package io.whatap.assignment.product.dto;

import io.whatap.assignment.cmm.domain.CommonDto;
import io.whatap.assignment.product.domain.Product;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductResponse extends CommonDto {

    private Long id;

    private String name;

    private Integer amount;

    private Integer quantity;

    @Builder
    public ProductResponse(Long id, LocalDateTime createdOn,LocalDateTime modifiedOn, String name, Integer amount, Integer quantity) {
      this.createdOn = createdOn;
      this.modifiedOn = modifiedOn;
      this.id = id;
      this.name = name;
      this.amount = amount;
      this.quantity = quantity;
    }

  public static ProductResponse of(final Product product) {
    return ProductResponse.builder()
        .id(product.getId())
        .createdOn(product.getCreatedOn())
        .modifiedOn(product.getModifiedOn())
        .name(product.getName())
        .amount(product.getAmount())
        .quantity(product.getQuantity())
        .build();
  }

}
