package entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class SalesOrder {

    
    /***********************************************************************************************
     * fields
     **********************************************************************************************/

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "order_number_generator")
    @SequenceGenerator(
        name = "order_number_generator",
        sequenceName = "ORDER_NUMBER_SEQ",
        allocationSize = 10)
    private Long id;

    @ManyToOne
    private Customer customer;

    private String description;

    
    @OneToMany(
        cascade = CascadeType.ALL,
        mappedBy = "parentOrder",
        orphanRemoval = true)
    private List<SalesOrderItem> items;

    
    /***********************************************************************************************
     * constructors
     **********************************************************************************************/
    
    public SalesOrder() {
    }

    
    
    public SalesOrder(Long id) {
        this.id = id;
    }



    /***********************************************************************************************
     * getters & setters
     **********************************************************************************************/
    
    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public List<SalesOrderItem> getItems() {
        return items;
    }



    public void setItems(List<SalesOrderItem> items) {
        this.items = items;
    }
    
    
   
    
}