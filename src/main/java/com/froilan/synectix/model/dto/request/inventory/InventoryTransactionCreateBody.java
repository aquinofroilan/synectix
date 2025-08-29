package com.froilan.synectix.model.dto.request.inventory;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryTransactionCreateBody {

    @NotBlank(message = "Unit cost is required")
    private BigDecimal unitCost;

    @NotBlank(message = "Quantity is required")
    private Integer quantity;

    @NotBlank(message = "Transaction date is required")
    private Instant transactionDate;

    @NotBlank(message = "Reference number is required")
    private String referenceNumber;

    @Builder.Default
    private String notes = ""; // Optional notes field, default to empty string
}
