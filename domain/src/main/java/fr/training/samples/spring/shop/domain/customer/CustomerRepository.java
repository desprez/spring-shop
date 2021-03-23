package fr.training.samples.spring.shop.domain.customer;

public interface CustomerRepository {

	public Customer findById(Long id);

	public void save(Customer customer);

	public Customer findByCustomerName(String name);

}
