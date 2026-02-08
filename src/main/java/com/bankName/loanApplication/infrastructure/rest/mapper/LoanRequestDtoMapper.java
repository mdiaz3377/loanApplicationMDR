package com.bankName.loanApplication.infrastructure.rest.mapper;

import com.bankName.loanApplication.domain.model.LoanRequest;
import com.bankName.loanApplication.infrastructure.rest.dto.LoanRequestDto;

public class LoanRequestDtoMapper {

    public static LoanRequestDto fromLoanRequestToDto(LoanRequest loanRequest) {
        return new LoanRequestDto(
                loanRequest.getLoadRequestId(),
                loanRequest.getApplicantName(),
                loanRequest.getAmount(),
                loanRequest.getCurrency(),
                loanRequest.getDni(),
                loanRequest.getCreationDate(),
                loanRequest.getStatus()
        );
    }

    public static LoanRequest fromDtoToLoanRequest(LoanRequestDto loanRequestDto) {
        return new LoanRequest(
                loanRequestDto.getLoadRequestId(),
                loanRequestDto.getApplicantName(),
                loanRequestDto.getAmount(),
                loanRequestDto.getCurrency(),
                loanRequestDto.getDni(),
                loanRequestDto.getCreationDate(),
                loanRequestDto.getStatus()
        );
    }
}
