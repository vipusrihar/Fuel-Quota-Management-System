package com.se.Fuel_Quota_Management_System.service;

import com.se.Fuel_Quota_Management_System.model.FuelTransaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FuelTransactionService {

  public List<FuelTransaction> getAllTransaction();

  public String scanVehicleQR(String qrCode);

  public double fetchQuotaByVehicleId(Long vehicleId);

  public void pumpFuel(Long stationId, Long vehicleId, double amount);


}
