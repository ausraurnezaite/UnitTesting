package com.coherensolutions.traning.automation.java.web.urnezaite.shop;

public class VirtualItem extends Item {

    private double sizeOnDisk;

    public void setSizeOnDisk(double sizeOnDisk) {
        this.sizeOnDisk = sizeOnDisk;
    }

    public double getSizeOnDisk() {
        return this.sizeOnDisk;
    }

    @Override
    public String toString() {
        return super.toString() + "Size on disk: " + sizeOnDisk;
    }
}
