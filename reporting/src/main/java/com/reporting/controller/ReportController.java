package com.reporting.controller;

import com.reporting.entity.BankDto;
import com.reporting.entity.BankRequest;
import com.reporting.service.ReportingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("api/v1/reporting")
public class ReportController {

    @Autowired
    ReportingService bankService;

    @GetMapping
    public ResponseEntity<List<BankDto>> getAllBanks(){

        List<BankDto> bankDto = null;
        try {
            log.info("Inside the ReportController.getAllBanks");
            bankDto = bankService.getAllBanks();
        }
        catch (Exception e){
            log.error("Not Found ",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("End of ReportController.getAllBanks");
        return new ResponseEntity<>( bankDto , HttpStatus.OK);
    }

    @GetMapping("/{bankCode}")
    public ResponseEntity<BankDto> getBankDetailsById(@PathVariable int bankCode){

        if(bankCode < 0){
            log.error("Code is not valid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        BankDto bankDtos = null;

        try {
            log.info("Inside the ReportController.getBankDetailsById");
            bankDtos = bankService.findById(bankCode);
        }
        catch (Exception e){
            log.error("Not Found" , e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.info("End of ReportController.getBankDetailsById");
        return new ResponseEntity<>(bankDtos,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BankDto> saveBankDetails(@RequestBody BankRequest bankRequest){

        if(Objects.isNull(bankRequest)){
            log.error("Bank Details not found");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        BankDto bankDto = null;

        try {
            bankDto = bankService.save(bankRequest);
            log.info("Inside the ReportController.saveBankDetails");
        }
        catch (Exception e){
            log.error("Not Found" , e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.info("Inside the ReportController.saveBankDetails");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BankDto> updateBankDetails(@RequestBody BankRequest bankRequest){

        if(Objects.isNull(bankRequest)){
            log.error("Bank Details not found");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        BankDto bankDto = null;

        try {
            bankDto = bankService.update(bankRequest);
            log.info("Inside the ReportController.updateBankDetails");
        }
        catch (Exception e){
            log.error("Not Found" , e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.info("Inside the ReportController.updateBankDetails");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{bankCode}")
    public ResponseEntity<String> deleteBankById(@PathVariable int bankCode){
        log.info("Inside the ReportController.deleteBankById");

        if(bankCode < 1){
            log.error("Provided bank Code is Invalid:{}",bankCode);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String response = null;

        try {
            response = bankService.deleteById(bankCode);
            log.info("Performed :{}",response);
        }
        catch (Exception e){
            log.info("Error during the Execution ",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of ReportController.deleteBankById");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
