package com.reporting.entity;

import lombok.Data;

import java.util.List;

@Data
public class BankRequest {

    private int bankCode;
    private String bankName;
    private String bankAddress;

}
