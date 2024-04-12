package org.example.demo;

public class RAM extends Product{
    private String formFactor;
    private String memoryType;
    private String volume;
    private String clockFrequency;
    private String throughputCapacity;
    private String imageFileName;

    public String getFormFactor() {return formFactor;}
    public void setFormFactor(String formFactor) {this.formFactor = formFactor;}
    public String getMemoryType() {return memoryType;}
    public void setMemoryType(String memoryType) {this.memoryType = memoryType;}
    public String getVolume() {return volume;}
    public void setVolume(String volume) {this.volume = volume;}
    public String getClockFrequency() {return clockFrequency;}
    public void setClockFrequency(String clockFrequency) {this.clockFrequency = clockFrequency;}
    public String getThroughputCapacity() {return throughputCapacity;}
    public void setThroughputCapacity(String throughputCapacity) {this.throughputCapacity = throughputCapacity;}
    public String getImageFileName(){return imageFileName;}
    public void setImageFileName(String imageFileName){this.imageFileName = imageFileName;}
}
