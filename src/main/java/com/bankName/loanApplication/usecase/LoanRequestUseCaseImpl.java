package com.bankName.loanApplication.usecase;

import com.bankName.loanApplication.domain.model.LoanRequest;
import com.bankName.loanApplication.domain.model.LoanRequestStatus;
import com.bankName.loanApplication.domain.repository.LoanRequestRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class LoanRequestUseCaseImpl implements ILoanRequestUseCase{

    private final LoanRequestRepositoryPort loanRequestRepositoryPort;

    @Override
    public LoanRequest findByLoadRequestId(String loadRequestId){
        return loanRequestRepositoryPort.findByLoadRequestId(loadRequestId);
    }

    @Override
    public LoanRequest save(LoanRequest loanRequest) {
        return loanRequestRepositoryPort.save(loanRequest);
    }

    @Override
    public String updateStatus(String loadRequestId, LoanRequestStatus status) {
        String result = "";
        LoanRequest loanRequest = loanRequestRepositoryPort.findByLoadRequestId(loadRequestId);
        if (loanRequest != null) {
            if(loanRequest.canTransitionTo(status)){
                loanRequest.setStatus(status);
                loanRequestRepositoryPort.save(loanRequest);
                result = "Status updated successfully";
            }else{
                result = "Invalid status transition";
            }
        }else{
            result = "Loan request not found with id: " + loadRequestId;
        }
        return result;
    }

    @Override
    public List<LoanRequest> findAll() {
        return loanRequestRepositoryPort.findAll();
    }
}
