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
public class Person {
    
    private String id = "";
    private String name = "";
    private String lastName = "";
    private String address = "";
    private String email = "";
    private String phone = "";
    private String typePerson = ""; //estudiante, maestro, administrativo
    
    public void set(Person p){
        this.setId(p.getId());
        this.setName(p.getName());
        this.setName(p.getLastName());
        this.setAddress(p.getAddress());
        this.setPhone(p.getPhone());
        this.setEmail(p.getEmail());
        
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(String typePerson) {
        this.typePerson = typePerson;
    }
    
    
    
    
}
