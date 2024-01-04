package com.reporting.entity;

import lombok.Data;

import java.util.List;

@Data
public class BankDto {

    private int bankCode;
    private String bankName;
    private String bankAddress;
    private List<BranchDTO> branchDto;

}
