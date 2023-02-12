package entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class SalesOrderItemId {

    private Long parentOrder;
    private int itemNumber;
    
    public SalesOrderItemId() {
    }

    public SalesOrderItemId(Long parentOrder, int itemNumber) {
        this.parentOrder = parentOrder;
        this.itemNumber = itemNumber;
    }
    
    public Long getParentOrder() {
        return parentOrder;
    }
    public int getItemNumber() {
        return itemNumber;
    }


}
