package com.tbowdev.auctioncalc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tyler on 11/14/17.
 */

public class TaxCalculator {

    static final String BOISE = "Boise";
    static final String HELENA = "Helena";
    static final String PORTLAND = "Portland";
    static final String SEATTLE = "Seattle";
    static final String SPOKANE = "Spokane";

    static TaxCalculator instance = new TaxCalculator();

    private Map<String, Double> taxRates;

    private TaxCalculator() {
        taxRates = new HashMap<>();

        taxRates.put(BOISE, 0.06);
        taxRates.put(HELENA, 0.00);
        taxRates.put(PORTLAND, 0.00);
        taxRates.put(SEATTLE, 0.095);
        taxRates.put(SPOKANE, 0.087);
    }

    double getTaxRate(String location) {
        switch (location) {
            case BOISE:
                return taxRates.get(BOISE);
            case HELENA:
                return taxRates.get(HELENA);
            case PORTLAND:
                return taxRates.get(PORTLAND);
            case SEATTLE:
                return taxRates.get(SEATTLE);
            case SPOKANE:
                return taxRates.get(SPOKANE);
            default:
                return 0.07;
        }
    }
}
