package org.example.demo;

public class Enclosures extends Product{
    private String sizeStandard;
    private String formFactor;
    private String BPLocation;
    private String imageFileName;

    public String getSizeStandard(){return sizeStandard;}
    public void setSizeStandard(String sizeStandard){this.sizeStandard = sizeStandard;}
    public String getFormFactor(){return formFactor;}
    public void setFormFactor(String formFactor){this.formFactor = formFactor;}
    public String getBPLocation(){return BPLocation;}
    public void setBPLocation(String BPLocation){this.BPLocation = BPLocation;}
    public String getImageFileName(){return imageFileName;}
    public void setImageFileName(String imageFileName){this.imageFileName = imageFileName;}
}
