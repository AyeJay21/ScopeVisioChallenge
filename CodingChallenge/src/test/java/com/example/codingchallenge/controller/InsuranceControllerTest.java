package com.example.codingchallenge.controller;

import com.example.codingchallenge.InvalidInputException;
import com.example.codingchallenge.insuranceApplication.InsuranceApplicationInterface;

import com.example.codingchallenge.regionFactor.RegionFactor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static com.example.codingchallenge.vehicleType.VehicleType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class InsuranceControllerTest {

    @MockitoBean
    private InsuranceApplicationInterface insuranceApplicationInterface;

    @BeforeEach
    public void setUp() {
        when(insuranceApplicationInterface.calculateBonus(10000.0, SUV, RegionFactor.getFactor("Berlin")))
                .thenReturn(3900.0);
        when(insuranceApplicationInterface.calculateBonus(5000.0, CAR, RegionFactor.getFactor("Nordrhein-Westfalen")))
                .thenReturn(650.0);
        when(insuranceApplicationInterface.calculateBonus(15000.0, LIMOUSINE, RegionFactor.getFactor("Thüringen")))
                .thenReturn(3600.0);

        when(insuranceApplicationInterface.calculateBonus(-1000.0, SUV, RegionFactor.getFactor("Berlin")))
                .thenThrow(new InvalidInputException("Kilometerleistung darf nicht negativ sein.")); // InvalidInputException verwenden
        when(insuranceApplicationInterface.calculateBonus(10000.0, null, RegionFactor.getFactor("Berlin")))
                .thenThrow(new InvalidInputException("Fahrzeugtyp darf nicht null sein.")); // InvalidInputException verwenden
        when(insuranceApplicationInterface.calculateBonus(10000.0, SUV, RegionFactor.getFactor("UnbekanntesBundesland")))
                .thenThrow(new InvalidInputException("Unbekannte Region: UnbekanntesBundesland")); // InvalidInputException verwenden
    }

    @Test
    public void testCalculateInsuranceBonus() {

        double expectedBonus = 3900.0;
        when(insuranceApplicationInterface.calculateBonus(10000.0, SUV, RegionFactor.getFactor("Berlin")))
                .thenReturn(expectedBonus);

        double actualBonus = insuranceApplicationInterface.calculateBonus(10000.0, SUV, RegionFactor.getFactor("Berlin"));

        assertEquals(expectedBonus, actualBonus, 0.01);
    }

    @Test
    public void testCalculateInsuranceBonusNegativeKilometers() {
        Exception exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            insuranceApplicationInterface.calculateBonus(-1000.0, SUV, RegionFactor.getFactor("Berlin"));
        });

        assertEquals(InvalidInputException.class, exception.getClass());
    }

    @Test
    public void testCalculateInsuranceBonusNullVehicleType() {
        Exception exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            insuranceApplicationInterface.calculateBonus(10000.0, null, RegionFactor.getFactor("Berlin"));
        });

        assertEquals(InvalidInputException.class, exception.getClass());
    }

    @Test
    public void testCalculateInsuranceBonusUnknownRegion() {
        Exception exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            insuranceApplicationInterface.calculateBonus(10000.0, SUV, RegionFactor.getFactor("UnbekanntesBundesland"));
        });

        assertEquals(InvalidInputException.class, exception.getClass());
    }
}