package com.viinidev.finance.dto;

import com.viinidev.finance.domain.Category;
import com.viinidev.finance.domain.TransactionType;

public record CategoryResponse(Long id, String name, TransactionType type) {

    public static CategoryResponse from(Category category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getType());
    }
}
