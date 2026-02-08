package com.bankName.loanApplication.domain.repository;

import com.bankName.loanApplication.domain.model.LoanRequest;
import com.bankName.loanApplication.domain.model.LoanRequestStatus;

import java.util.List;

public interface LoanRequestRepositoryPort {

    LoanRequest findByLoadRequestId(String loadRequestId);
    LoanRequest save(LoanRequest loanRequest);
    List<LoanRequest> findAll();

}
