package com.example.onlineshop.service;

import com.example.onlineshop.TestEnvContainer;
import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.dtos.RetailerDTO;
import com.example.onlineshop.entity.Order;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.entity.Retailer;
import com.example.onlineshop.enums.OrderStatus;
import com.example.onlineshop.enums.ProductType;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.repository.OrderRepository;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.repository.RetailerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = TestEnvContainer.class)
class RetailerServiceTest {

    @Autowired
    private RetailerService retailerService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private RetailerRepository retailerRepository;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    RetailerDTO retailerDTO = new RetailerDTO();
    Retailer retailer = new Retailer();
    ProductDTO productDTO = new ProductDTO();
    List<Product> _prod = new ArrayList<>();
    Product _product = new Product(1,"termek1",200, ProductType.ELECTRONICS,"t1_description",20);
    List<Order> _orderlist = new ArrayList<>();
    Order order = new Order();
    List<Retailer> _retailers = new ArrayList<>();
    List<RetailerDTO> _retailersDTO = new ArrayList<>();

    @BeforeEach
    public void setup(){
        _prod.add(_product);

        order.setId(1);
        order.setPrice(200);
        order.setOrderStatus(OrderStatus.DELIVERED);
        order.setFromAddress("BullStreet");
        order.setAddress("BearStreet");
        order.setProductList(_prod);

        _orderlist.add(order);

        productDTO.setName("termek1");
        productDTO.setPrice(200);
        productDTO.setProductType(ProductType.ELECTRONICS);
        productDTO.setDescription("t1_description");
        productDTO.setPopularity(20);

        retailerDTO.setProducts(_prod);
        retailerDTO.setName("Stratton Oakmont");
        retailerDTO.setEmail("belfort@stratton.com");
        retailerDTO.setAddress("Wall Street");

        retailer.setName("Stratton Oakmont");
        retailer.setEmail("belfort@stratton.com");
        retailer.setAddress("Wall Street");
        retailer.setProducts(_prod);
        retailer.setSold(_orderlist);

        retailerDTO.setSold(_orderlist);

        _retailersDTO.add(retailerDTO);

        _retailers.add(retailer);


    }

    @Test
    void createRetailer() {
        //when(this.productRepository.findByNameAndPriceAndDescription(productDTO.getName(),productDTO.getPrice(),productDTO.getDescription())).thenReturn(_product);
        when(this.retailerRepository.save(retailer)).thenReturn(retailer);
        assertThat(this.retailerService.createRetailer(retailerDTO)).isEqualTo(retailerDTO);

    }

    @Test
    void getAllRetailers() {
        when(this.retailerRepository.findAll()).thenReturn(_retailers);
        assertThat(this.retailerService.getAllRetailers()).isEqualTo(_retailersDTO);
    }

    @Test
    void updateRetailer() {
        RetailerDTO retailerDTO2 = new RetailerDTO();
        retailerDTO2.setProducts(_prod);
        retailerDTO2.setName("Stratton Oakmont Island");
        retailerDTO2.setEmail("belfort@stratton.com");
        retailerDTO2.setAddress("Wall Street");
        when(this.retailerRepository.getById(0)).thenReturn(retailer);
        assertThat(this.retailerService.updateRetailer(retailerDTO2,0)).isEqualTo(retailerDTO2);
    }

    @Test
    void deleteRetailer() {
    }

    @Test
    void getRetailer() {
        when(this.retailerRepository.getById(0)).thenReturn(retailer);
        assertThat(this.retailerService.getRetailer(0)).isEqualTo(retailerDTO);
    }
}