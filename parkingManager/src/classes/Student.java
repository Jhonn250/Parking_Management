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
public class Student extends Person{
   
    private String career = "";
    private String semester = "";
    private String status = "";
    
    public void set(Student p){
        
        this.setCareer(p.getCareer());
        this.setSemester(p.getSemester());
        this.setStatus(p.getStatus());
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
