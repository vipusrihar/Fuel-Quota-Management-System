package com.se.Fuel_Quota_Management_System.controller;

import com.se.Fuel_Quota_Management_System.DTO.FuelQuotaUpdateRequest;
import com.se.Fuel_Quota_Management_System.DTO.FuelTransactionDTO;
import com.se.Fuel_Quota_Management_System.model.FuelTransaction;
import com.se.Fuel_Quota_Management_System.service.FuelTransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class FuelTransactionController {

    @Autowired
   private FuelTransactionServiceImpl fuelTransactionService;



    //initiate a new fuel transaction
    @PostMapping("/start")
   public ResponseEntity<FuelTransaction> startTransaction(@RequestParam Long vehicleId,
                                                           @RequestParam Double amount,
                                                           @RequestParam Long stationId) {
       try {
           FuelTransaction fuelTransaction = fuelTransactionService.startTransaction(vehicleId, amount, stationId);
           return ResponseEntity.ok(fuelTransaction);
       } catch (IllegalArgumentException e) {
           return ResponseEntity.badRequest().body(null);
       }
   }




    // get all transactions for a specific vehicle
    @Transactional
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<FuelTransactionDTO>> getTransactionsByVehicleId(@PathVariable Long vehicleId) {
        try {
            List<FuelTransactionDTO> transactions = fuelTransactionService.getTransactionsByVehicleId(vehicleId);
            return ResponseEntity.ok(transactions);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }





    @PostMapping("/updateFuelQuota")
    public ResponseEntity<String> updateFuelInventory(@RequestBody FuelQuotaUpdateRequest request) {
        try {
            // Call the service method to update the fuel inventory
            fuelTransactionService.DeductFuelQuotaWhenPumpFuel(request.getStationId(), request.getAmount(), request.getVehicleId());
            return ResponseEntity.ok("Fuel updated successfully.");
        } catch (Exception e) {
            // Handle any exceptions and return an error response
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }




}
