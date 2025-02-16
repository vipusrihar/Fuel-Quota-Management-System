package com.se.Fuel_Quota_Management_System.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;


import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FuelStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Long id;

    private String stationName;

    private String registrationNumber;

    private String location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loginid", nullable = false)
    @JsonIgnore
    private UserLog stationLog;

    // fuel station have only one owner
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private FuelStationOwner owner;

    // Repeat for other fields
    @ElementCollection
    @CollectionTable(name = "fuel_station_inventory", joinColumns = @JoinColumn(name = "fuel_station_id"))
    @MapKeyColumn(name = "fuel_type")
    @Column(name = "available_fuel")
    private Map<String, Double> fuelInventory; // Key: Fuel type (e.g., Petrol, Diesel), Value: Quantity available

    @OneToMany(mappedBy = "station", cascade = CascadeType.PERSIST, orphanRemoval = false)
    @JsonIgnore
    private List<FuelTransaction> transactions;

    //to track station status
    @Column(nullable = false)
    private boolean isActive = true; // Default to active

}
