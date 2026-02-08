package com.bankName.loanApplication.infrastructure.rest.dto;

import com.bankName.loanApplication.domain.model.LoanRequestStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class LoanRequestDto {

    private String loadRequestId;
    private String applicantName;
    private BigDecimal amount;
    private String currency;
    private String dni;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;
    private LoanRequestStatus status;

    public LoanRequestDto( String loadRequestId, String applicantName, BigDecimal amount, String currency, String dni, LocalDateTime creationDate, LoanRequestStatus status) {
        this.loadRequestId = loadRequestId;
        this.applicantName = applicantName;
        this.amount = amount;
        this.currency = currency;
        this.dni = dni;
        this.creationDate = creationDate;
        this.status = status;
    }
}
