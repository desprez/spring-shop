package fr.training.samples.spring.shop.domain.customer;

public interface CustomerRepository {

	public Customer findById(String id);

	public void save(Customer customer);

}
