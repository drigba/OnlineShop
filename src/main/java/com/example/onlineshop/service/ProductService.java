package com.example.onlineshop.service;

import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.enums.ProductType;
import com.example.onlineshop.mapper.ProductMapper;
import com.example.onlineshop.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private Product dtoToProduct(ProductDTO productDTO){
        return Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .productType(productDTO.getProductType())
                .description(productDTO.getDescription())
                .popularity(productDTO.getPopularity())
                .build();
    }

    private ProductDTO productToDTO(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .productType(product.getProductType())
                .description(product.getDescription())
                .popularity(product.getPopularity())
                .build();
    }

    public ProductDTO createProduct(ProductDTO productDTO){
        Product product = dtoToProduct(productDTO);
        productRepository.save(product);
        log.info("Product created: " + productDTO.toString());
        return productToDTO(product);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::productToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO updateProduct(ProductDTO productDTO, Integer id) {
        Product tobesaved = productRepository.getById(id);

        tobesaved.setName(productDTO.getName());
        tobesaved.setPrice(productDTO.getPrice());
        tobesaved.setProductType(productDTO.getProductType());
        tobesaved.setDescription(productDTO.getDescription());
        tobesaved.setPopularity(productDTO.getPopularity());
        productRepository.save(tobesaved);
        log.info("Product updated: " + id);
        return productToDTO(productRepository.getById(id));
    }

    public void deleteProduct(Integer id) {

        productRepository.deleteById(id);
        log.info("Product deleted: " + id);
    }

    public List<ProductDTO> getSomeProducts(List<Product> products) {
        return products
                .stream()
                .map(this::productToDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByType(ProductType type) {
        return productRepository.findByProductType(type)
                .stream()
                .map(this::productToDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> sortProductsByPrice(List<ProductDTO> productDTOList, boolean isReverse){
        if(!isReverse)
        {
            return productDTOList.stream()
                    .sorted(Comparator.comparingInt(ProductDTO::getPrice))
                    .collect(Collectors.toList());
        }
        else{
            return productDTOList.stream()
                    .sorted(Comparator.comparingInt(ProductDTO::getPrice).reversed())
                    .collect(Collectors.toList());
        }
    }

    public ProductDTO getProduct(Integer id) {

        return productToDTO(productRepository.getById(id));
    }


    public List<ProductDTO> getProductByName(String s) {
        return productRepository.findByNameStartingWith(s)
                .stream()
                .map(this::productToDTO)
                .collect(Collectors.toList());
    }
}

