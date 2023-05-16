package com.gl.parcauto.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "fuel_consumption_record")
public class FuelConsumptionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;
    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;
    @Column(name = "kilometers_traveled", nullable = false)
    private Double kilometersTraveled;
    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false)
    private FuelType fuelType;
    @Column(name = "fuel_consumed", nullable = false)
    private Double fuelConsumed;
    @Column(name = "fuel_cost", nullable = false)
    private Double fuelCost;
    @Column(name = "refueling_location", nullable = false)
    private String refuelingLocation;
}
