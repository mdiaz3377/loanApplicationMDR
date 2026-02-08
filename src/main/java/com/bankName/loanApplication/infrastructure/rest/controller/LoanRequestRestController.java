package com.bankName.loanApplication.infrastructure.rest.controller;

import com.bankName.loanApplication.domain.model.LoanRequest;
import com.bankName.loanApplication.domain.model.LoanRequestStatus;
import com.bankName.loanApplication.infrastructure.rest.dto.LoanRequestDto;
import com.bankName.loanApplication.infrastructure.rest.mapper.LoanRequestDtoMapper;
import com.bankName.loanApplication.usecase.ILoanRequestUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/loan-requests")
@RestController
public class LoanRequestRestController {

    private final ILoanRequestUseCase loanRequestUseCase;

    @GetMapping("/{loadRequestId}")
    public ResponseEntity<LoanRequestDto> findByLoadRequestId(@PathVariable String loadRequestId) {
        LoanRequest loanRequest = loanRequestUseCase.findByLoadRequestId(loadRequestId);
        if(loanRequest == null) {
            return ResponseEntity.notFound().build();
        }else{
            LoanRequestDto loanRequestDto = LoanRequestDtoMapper.fromLoanRequestToDto(loanRequest);
            return ResponseEntity.ok(loanRequestDto);
        }
    }

    @PostMapping
    public ResponseEntity<LoanRequestDto> save(@RequestBody LoanRequestDto loanRequestDto){
        LoanRequest loanRequest = loanRequestUseCase.save(LoanRequestDtoMapper.fromDtoToLoanRequest(loanRequestDto));
        if(loanRequest == null) {
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(LoanRequestDtoMapper.fromLoanRequestToDto(loanRequest));
        }
    }

    @PatchMapping("/{loadRequestId}/{status}")
    public ResponseEntity<Void> updateStatus(@PathVariable String loadRequestId,@PathVariable String status){
        String result = loanRequestUseCase.updateStatus(loadRequestId, LoanRequestStatus.valueOf(status));
        return switch (result) {
            case "Status updated successfully" -> ResponseEntity.ok().build();
            case "Invalid status transition" -> ResponseEntity.status(HttpStatus.CONFLICT).build();
            default -> ResponseEntity.notFound().build();
        };
    }

    @GetMapping
    public ResponseEntity<List<LoanRequestDto>> findAll() {
        List<LoanRequest> loanRequests = loanRequestUseCase.findAll();

        if(loanRequests.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else{
            List<LoanRequestDto> loanRequestDtos = loanRequests.stream()
                    .map(LoanRequestDtoMapper::fromLoanRequestToDto)
                    .toList();
            return ResponseEntity.ok(loanRequestDtos);
        }
    }


}
