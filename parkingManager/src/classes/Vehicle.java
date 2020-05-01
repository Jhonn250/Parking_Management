/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author atlas
 */
public class Vehicle {
    
    
    private String model = "";
    private String brand = ""; //marca
    private String type = ""; //auto-motocicleta
    private String plates = "";
    private String color = "";
    
    public void set(Vehicle v){
        
        this.setModel(v.getModel());
        this.setBrand(v.getBrand());
        this.setType(v.getType());
        this.setColor(v.getColor());
        this.setPlates(v.getPlates());
    }
    

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }
    
    
    
    
    
    
    
}
