package com.reporting.service;

import com.reporting.entity.BankDto;
import com.reporting.entity.BankRequest;
import com.reporting.entity.BranchDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ReportingServiceImpl implements ReportingService {

    @Value("${bank.url}")
    private String bankUrl;

    @Override
    public List<BankDto> getAllBanks() throws Exception {

        log.info("Inside the ReportingServiceImpl.getAllBanks , url:{}",bankUrl);

        WebClient webClient = WebClient.create(bankUrl);
        Flux<BankDto> bankDtoFlux = webClient.get().retrieve().bodyToFlux(BankDto.class);

        List<BankDto> bankDtos = bankDtoFlux.collectList().block();
        log.info("Bank details, BankList:{}", bankDtos);

        return bankDtos;
    }

    @Override
    public BankDto findById(int bankCode) throws Exception {

        log.info("Inside the ReportingServiceImpl.findById Bank Code:{}",bankCode);

        WebClient webClient = WebClient.create(bankUrl + "/" + bankCode);

        Mono<BankDto> bankDtoMono = webClient.get().retrieve().bodyToMono(BankDto.class);

        BankDto bankDtosList =bankDtoMono.block();

        log.info("End of ReportingServiceImpl.findById");
        return bankDtosList;
    }

    @Override
    public BankDto save(BankRequest bankRequest) throws Exception {

        log.info("Inside the BankServiceImpl.save Bank Request:{}",bankRequest);

        WebClient webClient = WebClient.create(bankUrl);

        Mono<BankDto> bankDtoMono = webClient.post().body(Mono.just(bankRequest) , BankRequest.class)
                .retrieve()
                .bodyToMono(BankDto.class);

        BankDto bankDto = bankDtoMono.block();

        log.info("End of BankServiceImpl.save");

        return bankDto;
    }

    @Override
    public BankDto update(BankRequest bankRequest) throws Exception {

        log.info("Inside the BankServiceImpl.update Bank Details:{}",bankRequest);

        WebClient webClient = WebClient.create(bankUrl);

        Mono<BankDto> bankDtoMono = webClient.put()
                .body(Mono.just(bankRequest) , BankRequest.class)
                .retrieve()
                .bodyToMono(BankDto.class);

        BankDto bankDto = bankDtoMono.block();

        log.info("End of BankServiceImpl.update");

        return bankDto;
    }

    @Override
    public String deleteById(int bankCode) throws Exception {
        log.info("Inside the BankServiceImpl.deleteById");

        WebClient webClient = WebClient.create(bankUrl + "/" + bankCode);

        Mono<String> mono = webClient.delete()
                .retrieve()
                .bodyToMono(String.class);

        String response = mono.block();

        log.info("End of BankServiceImpl.deleteById");

        return response;
    }
}