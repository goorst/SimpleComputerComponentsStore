package org.example.demo;

public class SSD extends Product{
    private String type;
    private String capacityStorage;
    private String formFactor;
    private String maxReadingSpeed;
    private String maxRecordingSpeed;
    private String imageFileName;


    public String getType(){return type;}
    public void setType(String type){this.type = type;}
    public String getCapacityStorage(){return capacityStorage;}
    public void setCapacityStorage(String capacityStorage){this.capacityStorage = capacityStorage;}
    public String getFormFactor(){return formFactor;}
    public void setFormFactor(String formFactor){this.formFactor = formFactor;}
    public String getMaxReadingSpeed(){return maxReadingSpeed;}
    public void setMaxReadingSpeed(String maxReadingSpeed){this.maxReadingSpeed = maxReadingSpeed;}
    public String getMaxRecordingSpeed(){return maxRecordingSpeed;}
    public void setMaxRecordingSpeed(String maxRecordingSpeed){this.maxRecordingSpeed= maxRecordingSpeed;}
    public String getImageFileName(){return imageFileName;}
    public void setImageFileName(String imageFileName){this.imageFileName = imageFileName;}
}
