package io.whatap.assignment.product.order;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.whatap.assignment.cmm.aop.ExecutionTimeChecker;
import io.whatap.assignment.cmm.aop.MethodLogWriter;
import io.whatap.assignment.product.order.dto.ProductOrderRequest;
import io.whatap.assignment.product.order.dto.ProductOrderResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
@Tag(name="제품 주문 REST API", description = "제품 주문 CRUD용 API")
@MethodLogWriter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/order")
@Slf4j
public class ProductOrderController {

  private final ProductOrderService service;

  @ExecutionTimeChecker
  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<List<ProductOrderResponse>> requestOrder(
      @RequestBody final List<ProductOrderRequest> requests) {
    log.debug("re size {}", requests.size());
    return ResponseEntity.ok().body(service.doOrder(requests));
  }

  @ExecutionTimeChecker
  @DeleteMapping()
  public void cancelOrder(@RequestBody final List<ProductOrderRequest> requests) {
    service.cancelOrder(requests);
  }

  @ExecutionTimeChecker
  @PutMapping
  public ResponseEntity<List<ProductOrderResponse>> updateOrder(
      @RequestBody List<ProductOrderRequest> requests) {
    return ResponseEntity.ok().body(service.modifyOrder(requests));
  }


}
