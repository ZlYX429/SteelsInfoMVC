package model;

import java.util.Date;

public class SteelInfo {
    private int id;
    private String steelType;
    private String steelOrigin;
    private Date productionDate;
    private String manufacturer;
    private double price;
    private double volume;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSteelType() {
        return steelType;
    }

    public void setSteelType(String steelType) {
        this.steelType = steelType;
    }

    public String getSteelOrigin() {
        return steelOrigin;
    }

    public void setSteelOrigin(String steelOrigin) {
        this.steelOrigin = steelOrigin;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
} 