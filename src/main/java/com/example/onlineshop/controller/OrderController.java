package com.example.onlineshop.controller;

import com.example.onlineshop.dtos.OrderDTO;

import com.example.onlineshop.dtos.ProductDTO;
import com.example.onlineshop.enums.OrderStatus;
import com.example.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(path="/save")
    public OrderDTO createOrder(@RequestParam Integer customerId){
        return orderService.createOrder(customerId);
    }

    @GetMapping(path="/getall")
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping(path="/update")
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO, @RequestParam Integer id)
    {
        return orderService.updateOrder(orderDTO, id);
    }

    @PostMapping(path="/delete")
    public ResponseEntity<Void> deleteOrder(@RequestParam Integer id)
    {
        try{
            orderService.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(path="/getbystatus")
    public List<OrderDTO> getOrdersByStatus(@RequestParam OrderStatus orderStatus)
    {
        return orderService.getOrdersByStatus(orderStatus);
    }

    @GetMapping(path="/getbyaddress")
    public List<OrderDTO> getOrdersByAddress(@RequestParam String address)
    {
        return orderService.getOrdersByAddress(address);
    }

    @GetMapping(path="/get")
    public OrderDTO getOrder(@RequestParam Integer id ){
        return orderService.getOrder(id);
    }


}
