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
public class Administrative extends Person{
    private String area = "";
    
    
    public void set(Administrative a){
        this.setArea(a.getArea());
        
        
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }    
    
 
    
    
}
