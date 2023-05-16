package com.gl.parcauto.entity;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "vehilce_consumption_report")
public class VehicleConsumptionReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;
    @Column(name = "fuel_consumed", nullable = false)
    private double totalFuelConsumed;
    @Column(name = "distance_travelled", nullable = false)
    private double totalDistanceTravelled;
    @Column(name = "fuel_efficiency", nullable = false)
    private double fuelEfficiency;
}
