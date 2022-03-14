package com.example.onlineshop.service;

import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.mapper.ProductMapper;
import com.example.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    private Product dtoToProduct(ProductDTO productDTO){
        return productMapper.dtoToEntity(productDTO);
    }

    private ProductDTO productToDTO(Product product){
        return ProductDTO.builder()
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
        return productToDTO(product);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::productToDTO)
                .collect(Collectors.toList());
    }
}
