package entities;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(parentOrder, itemNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof SalesOrderItemId))
            return false;
        SalesOrderItemId other = (SalesOrderItemId) obj;
        return Objects.equals(parentOrder, other.parentOrder) && itemNumber == other.itemNumber;
    }

    


}
