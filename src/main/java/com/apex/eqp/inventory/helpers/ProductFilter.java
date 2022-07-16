package com.apex.eqp.inventory.helpers;

import com.apex.eqp.inventory.entities.Product;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductFilter {

    private final List<String> recalledProducts;

    public ProductFilter(List<String> recalledProducts) {
        if (recalledProducts == null) recalledProducts = new ArrayList<>();

        this.recalledProducts = recalledProducts;
    }

    public List<Product> removeRecalled(Collection<Product> allProduct) {
        // return allProduct.stream().filter(this::filterByName).collect(Collectors.toList());
    	return allProduct.stream().filter(this::isNotOnRecalled).collect(Collectors.toList());
    }
      
    // Better name than filterByName
    private boolean isNotOnRecalled(Product product) {
    	if (recalledProducts.stream().filter(p-> 
    	          { 
    	        	  if (p.equals(product.getName())) 
    	        		return true;
    	        	  else 
    	        		return false;
    	          }).findAny().isPresent())
    	   return false;
    	else
    	   return true;
    }

    // Removed static here
    private boolean filterByName(Product product) {
    	if (recalledProducts.stream().filter(p-> 
    	          { 
    	        	  if (p.equals(product.getName())) 
    	        		return true;
    	        	  else 
    	        		return false;
    	          }).findAny().isPresent())
    	   return false;
    	else
    	   return true;
    }
}
