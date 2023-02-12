package eclipselink.bugs.scenarios;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.persistence.internal.weaving.PersistenceWeaved;
import org.junit.jupiter.api.Test;

import entities.Customer;
import entities.SalesOrder;
import entities.SalesOrderItem;
import entities.SalesOrderItemId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class GetReferenceOnEmbeddedIdTestCase {

	private static final Class<PersistenceWeaved> MARKER_INTERFACE = PersistenceWeaved.class;

	@Test
	public void testGetReferenceOnEmbeddedId() {

		// given
		String customerId = "ID01";
		String customerName = "Best customer";
		String product = "Some product";


		// when
		Map<String, String> properties = new HashMap<>();

		// refresh the schema only on the first pass and pre-populate the data
		properties.put("jakarta.persistence.schema-generation.database.action", "drop-and-create");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu", properties);

		for (var mt : emf.getMetamodel().getManagedTypes()) {
            var entityClass = mt.getJavaType();
            System.out.println("Checking: " + entityClass);
            assertTrue(Arrays.asList(entityClass.getInterfaces()).contains(MARKER_INTERFACE),
                entityClass.getName() + " is not weaved!" );            
        }


		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// Step 1: Create a customer
		Customer customer = new Customer(customerId);
		customer.setName(customerName);
		em.persist(customer);
			
		// Step 2: Create an order
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setCustomer(customer);
		int itemNumber = 1;
		salesOrder.setItems(List.of(
			new SalesOrderItem(salesOrder, itemNumber)
		));

		salesOrder.getItems().get(0).setProduct(product);

		em.persist(salesOrder);

		Long salesOrderId = salesOrder.getId();

		tx.commit();
		em.close();

		// close the persistence unit
		emf.close();

		
		// new session & new persistence context
		emf = Persistence.createEntityManagerFactory("pu");
		em = emf.createEntityManager();

		SalesOrderItemId salesOrderItemId = new SalesOrderItemId(salesOrderId, itemNumber);

		em.getReference(SalesOrderItem.class, salesOrderItemId);

		em.close();
		emf.close();

	}

}
