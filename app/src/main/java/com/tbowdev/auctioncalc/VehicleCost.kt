package com.tbowdev.auctioncalc

/**
 * Created by tyler on 11/15/17.
 */
class VehicleCost(val name: String, val bidPrice: Double, location: String) {
    val salePriceFee: Double
    val internetFee: Double
    val loadFee: Double
    val taxRate: Double
    val taxVal: Double
    val totalCost: Double

    init {
        this.salePriceFee = setSalePriceFee(bidPrice)
        this.internetFee = setInternetFee(bidPrice)
        this.loadFee = setLoadFee(bidPrice)
        this.taxRate = setTaxRate(location)
        this.taxVal = calculateTax()
        this.totalCost = calculateTotal()
    }

    private fun calculateTax(): Double {
        return (bidPrice + salePriceFee + internetFee + loadFee) * taxRate
    }

    private fun calculateTotal(): Double {
        return bidPrice + salePriceFee + internetFee + loadFee + taxVal
    }

    private fun setSalePriceFee(bidPrice: Double): Double {

        return if (bidPrice <= 49.99)
            25.0
        else if (bidPrice <= 99.99)
            45.0
        else if (bidPrice <= 199.99)
            80.0
        else if (bidPrice <= 399.99)
            120.0
        else if (bidPrice <= 499.99)
            160.0
        else if (bidPrice <= 599.99)
            205.0
        else if (bidPrice <= 699.99)
            230.0
        else if (bidPrice <= 799.99)
            250.0
        else if (bidPrice <= 899.99)
            270.0
        else if (bidPrice <= 999.99)
            295.0
        else if (bidPrice <= 1099.99)
            320.0
        else if (bidPrice <= 1199.99)
            345.0
        else if (bidPrice <= 1399.99)
            370.0
        else if (bidPrice <= 1599.99)
            400.0
        else if (bidPrice <= 1799.99)
            430.0
        else if (bidPrice <= 1999.99)
            460.0
        else if (bidPrice <= 2499.99)
            490.0
        else if (bidPrice <= 2999.99)
            520.0
        else if (bidPrice <= 4999.99)
            620.0
        else
            210.0
    }

    private fun setInternetFee(bidPrice: Double): Double {
        return if (bidPrice <= 99.99)
            0.0
        else if (bidPrice <= 499.99)
            29.0
        else if (bidPrice <= 999.99)
            39.0
        else if (bidPrice <= 1499.99)
            49.0
        else if (bidPrice <= 1999.99)
            59.0
        else if (bidPrice <= 2999.99)
            69.0
        else if (bidPrice <= 3999.99)
            79.0
        else
            89.0
    }

    private fun setLoadFee(bidPrice: Double): Double {
        return 59.0
    }

    private fun setTaxRate(location: String): Double {
        return TaxCalculator.getTaxRate(location)!!
    }
}