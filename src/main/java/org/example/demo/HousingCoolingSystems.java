package org.example.demo;

public class HousingCoolingSystems extends Product{
    private String coolingType;
    private int fansNumber;
    private String fanSize;
    private String fanRotationSpeed;
    private String fanLife;
    private String imageFileName;

    public String getCoolingType(){return coolingType;}
    public void setCoolingType(String coolingType) {this.coolingType = coolingType;}
    public int getFansNumber() {return fansNumber;}
    public void setFansNumber(int fansNumber) {this.fansNumber = fansNumber;}
    public String getFanSize() {return fanSize;}
    public void setFanSize(String fanSize) {this.fanSize = fanSize;}
    public String getFanRotationSpeed() {return fanRotationSpeed;}
    public void setFanRotationSpeed(String fanRotationSpeed) {this.fanRotationSpeed = fanRotationSpeed;}
    public String getFanLife() {return fanLife;}
    public void setFanLife(String fanLife) {this.fanLife = fanLife;}
    public String getImageFileName(){return imageFileName;}
    public void setImageFileName(String imageFileName){this.imageFileName = imageFileName;}
}
