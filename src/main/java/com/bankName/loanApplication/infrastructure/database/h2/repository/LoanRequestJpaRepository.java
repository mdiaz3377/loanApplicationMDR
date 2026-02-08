package com.bankName.loanApplication.infrastructure.database.h2.repository;

import com.bankName.loanApplication.domain.model.LoanRequest;
import com.bankName.loanApplication.infrastructure.database.h2.entity.LoanRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("loanRequestJpaRepository")
public interface LoanRequestJpaRepository extends JpaRepository<LoanRequestEntity, UUID> {

    Optional<LoanRequestEntity> findByLoadRequestId(String loadRequestId);
    LoanRequestEntity save(LoanRequest loanRequest);
    List<LoanRequestEntity> findAll();



}
