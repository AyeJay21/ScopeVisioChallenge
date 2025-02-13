package com.example.codingchallenge.insuranceApplication;

import com.example.codingchallenge.InvalidInputException;
import com.example.codingchallenge.vehicleType.VehicleType;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class InsuranceApplicationService implements InsuranceApplicationInterface  {

    public final InsuranceApplicationRepository insuranceApplicationRepository;

    public InsuranceApplicationService(InsuranceApplicationRepository insuranceApplicationRepository){
        this.insuranceApplicationRepository = insuranceApplicationRepository;
    }

    /**
     * Überprüft die Kilometergrenzen
     * @param yearlyKilometers
     * @return
     */
    private Double getKilometerFactor(Double yearlyKilometers) {
        if (yearlyKilometers <= 5000) {
            return 0.5;
        } else if (yearlyKilometers <= 10000) {
            return 1.0;
        } else if (yearlyKilometers <= 20000) {
            return 1.5;
        } else {
            return 2.0;
        }
    }

    /**
     * Kilometerleistung-Faktor * Fahrzeugtyp-Faktor * Region-Faktor = Versicherungsprämie
     * regionFactor ist eine Map
     * vehicleType ist ein Enum
     * @param estimateYearlyKilometer
     * @param vehicleType
     * @param regionFactor
     * @return Double
     * @throws InvalidInputException if...
     * estimateYearlyKilometer, vehicleType oder regionFactor null ist
     * estimateYearlyKilometer < 0 ist
     */
    @Override
    public Double calculateBonus(Double estimateYearlyKilometer, VehicleType vehicleType, Double regionFactor) throws InvalidInputException {
        if(regionFactor == null){
            throw new IllegalArgumentException("Eines der folgenden Attribute ist fehlt: RegionFactor");
        }
        if(estimateYearlyKilometer == null){
            throw new IllegalArgumentException("Eines der folgenden Attribute ist fehlt: Kilometer");
        }
        if(vehicleType == null){
            throw new IllegalArgumentException("Eines der folgenden Attribute ist fehlt: VehicleType");
        }

        if (estimateYearlyKilometer < 0) {
            throw new IllegalArgumentException("Kilometerleistung darf nicht negativ sein.");
        }

        Double kilometerFactor = getKilometerFactor(estimateYearlyKilometer);
        Double bonus = kilometerFactor * vehicleType.getFactor() * regionFactor;
        InsuranceApplication insuranceApplication = new InsuranceApplication();
        insuranceApplication.setId(UUID.randomUUID());
        insuranceApplication.setVehicleType(vehicleType);
        insuranceApplication.setEstimateYearlyKilometer(estimateYearlyKilometer);
        insuranceApplication.setVehicleRegistrationArea(regionFactor.toString());
        insuranceApplication.setBonus(bonus);
        insuranceApplicationRepository.save(insuranceApplication);
        return bonus;
    }
}
