package org.springframework.samples.petclinic.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

	@Transactional(readOnly = true)
    public List<Product> getProductsCheaperThan(double price) {
        return null;
    }

	@Transactional(readOnly = true)
    public ProductType getProductType(String typeName) {
        return productRepository.findByName(typeName);
    }
	
    public Product save(Product p){
        return null;       
    }
    

    
}
