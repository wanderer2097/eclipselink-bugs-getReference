package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {
    
    
    /***********************************************************************************************
     * fields
     **********************************************************************************************/

    @Id
    private String id;
    
    private String name;


    
    /***********************************************************************************************
     * constructors
     **********************************************************************************************/
    
    public Customer() {
    }

    public Customer(String id) {
        this.id = id;
    }

    
    
    /***********************************************************************************************
     * getters & setters
     **********************************************************************************************/
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
    
}