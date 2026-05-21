package com.viinidev.finance.dto;

import com.viinidev.finance.domain.FinancialTransaction;
import com.viinidev.finance.domain.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(
        Long id,
        String description,
        BigDecimal amount,
        TransactionType type,
        LocalDate transactionDate,
        CategoryResponse category
) {

    public static TransactionResponse from(FinancialTransaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getTransactionDate(),
                CategoryResponse.from(transaction.getCategory())
        );
    }
}
