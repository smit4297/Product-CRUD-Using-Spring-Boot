package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/productList")
	public List<Product> findAll() {
		return productService.findAll();
	}

	@GetMapping("/product/{pId}")
	public Product findById(@PathVariable Integer pId) throws Exception {
		Product e = productService.findById(pId);

		if (e == null)
			throw new Exception("Product ID : " + pId + " details not available in Repository..");

		return e;
	}

	@PostMapping("/saveProduct")
	public String saveProduct(@RequestBody Product product) {
		product.setProductId(0);;
		productService.saveOrUpdate(product);

		return "Product details added successfully!!!";
	}

	@PutMapping("/updateProduct")
	public String updateProduct(@RequestBody Product product) throws Exception {
	
		
		productService.saveOrUpdate(product);
		
		return "Product ID : "+ product.getProductId() + " Details updated Succesfully !!"; 
	}

	@DeleteMapping("product/{id}")
	public String deleteById(@PathVariable int id) {
		productService.deleteById(id);
		return "Product ID : " + id +"details  deleted !!!";
	}
}

