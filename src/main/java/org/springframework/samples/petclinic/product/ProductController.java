package org.springframework.samples.petclinic.product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    
	private final ProductService productService;
    
    @Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
    
    @GetMapping(value = "/product/create")
	public String initProductForm(Product product, ModelMap model) {
    	List<ProductType> ls = productService.getAllProductTypes();
		model.addAttribute("product", product);
		model.addAttribute("productType", ls);
		return "products/createOrUpdateProductForm";
	}
    
    @PostMapping(value = "/product/create")
	public String processCreationForm(@Valid Product product, BindingResult result, ModelMap model) {	
    	List<ProductType> ls = productService.getAllProductTypes();
		if (result.hasErrors()) {
			model.put("product", product);
			model.addAttribute("productType", ls);
			return "products/createOrUpdateProductForm";
		}
		else {
                    try{
                    	this.productService.save(product);
                    }catch(DuplicateKeyException ex){
                        result.rejectValue("id", "duplicate", "already exists");
                        return "products/createOrUpdateProductForm";
                    }
                    return "welcome";
		}
	}

    
    
    
    
	/*
    @GetMapping(value = "/product/create")
	public String initCreationForm(Map<String, Object> model) {
		Product product = new Product();
		model.put("product", product);
		return "products/createOrUpdateProductForm";
	}

	@PostMapping(value = "/product/create")
	public String processCreationForm(@Valid Product product, BindingResult result) {
		if (result.hasErrors()) {
			return "products/createOrUpdateProductForm";
		}
		else {
			this.productService.save(product);
			return "redirect:/";
		}
	}

	@GetMapping(value = "/product/{productId}/edit")
	public String initUpdateForm(@PathVariable("productId") int productId, ModelMap model) {
		Product product = this.productService.getById(productId).get();
		model.put("product", product);
		return "products/createOrUpdateProductForm";
	}
	
	@PostMapping(value = "/product/{productId}/edit")
	public String processUpdateForm(@Valid Product product, BindingResult result,@PathVariable("productId") int productId, ModelMap model) {
		if (result.hasErrors()) {
			model.put("product", product);
			return "products/createOrUpdateProductForm";
		}
		else {
            Product productToUpdate=this.productService.getById(productId).get();
			BeanUtils.copyProperties(product, productToUpdate, "id","name","price","productType");                                                                                  
                    try {                    
                        this.productService.save(productToUpdate);             
                    } catch (DuplicateKeyException ex) {
                        result.rejectValue("name", "duplicate", "already exists");
                        return "products/createOrUpdateProductForm";
                    }
			return "redirect:/";
		}
	}
	*/
}
