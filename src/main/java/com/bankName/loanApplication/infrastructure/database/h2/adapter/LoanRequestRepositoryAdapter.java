package com.bankName.loanApplication.infrastructure.database.h2.adapter;

import com.bankName.loanApplication.domain.model.LoanRequest;
import com.bankName.loanApplication.domain.repository.LoanRequestRepositoryPort;
import com.bankName.loanApplication.infrastructure.database.h2.entity.LoanRequestEntity;
import com.bankName.loanApplication.infrastructure.database.h2.mapper.LoanRequestEntityMapper;
import com.bankName.loanApplication.infrastructure.database.h2.repository.LoanRequestJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class LoanRequestRepositoryAdapter implements LoanRequestRepositoryPort {

    private final LoanRequestJpaRepository loanRequestJpaRepository;

    @Override
    public LoanRequest findByLoadRequestId(String loadRequestId) {
        return loanRequestJpaRepository.findByLoadRequestId(loadRequestId)
                .map(LoanRequestEntityMapper::fromEntityToLoanRequest)
                .orElseThrow(() ->new NoSuchElementException("Loan request not found with id: " + loadRequestId));
    }

    @Override
    public LoanRequest save(LoanRequest loanRequest) {
        LoanRequestEntity loanRequestSaved = loanRequestJpaRepository.save(LoanRequestEntityMapper.fromLoanRequestToEntity(loanRequest));
        return LoanRequestEntityMapper.fromEntityToLoanRequest(loanRequestSaved);
    }

    @Override
    public List<LoanRequest> findAll() {
        return loanRequestJpaRepository.findAll().stream()
                .map(LoanRequestEntityMapper::fromEntityToLoanRequest)
                .toList();
    }
}
