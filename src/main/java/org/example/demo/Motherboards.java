package org.example.demo;

public class Motherboards extends Product{
    private String formFactor;
    private String socket;
    private String chipset;
    private String memoryType;
    private String imageFileName;

    public String getFormFactor(){return formFactor;}
    public void setFormFactor(String formFactor){this.formFactor = formFactor;}
    public String getSocket(){return socket;}
    public void setSocket(String socket){this.socket = socket;}
    public String getChipset(){return chipset;}
    public void setChipset(String chipset){this.chipset = chipset;}
    public String getMemoryType(){return memoryType;}
    public void setMemoryType(String memoryType){this.memoryType = memoryType;}
    public String getImageFileName(){return imageFileName;}
    public void setImageFileName(String imageFileName){this.imageFileName = imageFileName;}
}
