package com.example.codingchallenge.insuranceApplication;

import com.example.codingchallenge.regionFactor.RegionFactor;
import org.springframework.web.bind.annotation.*;

@RestController
public class InsuranceApplicationController {

    public final InsuranceApplicationInterface insuranceApplicationInterface;

    public InsuranceApplicationController(InsuranceApplicationInterface insuranceApplicationInterface){
        this.insuranceApplicationInterface = insuranceApplicationInterface;
    }

    @PostMapping("/calculate")
    public double calculateBonus(@RequestBody InsuranceApplication request) throws Exception {
        double regionFactor = RegionFactor.getFactor(request.getVehicleRegistrationArea());
        return insuranceApplicationInterface.calculateBonus(request.getEstimateYearlyKilometer(), request.getVehicleType(), regionFactor);
    }
}