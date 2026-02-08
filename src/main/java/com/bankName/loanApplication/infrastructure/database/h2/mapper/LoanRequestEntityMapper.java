package com.bankName.loanApplication.infrastructure.database.h2.mapper;

import com.bankName.loanApplication.domain.model.LoanRequest;
import com.bankName.loanApplication.infrastructure.database.h2.entity.LoanRequestEntity;

public class LoanRequestEntityMapper {

    public static LoanRequest fromEntityToLoanRequest(LoanRequestEntity loanRequestEntity) {
        return new LoanRequest(
                loanRequestEntity.getLoadRequestId(),
                loanRequestEntity.getApplicantName(),
                loanRequestEntity.getAmount(),
                loanRequestEntity.getCurrency(),
                loanRequestEntity.getDni(),
                loanRequestEntity.getCreationDate(),
                loanRequestEntity.getStatus()
        );
    }

    public static LoanRequestEntity fromLoanRequestToEntity(LoanRequest loanRequest) {
        return new LoanRequestEntity(
                loanRequest.getLoadRequestId(),
                loanRequest.getApplicantName(),
                loanRequest.getAmount(),
                loanRequest.getCurrency(),
                loanRequest.getDni(),
                loanRequest.getCreationDate(),
                loanRequest.getStatus()
        );
    }
}
