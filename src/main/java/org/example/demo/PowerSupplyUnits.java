package org.example.demo;

public class PowerSupplyUnits extends Product{
    private String formFactor;
    private String power;
    private String minInpVolt;
    private String maxInpVolt;
    private String imageFileName;

    public String getFormFactor(){return formFactor;}
    public void setFormFactor(String formFactor){this.formFactor = formFactor;}
    public String getPower(){return power;}
    public void setPower(String power){this.power = power;}
    public String getMinInpVolt(){return minInpVolt;}
    public void setMinInpVolt(String minInpVolt){this.minInpVolt = minInpVolt;}
    public String getMaxInpVolt(){return maxInpVolt;}
    public void setMaxInpVolt(String maxInpVolt){this.maxInpVolt = maxInpVolt;}
    public String getImageFileName(){return imageFileName;}
    public void setImageFileName(String imageFileName){this.imageFileName = imageFileName;}

}
