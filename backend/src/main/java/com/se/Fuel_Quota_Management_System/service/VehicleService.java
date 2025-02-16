package com.se.Fuel_Quota_Management_System.service;

import com.se.Fuel_Quota_Management_System.DTO.logs.VehicleOwnerLogDTO;
import com.se.Fuel_Quota_Management_System.model.FuelTransaction;
import com.se.Fuel_Quota_Management_System.model.Vehicle;

import java.util.List;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Vehicle registerVehicle(VehicleOwnerLogDTO vehicleOwnerLogDTO);

    Vehicle getVehicleByNumber(String vehicleNumber);

    Vehicle updateVehicle(Vehicle vehicle);

    double calculateFuelQuota(String vehicleType);

    public String generateQrCode(String qrCodeId);


    String getFuelTypeByVehicleId(Long vehicleId);

    public void updateVehicleFuelQuota(String qrCodeId, double amount);


    Vehicle findVehicleByOwnerLog(Long loginid);

    Optional<Vehicle> getVehicleById(Long vehicleId);

    public List<FuelTransaction> getFuelTransactions(Long vehicleId);

}