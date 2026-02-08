package com.bankName.loanApplication.usecase;

import com.bankName.loanApplication.domain.model.LoanRequest;
import com.bankName.loanApplication.domain.model.LoanRequestStatus;

import java.util.List;

public interface ILoanRequestUseCase {

    LoanRequest findByLoadRequestId(String loadRequestId);
    LoanRequest save(LoanRequest loanRequest);
    String updateStatus(String loadRequestId, LoanRequestStatus status);
    List<LoanRequest> findAll();

}
