package com.tbowdev.auctioncalc

/**
 * Created by tyler on 11/15/17.
 */

object TaxCalculator {
    val BOISE = "Boise"
    val HELENA = "Helena"
    val PORTLAND = "Portland"
    val SEATTLE = "Seattle"
    val SPOKANE = "Spokane"

    private val taxRates: HashMap<String, Double> = HashMap()

    init {
        taxRates.put(BOISE, 0.06)
        taxRates.put(HELENA, 0.00)
        taxRates.put(PORTLAND, 0.00)
        taxRates.put(SEATTLE, 0.095)
        taxRates.put(SPOKANE, 0.087)
    }

    fun getTaxRate(location: String): Double? {
        when (location) {
            BOISE -> return taxRates[BOISE]
            HELENA -> return taxRates[HELENA]
            PORTLAND -> return taxRates[PORTLAND]
            SEATTLE -> return taxRates[SEATTLE]
            SPOKANE -> return taxRates[SPOKANE]
            else -> return 0.07
        }
    }
}