package com.tbowdev.auctioncalc;

import java.util.ArrayList;
import java.util.List;

import static com.tbowdev.auctioncalc.TaxCalculator.BOISE;
import static com.tbowdev.auctioncalc.TaxCalculator.HELENA;
import static com.tbowdev.auctioncalc.TaxCalculator.PORTLAND;
import static com.tbowdev.auctioncalc.TaxCalculator.SEATTLE;
import static com.tbowdev.auctioncalc.TaxCalculator.SPOKANE;

/**
 * Created by tyler on 11/14/17.
 */

public class Model {

    private List<VehicleCost> vehicles;
    private List<String> locations;

    public static Model instance = new Model();

    private Model() {
        vehicles = new ArrayList<>();
        locations = new ArrayList<>();
        locations.add(BOISE);
        locations.add(HELENA);
        locations.add(PORTLAND);
        locations.add(SEATTLE);
        locations.add(SPOKANE);
    }

    public VehicleCost calculateCost(String name, double bidPrice, String location) {
        return new VehicleCost(name, bidPrice, location);
    }

    public List<String> getLocations() {
        return locations;
    }
}
