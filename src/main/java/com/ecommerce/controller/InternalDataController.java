package com.ecommerce.controller;


import com.ecommerce.model.ShopperData;
import com.ecommerce.service.InternalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * InternalDataController receives shoppers’ personalized
 * information and product metadata from our data team and storing it in a specified
 * database.
 *
 * @author  Veni T
 */
@RestController
@RequestMapping("/internalData")
public class InternalDataController {

    @Autowired
    private InternalDataService internalDataService;


    /**
     * saveShoppersInfo saves the shopper and its personalized products info into databases.
     * @param shopper ShopperData
     * @return ResponseEntity<String> information about the data action.
     */
    @PostMapping(value = "/shopper",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveShoppersInfo(@RequestBody ShopperData shopper){
        internalDataService.saveShopperDetails(shopper);
        return  ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.CREATED.value())).body("Data Saved Successfully");
    }



}
