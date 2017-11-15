package com.tbowdev.auctioncalc;

/**
 * Created by tyler on 11/14/17.
 */

public class VehicleCost {

    private String name;
    private double bidPrice;
    private double salePriceFee;
    private double internetFee;
    private double loadFee;
    private double taxRate;
    private double taxVal;
    private double totalCost;

    public VehicleCost(String name, double bidPrice, String location) {
        this.name = name;
        this.bidPrice = bidPrice;
        this.salePriceFee = setSalePriceFee(bidPrice);
        this.internetFee = setInternetFee(bidPrice);
        this.loadFee = setLoadFee(bidPrice);
        this.taxRate = setTaxRate(location);
        this.taxVal = calculateTax();
        this.totalCost = calculateTotal();
    }

    private double calculateTax() {
        return (bidPrice + salePriceFee + internetFee + loadFee) * taxRate;
    }

    private double calculateTotal() {
        return bidPrice + salePriceFee + internetFee + loadFee + taxVal;
    }

    private static double setSalePriceFee(double bidPrice) {

        if(bidPrice <= 49.99) return 25;
        else if(bidPrice <= 99.99) return 45;
        else if(bidPrice <= 199.99) return 80;
        else if(bidPrice <= 399.99) return 120;
        else if(bidPrice <= 499.99) return 160;
        else if(bidPrice <= 599.99) return 205;
        else if(bidPrice <= 699.99) return 230;
        else if(bidPrice <= 799.99) return 250;
        else if(bidPrice <= 899.99) return 270;
        else if(bidPrice <= 999.99) return 295;
        else if(bidPrice <= 1099.99) return 320;
        else if(bidPrice <= 1199.99) return 345;
        else if(bidPrice <= 1399.99) return 370;
        else if(bidPrice <= 1599.99) return 400;
        else if(bidPrice <= 1799.99) return 430;
        else if(bidPrice <= 1999.99) return 460;
        else if(bidPrice <= 2499.99) return 490;
        else if(bidPrice <= 2999.99) return 520;
        else if(bidPrice <= 4999.99) return 620;
        else return 210;
    }

    private static double setInternetFee(double bidPrice) {
        if(bidPrice <= 99.99) return 0;
        else if(bidPrice <= 499.99) return 29;
        else if(bidPrice <= 999.99) return 39;
        else if(bidPrice <= 1499.99) return 49;
        else if(bidPrice <= 1999.99) return 59;
        else if(bidPrice <= 2999.99) return 69;
        else if(bidPrice <= 3999.99) return 79;
        else return 89;
    }

    private static double setLoadFee(double bidPrice) {
        return 59;
    }

    private static double setTaxRate(String location) {
        return TaxCalculator.instance.getTaxRate(location);
    }

    public String getName() {
        return name;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public double getSalePriceFee() {
        return salePriceFee;
    }

    public double getInternetFee() {
        return internetFee;
    }

    public double getLoadFee() {
        return loadFee;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getTaxVal() {
        return taxVal;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
