package com.example.codingchallenge.regionFactor;

import java.util.HashMap;
import java.util.Map;

public class RegionFactor {

    private static final Map<String, Double> regionFactors = new HashMap<>();

    static{
        regionFactors.put("Baden-Württemberg", 1.1);
        regionFactors.put("Bayern", 1.2);
        regionFactors.put("Berlin", 1.3);
        regionFactors.put("Brandenburg", 0.9);
        regionFactors.put("Bremen", 1.0);
        regionFactors.put("Hamburg", 1.1);
        regionFactors.put("Hessen", 1.2);
        regionFactors.put("Mecklenburg-Vorpommern", 0.8);
        regionFactors.put("Niedersachsen", 1.0);
        regionFactors.put("Nordrhein-Westfalen", 1.3);
        regionFactors.put("Rheinland-Pfalz", 1.1);
        regionFactors.put("Saarland", 1.0);
        regionFactors.put("Sachsen", 0.9);
        regionFactors.put("Sachsen-Anhalt", 0.8);
        regionFactors.put("Schleswig-Holstein", 0.9);
        regionFactors.put("Thüringen", 0.8);
    }

    public static double getFactor(String bundesland){
        return regionFactors.getOrDefault(bundesland, 1.0);
    }
}
