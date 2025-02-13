package com.example.codingchallenge.insuranceApplication;

import com.example.codingchallenge.InvalidInputException;
import com.example.codingchallenge.vehicleType.VehicleType;

public interface InsuranceApplicationInterface {
    Double calculateBonus(Double estimateYearlyKilometer, VehicleType vehicleType, Double regionFactor) throws InvalidInputException;
}
