package com.ighor.shopping_cart.controller;

import com.ighor.shopping_cart.dto.request.AddProductRequest;
import com.ighor.shopping_cart.dto.request.UpdateCategoryRequest;
import com.ighor.shopping_cart.dto.request.UpdateProductRequest;
import com.ighor.shopping_cart.dto.response.ApiResponse;
import com.ighor.shopping_cart.entity.Category;
import com.ighor.shopping_cart.entity.Product;
import com.ighor.shopping_cart.exception.AlreadyExistsException;
import com.ighor.shopping_cart.exception.ResourceNotFoundException;
import com.ighor.shopping_cart.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

    private final ProductService productService;

    public  ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse("Products found", products));
    }

    @GetMapping("/product/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId){
        try {
            Product newProd = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Found", newProd));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: "+e.getMessage(), null));
        }
    }

    @GetMapping("/product/{productName}/product")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String productName){
        try {
            List<Product> newProds = productService.getProductsByName(productName);
            return ResponseEntity.ok(new ApiResponse("Found", newProds));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: "+e.getMessage(), null));
        }
    }

    //Need to implement CategoryDto, best practice
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest request){
        try {
            Product newProd = productService.addProduct(request);
            return ResponseEntity.ok(new ApiResponse("Successful addition", null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Error: "+e.getMessage(), null));
        }
    }

    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId){
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Deletion successful", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: "+e.getMessage(), null));
        }
    }

    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductRequest updateRequest){
        try {
            Product newProd = productService.updateProduct(updateRequest,productId);
            return ResponseEntity.ok(new ApiResponse("Deletion successful", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: "+e.getMessage(), null));
        }
    }




}
