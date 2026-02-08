package com.bankName.loanApplication.infrastructure.database.h2.entity;

import com.bankName.loanApplication.domain.model.LoanRequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "loanRequest")
public class LoanRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loadRequestId", unique = true, nullable = false)
    private String loadRequestId;

    @Column(name = "applicantName", nullable = false)
    private String applicantName;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "dni", nullable = false)
    private String dni;

    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LoanRequestStatus status;

    public LoanRequestEntity(String loadRequestId,String applicantName, BigDecimal amount, String currency, String dni, LocalDateTime creationDate, LoanRequestStatus status) {
        this.loadRequestId = loadRequestId;
        this.applicantName = applicantName;
        this.amount = amount;
        this.currency = currency;
        this.dni = dni;
        this.creationDate = creationDate;
        this.status = status;
    }
}
