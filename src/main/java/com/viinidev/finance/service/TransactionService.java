package com.viinidev.finance.service;

import com.viinidev.finance.domain.Category;
import com.viinidev.finance.domain.FinancialTransaction;
import com.viinidev.finance.domain.TransactionType;
import com.viinidev.finance.dto.MonthlySummaryResponse;
import com.viinidev.finance.dto.TransactionRequest;
import com.viinidev.finance.dto.TransactionResponse;
import com.viinidev.finance.repository.FinancialTransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class TransactionService {

    private final FinancialTransactionRepository transactionRepository;
    private final CategoryService categoryService;

    public TransactionService(FinancialTransactionRepository transactionRepository, CategoryService categoryService) {
        this.transactionRepository = transactionRepository;
        this.categoryService = categoryService;
    }

    public TransactionResponse create(TransactionRequest request) {
        Category category = categoryService.getById(request.categoryId());
        validateCategoryType(request.type(), category);
        FinancialTransaction transaction = new FinancialTransaction(
                request.description(),
                request.amount(),
                request.type(),
                request.transactionDate(),
                category
        );
        return TransactionResponse.from(transactionRepository.save(transaction));
    }

    public List<TransactionResponse> list(TransactionType type, LocalDate start, LocalDate end) {
        LocalDate startDate = start == null ? LocalDate.now().withDayOfMonth(1) : start;
        LocalDate endDate = end == null ? LocalDate.now() : end;

        List<FinancialTransaction> transactions = type == null
                ? transactionRepository.findByTransactionDateBetweenOrderByTransactionDateDesc(startDate, endDate)
                : transactionRepository.findByTypeAndTransactionDateBetweenOrderByTransactionDateDesc(type, startDate, endDate);

        return transactions.stream().map(TransactionResponse::from).toList();
    }

    public TransactionResponse update(Long id, TransactionRequest request) {
        FinancialTransaction transaction = getEntity(id);
        Category category = categoryService.getById(request.categoryId());
        validateCategoryType(request.type(), category);
        transaction.update(request.description(), request.amount(), request.type(), request.transactionDate(), category);
        return TransactionResponse.from(transactionRepository.save(transaction));
    }

    public void delete(Long id) {
        transactionRepository.delete(getEntity(id));
    }

    public MonthlySummaryResponse summary(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        List<FinancialTransaction> transactions = transactionRepository.findByTransactionDateBetweenOrderByTransactionDateDesc(
                yearMonth.atDay(1),
                yearMonth.atEndOfMonth()
        );

        BigDecimal income = sumByType(transactions, TransactionType.INCOME);
        BigDecimal expense = sumByType(transactions, TransactionType.EXPENSE);

        return new MonthlySummaryResponse(year, month, income, expense, income.subtract(expense));
    }

    private FinancialTransaction getEntity(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found."));
    }

    private BigDecimal sumByType(List<FinancialTransaction> transactions, TransactionType type) {
        return transactions.stream()
                .filter(transaction -> transaction.getType() == type)
                .map(FinancialTransaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void validateCategoryType(TransactionType type, Category category) {
        if (category.getType() != type) {
            throw new IllegalArgumentException("Category type must match transaction type.");
        }
    }
}
