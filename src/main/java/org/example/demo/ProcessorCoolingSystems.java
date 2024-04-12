package org.example.demo;

public class ProcessorCoolingSystems extends Product{
    private String coolingType;
    private int fansNumber;
    private String fanSize;
    private String fanRotationSpeed;
    private String maxHeatDissipationProcessor;
    private String imageFileName;

    public String getCoolingType(){return coolingType;}
    public void setCoolingType(String coolingType) {this.coolingType = coolingType;}
    public int getFansNumber() {return fansNumber;}
    public void setFansNumber(int fansNumber) {this.fansNumber = fansNumber;}
    public String getFanSize() {return fanSize;}
    public void setFanSize(String fanSize) {this.fanSize = fanSize;}
    public String getFanRotationSpeed() {return fanRotationSpeed;}
    public void setFanRotationSpeed(String fanRotationSpeed) {this.fanRotationSpeed = fanRotationSpeed;}
    public String getMaxHeatDissipationProcessor() {return maxHeatDissipationProcessor;}
    public void setMaxHeatDissipationProcessor(String maxHeatDissipationProcessor) {this.maxHeatDissipationProcessor = maxHeatDissipationProcessor;}
    public String getImageFileName(){return imageFileName;}
    public void setImageFileName(String imageFileName){this.imageFileName = imageFileName;}
}
