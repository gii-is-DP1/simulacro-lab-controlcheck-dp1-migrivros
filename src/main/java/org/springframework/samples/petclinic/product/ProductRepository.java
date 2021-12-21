package org.springframework.samples.petclinic.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository  extends CrudRepository<Product, Integer>{
	
	@Query("SELECT product FROM Product product")
    List<Product> findAll();
	
	@Query("SELECT productType FROM ProductType productType")
    List<ProductType> findAllProductTypes();
	
	@Query("SELECT product FROM Product product WHERE product.id = ?1")
    Optional<Product> findById(int id);
    
	@Query("SELECT product FROM Product product ")
    Product findByName(String name);
    
    Product save(Product p);
}
