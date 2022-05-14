package com.example.onlineshop.service;

import com.example.onlineshop.dtos.OrderDTO;
import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.dtos.RetailerDTO;
import com.example.onlineshop.entity.Retailer;
import com.example.onlineshop.mapper.RetailerMapper;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.repository.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RetailerService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RetailerRepository retailerRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RetailerMapper retailerMapper;

    private Retailer dtoToRetailer(RetailerDTO retailerDTO){ return  retailerMapper.dtoToEntity(retailerDTO);}

    private RetailerDTO retailerToDTO(Retailer retailer){
        return  RetailerDTO.builder()
                .name(retailer.getName())
                .email(retailer.getEmail())
                .products(retailer.getProducts())
                .sold(retailer.getSold())
                .build();

    }

    private RetailerDTO addProductToStock(ProductDTO productDTO, RetailerDTO retailerDTO){
        Retailer retailer = dtoToRetailer(retailerDTO);
        retailer.getProducts().add(productRepository.findByNameAndPriceAndDescription(productDTO.getName(), productDTO.getPrice(), productDTO.getDescription()));
        retailerRepository.save(retailer);
        return retailerToDTO(retailer);
    }

    private RetailerDTO deleteProductFromStock(ProductDTO productDTO, RetailerDTO retailerDTO){
        Retailer retailer = dtoToRetailer(retailerDTO);
        retailer.getProducts().remove(productRepository.findByNameAndPriceAndDescription(productDTO.getName(), productDTO.getPrice(), productDTO.getDescription()));
        retailerRepository.save(retailer);
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

    private Map<OrderDTO, Long> getSold (RetailerDTO retailerDTO){
        Retailer retailer = dtoToRetailer(retailerDTO);
        return orderService.getSomeOrders(retailer.getSold())
                .stream()
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting())
                );
    }


    public RetailerDTO createRetailer(RetailerDTO retailerDTO) {
        Retailer retailer = dtoToRetailer(retailerDTO);
        retailerRepository.save(retailer);
        return retailerToDTO(retailer);
    }

    public List<RetailerDTO> getAllRetailers() {
        return retailerRepository.findAll()
                .stream()
                .map(this::retailerToDTO)
                .collect(Collectors.toList());
    }

    public RetailerDTO updateRetailer(RetailerDTO retailerDTO, Integer id) {
        retailerRepository.getById(id).setName(retailerDTO.getName());
        retailerRepository.getById(id).setAddress(retailerDTO.getAddress());
        retailerRepository.getById(id).setEmail(retailerDTO.getEmail());
        retailerRepository.getById(id).setProducts(retailerDTO.getProducts());
        retailerRepository.getById(id).setSold(retailerDTO.getSold());
        return retailerToDTO(retailerRepository.getById(id));
    }

    public void deleteRetailer(Integer id) {
        retailerRepository.deleteById(id);
    }

    public RetailerDTO getRetailer(Integer id) {
        return retailerToDTO(retailerRepository.getById(id));
    }
}