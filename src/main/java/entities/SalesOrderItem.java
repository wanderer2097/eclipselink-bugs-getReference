package entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class SalesOrderItem {

    @EmbeddedId
    private SalesOrderItemId salesOrderItemId;

    @MapsId("parentOrder")
    @ManyToOne
    private SalesOrder parentOrder;

    @Column(insertable = false, updatable = false)
    private int itemNumber;

    private String product;

    public SalesOrderItem() {
    }

    public SalesOrderItem(SalesOrder parentOrder, int itemNumber) {
        this.parentOrder = parentOrder;
        this.itemNumber = itemNumber;
        this.salesOrderItemId = new SalesOrderItemId(parentOrder.getId(), itemNumber);
    }

    public SalesOrderItemId getSalesOrderItemId() {
        return salesOrderItemId;
    }

    public SalesOrder getParentOrder() {
        return parentOrder;
    }

    public void setParentOrder(SalesOrder parentOrder) {
        this.parentOrder = parentOrder;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    
    
    
}
