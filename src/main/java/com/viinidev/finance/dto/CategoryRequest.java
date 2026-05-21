package com.viinidev.finance.dto;

import com.viinidev.finance.domain.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @NotBlank String name,
        @NotNull TransactionType type
) {
}
