package org.example.demo;

public class VideoCards extends Product{
    private String videoChipset;
    private String gpf;
    private String maxResolution;
    private String videoMemory;
    private String imageFileName;

    public String getVideoChipset(){return videoChipset;}
    public void setVideoChipset(String videoChipset){this.videoChipset = videoChipset;}
    public String getGpf(){return gpf;}
    public void setGpf(String gpf){this.gpf = gpf;}
    public String getMaxResolution(){return maxResolution;}
    public void setMaxResolution(String maxResolution){this.maxResolution = maxResolution;}
    public String getVideoMemory(){return videoMemory;}
    public void setVideoMemory(String videoMemory){this.videoMemory = videoMemory;}
    public String getImageFileName(){return imageFileName;}
    public void setImageFileName(String imageFileName){this.imageFileName = imageFileName;}
}
