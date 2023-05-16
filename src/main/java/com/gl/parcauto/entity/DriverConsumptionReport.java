package com.gl.parcauto.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "driver_consumption_report")
public class DriverConsumptionReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Driver driver;
    @Column(name = "fuel_consumend", nullable = false)
    private double totalFuelConsumed;
    @Column(name = "distance_travelled", nullable = false)
    private double totalDistanceTravelled;
    @Column(name = "fuel_efficiency", nullable = false)
    private double fuelEfficiency;
}
