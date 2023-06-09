package com.claimManagement.insuranceCompany.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feeId;

    private int estimatedStartLimit;

    private int getEstimatedEndLimit;

    private int fees;

    public Fees() {

    }

    public int getEstimatedStartLimit() {
        return estimatedStartLimit;
    }

    public void setEstimatedStartLimit(int estimatedStartLimit) {
        this.estimatedStartLimit = estimatedStartLimit;
    }

    public int getGetEstimatedEndLimit() {
        return getEstimatedEndLimit;
    }

    public void setGetEstimatedEndLimit(int getEstimatedEndLimit) {
        this.getEstimatedEndLimit = getEstimatedEndLimit;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "Fees{" +
                "feeId=" + feeId +
                ", estimatedStartLimit=" + estimatedStartLimit +
                ", getEstimatedEndLimit=" + getEstimatedEndLimit +
                ", fees=" + fees +
                '}';
    }

    public Fees(int estimatedStartLimit, int getEstimatedEndLimit, int fees) {
        this.estimatedStartLimit = estimatedStartLimit;
        this.getEstimatedEndLimit = getEstimatedEndLimit;
        this.fees = fees;
    }
}
