package com.example.onlineshop.service;

import com.example.onlineshop.dtos.OrderDTO;
import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.dtos.RetailerDTO;
import com.example.onlineshop.entity.Customer;
import com.example.onlineshop.entity.Retailer;
import com.example.onlineshop.mapper.RetailerMapper;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.repository.RetailerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RetailerService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RetailerRepository retailerRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    private Retailer dtoToRetailer(RetailerDTO retailerDTO){
        return Retailer.builder()
                .name(retailerDTO.getName())
                .email(retailerDTO.getEmail())
                .address(retailerDTO.getAddress())
                .products(retailerDTO.getProducts())
                .sold(retailerDTO.getSold())
                .build();
    }

    private RetailerDTO retailerToDTO(Retailer retailer){
        return  RetailerDTO.builder()
                .name(retailer.getName())
                .email(retailer.getEmail())
                .address((retailer.getAddress()))
                .products(retailer.getProducts())
                .sold(retailer.getSold())
                .build();

    }

    private RetailerDTO addProductToStock(ProductDTO productDTO, RetailerDTO retailerDTO){
        Retailer retailer = dtoToRetailer(retailerDTO);
        retailer.getProducts().add(productRepository.findByNameAndPriceAndDescription(productDTO.getName(), productDTO.getPrice(), productDTO.getDescription()));
        retailerRepository.save(retailer);
        log.info("Product added to stock: " + productDTO.toString());
        return retailerToDTO(retailer);
    }

    private RetailerDTO deleteProductFromStock(ProductDTO productDTO, RetailerDTO retailerDTO){
        Retailer retailer = dtoToRetailer(retailerDTO);
        retailer.getProducts().remove(productRepository.findByNameAndPriceAndDescription(productDTO.getName(), productDTO.getPrice(), productDTO.getDescription()));
        retailerRepository.save(retailer);
        log.info("Product deleted from stock: " + productDTO.toString());
        return retailerToDTO(retailer);
    }

    private Map<ProductDTO, Long> getStock (RetailerDTO retailerDTO){
        Retailer retailer = dtoToRetailer(retailerDTO);
        return productService.getSomeProducts(retailer.getProducts())
                .stream()
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting())
                );
    }

    private Map<ProductDTO, Long> getSold (RetailerDTO retailerDTO){
        Retailer retailer = dtoToRetailer(retailerDTO);
        return productService.getSomeProducts(retailer.getSold())
                .stream()
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting())
                );
    }

    public Integer authenticate(String email, String password) {
        String e = email;
        log.info(e);
        Retailer c = retailerRepository.findRetailerByEmail(e);
        if (c != null) {
            log.info(c.getPassword() + " - " + password);
            return (password.equals(c.getPassword())) ? c.getId() : -1;
        }
        return -1;

    }
    public RetailerDTO createRetailer(RetailerDTO retailerDTO) {
        Retailer retailer = dtoToRetailer(retailerDTO);
        retailerRepository.save(retailer);
        log.info("Retailer created: " + retailerDTO.toString());
        return retailerToDTO(retailer);
    }

    public List<RetailerDTO> getAllRetailers() {
        return retailerRepository.findAll()
                .stream()
                .map(this::retailerToDTO)
                .collect(Collectors.toList());
    }

    public RetailerDTO updateRetailer(RetailerDTO retailerDTO, Integer id) {
        Retailer tobesave = retailerRepository.getById(id);
        tobesave.setName(retailerDTO.getName());
        tobesave.setAddress(retailerDTO.getAddress());
        tobesave.setEmail(retailerDTO.getEmail());
        tobesave.setProducts(retailerDTO.getProducts());
        tobesave.setSold(retailerDTO.getSold());
        retailerRepository.save(tobesave);
        log.info("Retailer updated: " + id);
        return retailerToDTO(retailerRepository.getById(id));
    }

    public void deleteRetailer(Integer id) {

        retailerRepository.deleteById(id);
        log.info("Retaler deleted: " + id);
    }

    public RetailerDTO getRetailer(Integer id) {
        return retailerToDTO(retailerRepository.getById(id));
    }
}
