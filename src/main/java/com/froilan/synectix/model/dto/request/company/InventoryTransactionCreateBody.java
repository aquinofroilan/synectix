package com.froilan.synectix.model.dto.request.company;

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
    @NotBlank(message = "Quantity on hand cannot be blank")
    private BigDecimal quantityOnHand;

    @NotBlank(message = "Quantity allocated cannot be blank")
    private BigDecimal quantityAllocated;

    @NotBlank(message = "Quantity available cannot be blank")
    private BigDecimal quantityAvailable;

    @NotBlank(message = "Quantity on order cannot be blank")
    private BigDecimal quantityOnOrder;

    @NotBlank(message = "Average cost cannot be blank")
    private BigDecimal averageCost;

    @NotBlank(message = "Last cost cannot be blank")
    private BigDecimal lastCost;

    @NotBlank(message = "Location code cannot be blank")
    private String locationCode;

    @NotBlank(message = "Lot number cannot be blank")
    private String lotNumber;

    @NotBlank(message = "Serial number cannot be blank")
    private String serialNumber;

    @NotBlank(message = "Expiration date cannot be blank")
    private Instant expirationDate;

    @NotBlank(message = "Last counted date cannot be blank")
    private Instant lastCountedDate;

    @NotBlank(message = "Last movement date cannot be blank")
    private Instant lastMovementDate;

    @NotBlank(message = "Transaction type cannot be blank")
    private String transactionType; // TODO: Define valid transaction types: "IN", "OUT", "ADJUSTMENT"

    @NotBlank(message = "Transaction reason cannot be blank")
    private String transactionReason;

    @NotBlank(message = "Quantity cannot be blank")
    private BigDecimal quantity;

    @NotBlank(message = "Unit costs cannot be blank")
    private BigDecimal unitCosts;

    @NotBlank(message = "Total costs cannot be blank")
    private BigDecimal totalCosts;

    @NotBlank(message = "Notes cannot be blank")
    private String notes;
}
