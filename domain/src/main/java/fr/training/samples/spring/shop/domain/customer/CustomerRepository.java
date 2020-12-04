package fr.training.samples.spring.shop.domain.customer;

/**
 * Repository for customer entity
 */
public interface CustomerRepository {

	public Customer findById(String id);

	public void save(Customer customer);

	public Customer findByCustomerName(String name);

}
