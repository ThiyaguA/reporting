package com.reporting.service;

import com.reporting.entity.BankDto;
import com.reporting.entity.BankRequest;

import java.util.ArrayList;
import java.util.List;

public interface ReportingService {

    List<BankDto> getAllBanks() throws Exception;

    BankDto findById(int bankCode) throws Exception;

    BankDto save(BankRequest bankRequest) throws Exception;

    BankDto update(BankRequest bankRequest) throws Exception;

    String deleteById(int bankCode) throws Exception;
}
