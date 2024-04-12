package org.example.demo;

public class CPs extends Product {

    private String coreName;
    private int coreNumber;
    private String socket;
    private String frequency;
    private int threadsNumber;
    private String imageFileName;

    public int getCoreNumber() {return coreNumber;}
    public void setCoreNumber(int coreNumber) {this.coreNumber = coreNumber;}
    public String getSocket() {return socket;}
    public void setSocket(String socket) {this.socket = socket;}
    public String getFrequency() {return frequency;}
    public void setFrequency(String frequency) {this.frequency = frequency;}
    public int getThreadsNumber() {return threadsNumber;}
    public void setThreadsNumber(int threadsNumber) {this.threadsNumber = threadsNumber;}
    public String getCoreName(){return coreName;}
    public void setCoreName(String coreName){this.coreName = coreName;}

    public String getImageFileName(){return imageFileName;}
    public void setImageFileName(String imageFileName){this.imageFileName = imageFileName;}
}
