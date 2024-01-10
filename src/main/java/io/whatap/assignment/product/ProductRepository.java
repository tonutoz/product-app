package io.whatap.assignment.product;

import io.whatap.assignment.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    public boolean existsByName(final String name);

}
