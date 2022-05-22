package com.example.onlineshop.controller;

import com.example.onlineshop.dtos.RetailerDTO;
import com.example.onlineshop.service.RetailerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/retailer")
@Slf4j
public class RetailerController {
    @Autowired
    private RetailerService retailerService;

    @PostMapping(path="/save")
    public RetailerDTO createRetailer(@RequestBody RetailerDTO retailerDTO){
        return retailerService.createRetailer(retailerDTO);
    }

    @GetMapping(path="/getall")
    public List<RetailerDTO> getAllRetailers(){
        return retailerService.getAllRetailers();
    }


    @GetMapping(path="/get")
    public RetailerDTO getRetailer(@RequestParam Integer id ){
        return retailerService.getRetailer(id);
    }


    @PostMapping(path="/update")
    public RetailerDTO updateRetailer(@RequestBody RetailerDTO retailerDTO, @RequestParam Integer id)
    {
        return retailerService.updateRetailer(retailerDTO, id);
    }

    @PostMapping(path="/delete")
    public ResponseEntity<Void> deleteRetailer(@RequestParam Integer id)
    {
        try{
            retailerService.deleteRetailer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


}
