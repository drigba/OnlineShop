package com.example.onlineshop.controller;

import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.enums.ProductType;
import com.example.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(path="/save")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        return productService.createProduct(productDTO);
    }

    @GetMapping(path="/getall")
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping(path="/get")
    public ProductDTO getProduct(@RequestParam Integer id ){
        return productService.getProduct(id);
    }

    @PostMapping(path="/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO, @RequestParam Integer id)
    {
        return productService.updateProduct(productDTO, id);
    }

    @PostMapping(path="/delete")
    public ResponseEntity<Void> deleteProduct(@RequestParam Integer id)
    {
        try{
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(path="/getbytype")
    public List<ProductDTO> getProductsByType(@RequestParam ProductType productType)
    {
        return productService.getProductsByType(productType);
    }

    @GetMapping(path="/getbyname")
    public List<ProductDTO> getProductsByName(@RequestParam String name)
    {
        return productService.getProductByName(name);
    }

    @GetMapping(path="/sortbyprice")
    public List<ProductDTO> sortByPrice(@RequestParam boolean isFiltered, @RequestParam ProductType productType,@RequestParam boolean isReverse){
        if(isFiltered)
        {
            return productService.sortProductsByPrice(productService.getProductsByType(productType), isReverse);
        }
        else{
            return productService.sortProductsByPrice(getAllProducts(), isReverse);
        }
    }
}
