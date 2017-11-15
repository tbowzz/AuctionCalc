package com.tbowdev.auctioncalc

import com.tbowdev.auctioncalc.TaxCalculator.BOISE;
import com.tbowdev.auctioncalc.TaxCalculator.HELENA
import com.tbowdev.auctioncalc.TaxCalculator.PORTLAND
import com.tbowdev.auctioncalc.TaxCalculator.SEATTLE
import com.tbowdev.auctioncalc.TaxCalculator.SPOKANE
import java.util.*

/**
 * Created by tyler on 11/15/17.
 */

object Model {
    var locations: ArrayList<String> = ArrayList()
//    private val vehicles: List<VehicleCost>

    init {
        locations.add(BOISE)
        locations.add(HELENA)
        locations.add(PORTLAND)
        locations.add(SEATTLE)
        locations.add(SPOKANE)
    }

    fun calculateCost(name: String, bidPrice: Double, location: String): VehicleCost {
        return VehicleCost(name, bidPrice, location)
    }

    fun getLocations(): List<String> {
        return locations
    }
}