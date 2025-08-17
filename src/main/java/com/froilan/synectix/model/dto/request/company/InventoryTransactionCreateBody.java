package com.froilan.synectix.model.dto.request.company;

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
    private Float quantityOnHand;

    @NotBlank(message = "Quantity allocated cannot be blank")
    private Float quantityAllocated;

    @NotBlank(message = "Quantity available cannot be blank")
    private Float quantityAvailable;

    @NotBlank(message = "Quantity on order cannot be blank")
    private Float quantityOnOrder;

    @NotBlank(message = "Average cost cannot be blank")
    private Float averageCost;

    @NotBlank(message = "Last cost cannot be blank")
    private Float lastCost;

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
    private String quantity;

    @NotBlank(message = "Unit costs cannot be blank")
    private Float unitCosts;

    @NotBlank(message = "Total costs cannot be blank")
    private Float totalCosts;

    @NotBlank(message = "Notes cannot be blank")
    private String notes;
}
