package com.se.Fuel_Quota_Management_System.controller;

import com.se.Fuel_Quota_Management_System.DTO.FuelStationOwnerLogDTO;
import com.se.Fuel_Quota_Management_System.model.FuelStation;
import com.se.Fuel_Quota_Management_System.model.FuelStationOwner;
import com.se.Fuel_Quota_Management_System.service.FuelStationOwnerService;
import com.se.Fuel_Quota_Management_System.service.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/owner")
public class FuelStationOwnerController {
    @Autowired
    private FuelStationOwnerService fuelStationOwnerService;

    @Autowired
    private FuelStationService fuelStationService;


    // for register StationOwner
    @PostMapping("/register")
    public ResponseEntity<?> registerOwner(@Validated @RequestBody FuelStationOwnerLogDTO request) {
        try {
            ResponseEntity<?> registeredowner = fuelStationOwnerService.registerOwner(request);
            return ResponseEntity.ok(registeredowner.getBody());
        } catch (Exception e) {
            // Log the error (use a logger in production)
            System.err.println("Error during registration: " + e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }



    // find owner name by Id
    @PreAuthorize("hasAuthority('stationowner')")
    @GetMapping("/findname/{id}")
    public ResponseEntity<?> getOwnerById(@PathVariable Long id) {
        try {
            FuelStationOwner owner = fuelStationOwnerService.findFuelStationOwnerById(id);
            return ResponseEntity.ok(owner.getName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    // find owner details by Id
    @PreAuthorize("hasAuthority('stationowner')")
    @GetMapping("finddetail/{id}")
    public ResponseEntity<?> getDetailsbyId(@PathVariable Long id){
        try {
            FuelStationOwner owner = fuelStationOwnerService.findFuelStationOwnerById(id);
            return ResponseEntity.ok(owner);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    // find fuelstations owned by owner through owner id
    @PreAuthorize("hasAuthority('stationowner')")
    @GetMapping("findstations/{id}")
    public ResponseEntity<?> getStationsById(@PathVariable Long id){
        try {
            List<FuelStation> fuelStation = fuelStationService.getByOwnerId(id);
            return ResponseEntity.ok(fuelStation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    // find owner by login Id
    @PreAuthorize("hasAuthority('stationowner')")
    @GetMapping("/findbyloginid/{id}")
    public ResponseEntity<?> getidbyloginid(@PathVariable("id") Long loginid){
        try {
            FuelStationOwner owner = fuelStationOwnerService.findFuelStationOwnerByOwnerLog(loginid);
            return ResponseEntity.ok(owner.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }


//
//    @GetMapping("search")
//    public FuelStationOwner findOwnerByNicOrEmail(@RequestParam(value="nicNo",required = false) String nicNo,
//                                                             @RequestParam(value= "email",required = false) String email){
//        return fuelStationOwnerService.findAllByNicOrEmail(nicNo,email);
//
//    }
}
