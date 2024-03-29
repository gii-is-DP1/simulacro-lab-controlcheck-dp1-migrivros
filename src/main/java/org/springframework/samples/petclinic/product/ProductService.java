package org.springframework.samples.petclinic.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Transactional(readOnly = true)
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
	
	@Transactional(readOnly = true)
    public Optional<Product> getById(int Id){
        return null;
    }

	@Transactional(readOnly = true)
    public List<ProductType> getAllProductTypes() {
        return productRepository.findAllProductTypes();
    }
	
    public Product save(Product p){
        return productRepository.save(p);       
        
    }
    
    @Transactional(readOnly = true)
    public ProductType getProductType(String name) {
        return productRepository.findProductTypeByName(name);
    }
    
    @Transactional(readOnly = true)
    public List<Product> getProductsCheaperThan(double price){
    	return productRepository.findByPriceLessThan(price);
    }

    
}
