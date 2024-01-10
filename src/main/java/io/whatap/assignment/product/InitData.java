package io.whatap.assignment.product;

import io.whatap.assignment.product.domain.Product;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitData {

  private final ProductRepository productRepository;

  @PostConstruct
  public void initProduct() {

    final int makeCount = 100;
    final Random random = new Random();

    List<Product> makeList= IntStream.rangeClosed(1,makeCount).boxed().map((i) -> Product.builder().name("Product" + i).quantity(random.nextInt(1,10)).amount(random.nextInt(1,90)*1000).build()).toList();

    productRepository.saveAll(makeList);

  }
}
