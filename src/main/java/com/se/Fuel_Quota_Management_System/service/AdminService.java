package com.se.Fuel_Quota_Management_System.service;

//import com.se.Fuel_Quota_Management_System.model.AdminLog;
import com.se.Fuel_Quota_Management_System.model.Vehicle;
import com.se.Fuel_Quota_Management_System.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private VehicleRepository vehicleRepository;

//    @Autowired
//    private AdminLogRepository adminLogRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
    }

    /**
     * Get vehicles by their type (e.g., "Car", "Truck").
     *
     * @param vehicleType Type of vehicle
     * @return List of vehicles matching the type
     */
    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> vehicles = vehicleRepository.findByVehicleType(vehicleType);
        if (vehicles.isEmpty()) {
            throw new RuntimeException("Vehicle not found with Type: " + vehicleType);
        }
        return vehicles;
    }

    /**
     * Get vehicles by owner name.
     *
     * @param ownerName Name of the vehicle owner
     * @return List of vehicles registered under the owner's name
     */
    public Optional<Vehicle> getVehiclesByOwner(String ownerName) {
        return vehicleRepository.findByOwnerName(ownerName);
    }
    /**
     * Update vehicle details by ID.
     *
     * @param id Vehicle ID
     * @param updatedVehicle Updated vehicle details
     * @return Updated vehicle object
     */
    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        Vehicle existingVehicle = getVehicleById(id);

        // Update only the fields that are allowed to be modified
        existingVehicle.setFuelQuota(updatedVehicle.getFuelQuota());
        existingVehicle.setFuelType(updatedVehicle.getFuelType());
        existingVehicle.setNotificationType(updatedVehicle.getNotificationType());

        return vehicleRepository.save(existingVehicle);
    }


//    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
//        Vehicle existingVehicle = vehicleRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
//
//        // Update vehicle details
//        existingVehicle.setQuota(updatedVehicle.getQuota());
//        vehicleRepository.save(existingVehicle);

////        // Log the action
////        AdminLog log = new AdminLog("Updated Vehicle Quota", LocalDateTime.now(),admin);
////        adminLogRepository.save(log);
////
////        return existingVehicle;
////    }
////
////    public List<AdminLog> getAdminLogs() {
////        return adminLogRepository.findAll();
////    }
}
