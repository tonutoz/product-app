package io.whatap.assignment.product.domain;


import io.whatap.assignment.cmm.domain.CommonEntity;
import io.whatap.assignment.cmm.exception.RestApiException;
import io.whatap.assignment.product.dto.ProductRequest;
import io.whatap.assignment.product.exception.ProductError;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "PRODUCT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Product extends CommonEntity {

  @Id
  @Column(name = "id")
  @Comment("상품 아이디")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_name", nullable = false, unique = true)
  @Comment("상품 명")
  private String name;

  @Column(name = "amont", nullable = false)
  @Comment("금액")
  private Integer amount;

  @Column(name = "quantity")
  @ColumnDefault("0")
  @Comment("수량")
  private Integer quantity;

  @Builder
  public Product(Long id, String name, Integer amount, Integer quantity) {
    this.id = id;
    this.name = name;
    this.amount = amount;
    this.quantity = quantity;
  }

  public void update(final ProductRequest productRequest) {
    if (Objects.nonNull(productRequest.getName()) && !productRequest.getName().equals(this.name)) {
      this.name = productRequest.getName();
    }

    if (!this.quantity.equals(productRequest.getQuantity())) {
      this.quantity = productRequest.getQuantity();
    }

    if (this.amount.equals(productRequest.getAmount())) {
      this.amount = productRequest.getAmount();
    }
  }

  public void increaseQuantity(final Integer reqCnt) {
    this.quantity += reqCnt;
  }

  public void decreaseQuantity(final Integer reqCnt) {
    this.quantity -= reqCnt;
  }

  public boolean isAvailableQuantity(final Integer reqCnt) {

    if (this.quantity == 0) {
      throw new RestApiException(ProductError.PRODUCT_IS_SOLD_OUT);
    }

    return this.quantity < reqCnt;

  }

}
