package com.bankName.loanApplication.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class LoanRequest {

    private String loadRequestId;
    private String applicantName;
    private BigDecimal amount;
    private String currency;
    private String dni;
    private LocalDateTime creationDate;
    private LoanRequestStatus status;

    public boolean canTransitionTo(LoanRequestStatus next) {
        switch (this.status) {
            case LoanRequestStatus.PENDING: return next == LoanRequestStatus.APPROVED || next == LoanRequestStatus.REJECTED;
            case LoanRequestStatus.APPROVED: return next == LoanRequestStatus.CANCELED;
            default: return false;
        }
    }

}
