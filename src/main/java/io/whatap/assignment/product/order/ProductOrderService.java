package io.whatap.assignment.product.order;

import io.whatap.assignment.cmm.aop.MethodLogWriter;
import io.whatap.assignment.cmm.exception.RestApiException;
import io.whatap.assignment.product.domain.Product;
import io.whatap.assignment.product.ProductRepository;
import io.whatap.assignment.product.exception.ProductError;
import io.whatap.assignment.product.order.dto.ProductOrderRequest;
import io.whatap.assignment.product.order.dto.ProductOrderResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductOrderService {

  private final ProductRepository productRepository;

  /**
   * 주문처리
   *
   * @param requests
   * @return
   */
  @Transactional
  @MethodLogWriter
  public List<ProductOrderResponse> doOrder(final List<ProductOrderRequest> requests) {

    return requests.stream().map((req) -> {
      log.debug("id {}", req.getProductId());
      Product product = productRepository.findById(req.getProductId())
          .orElseThrow(() -> new RestApiException(
              ProductError.PRODUCT_NOT_FOUND));

      if (product.isAvailableQuantity(req.getReqQuantity())) {
        throw new RestApiException(ProductError.PRODUCT_NOT_AVAILABLE);
      }
      product.decreaseQuantity(req.getReqQuantity());
      log.info("Order Complete product id {} reqCnt {}", req.getProductId(), req.getReqQuantity());

      return ProductOrderResponse.builder()
          .id(product.getId())
          .name(product.getName())
          .quantity(req.getReqQuantity())
          .amount(product.getAmount())
          .build();
    }).toList();
  }

  /**
   * 주문 수정
   *
   * @param requests
   * @return
   */
  @Transactional
  @MethodLogWriter
  public List<ProductOrderResponse> modifyOrder(final List<ProductOrderRequest> requests) {

    return requests.stream().map((req) -> {
      Product product = productRepository.findById(req.getProductId())
          .orElseThrow(() -> new RestApiException(
              ProductError.PRODUCT_NOT_FOUND));
      product.increaseQuantity(req.getOriQuantity());
      if (product.isAvailableQuantity(req.getReqQuantity())) {
        throw new RestApiException(ProductError.PRODUCT_NOT_AVAILABLE);
      }
      product.decreaseQuantity(req.getReqQuantity());
      return ProductOrderResponse.builder()
          .id(product.getId())
          .name(product.getName())
          .quantity(product.getQuantity())
          .amount(product.getAmount())
          .build();
    }).toList();
  }


  /**
   * 주문 취소
   *
   * @param requests
   */
  @Transactional
  @MethodLogWriter
  public void cancelOrder(final List<ProductOrderRequest> requests) {
    log.debug("cancel Req {}", requests);
    //TODO ... 주문 후 나중에 상품이 지워 졌을시 문제...?
    requests.forEach((req) -> {
      Product product = productRepository.findById(req.getProductId())
          .orElseThrow(() -> new RestApiException(
              ProductError.PRODUCT_NOT_FOUND));
      product.increaseQuantity(req.getReqQuantity());
    });
  }


}
