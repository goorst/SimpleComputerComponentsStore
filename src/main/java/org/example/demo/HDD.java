package org.example.demo;

public class HDD extends Product{
    private String type;
    private String formFactor;
    private String capacityStorage;
    private String spindleRotationSpeed;
    private String imageFileName;

    public String getType(){return type;}
    public void setType(String type){this.type = type;}
    public String getFormFactor(){return formFactor;}
    public void setFormFactor(String formFactor){this.formFactor = formFactor;}
    public String getCapacityStorage(){return capacityStorage;}
    public void setCapacityStorage(String capacityStorage){this.capacityStorage = capacityStorage;}
    public String getSpindleRotationSpeed(){return spindleRotationSpeed;}
    public void  setSpindleRotationSpeed(String spindleRotationSpeed){this.spindleRotationSpeed = spindleRotationSpeed;}
    public String getImageFileName(){return imageFileName;}
    public void setImageFileName(String imageFileName){this.imageFileName = imageFileName;}
}
