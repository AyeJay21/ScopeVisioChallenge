package com.example.codingchallenge.insuranceApplication;

import com.example.codingchallenge.vehicleType.VehicleType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class InsuranceApplication {

    @Id
    public UUID id;
    public Double estimateYearlyKilometer;
    @Enumerated(EnumType.STRING)
    public VehicleType vehicleType;
    public String vehicleRegistrationArea;
    public Double bonus;
}
