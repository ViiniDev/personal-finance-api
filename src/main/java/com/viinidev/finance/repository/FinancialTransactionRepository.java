package com.viinidev.finance.repository;

import com.viinidev.finance.domain.FinancialTransaction;
import com.viinidev.finance.domain.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, Long> {

    List<FinancialTransaction> findByTransactionDateBetweenOrderByTransactionDateDesc(LocalDate start, LocalDate end);

    List<FinancialTransaction> findByTypeAndTransactionDateBetweenOrderByTransactionDateDesc(TransactionType type, LocalDate start, LocalDate end);
}
