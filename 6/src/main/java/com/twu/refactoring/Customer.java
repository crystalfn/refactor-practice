package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {

    private final String name;
    private final ArrayList<Rental> rentalList = new ArrayList<Rental>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentalList.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Iterator<Rental> rentals = rentalList.iterator();
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
        while (rentals.hasNext()) {
            double eachLineAmount = 0;
            Rental each = rentals.next();
            eachLineAmount = getEachLineAmount(eachLineAmount, each);

            frequentRenterPoints++;
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            result.append("\t").append(each.getMovie().getTitle())
                .append("\t").append(eachLineAmount)
                .append("\n");
            totalAmount += eachLineAmount;
        }

        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints)
            .append(" frequent renter points");
        return result.toString();
    }

    private double getEachLineAmount(double eachLineAmount, Rental each) {
        switch (each.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                eachLineAmount = getAmountCaseRegular(eachLineAmount, each);
                break;
            case Movie.NEW_RELEASE:
                eachLineAmount = getAmountCaseNewRelease(eachLineAmount, each);
                break;
            case Movie.CHILDREN:
                eachLineAmount = getAmountCaseChildren(eachLineAmount, each);
                break;
        }
        return eachLineAmount;
    }

    private double getAmountCaseRegular(double eachLineAmount, Rental each) {
        eachLineAmount += 2;
        if (each.getDaysRented() > 2) {
            eachLineAmount += (each.getDaysRented() - 2) * 1.5;
        }
        return eachLineAmount;
    }

    private double getAmountCaseNewRelease(double eachLineAmount, Rental each) {
        eachLineAmount += each.getDaysRented() * 3;
        return eachLineAmount;
    }

    private double getAmountCaseChildren(double eachLineAmount, Rental each) {
        eachLineAmount += 1.5;
        if (each.getDaysRented() > 3) {
            eachLineAmount += (each.getDaysRented() - 3) * 1.5;
        }
        return eachLineAmount;
    }
}
