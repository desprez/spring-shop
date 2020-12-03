package fr.training.samples.spring.shop.application.customer;

import fr.training.samples.spring.shop.domain.customer.Customer;

public interface CustomerService {

	/**
	 * @param customer a Customer
	 * @return a Customer
	 */
	public Customer create(Customer customer);

	/**
	 * @param customerID a customer identifier
	 * @return a Customer
	 */
	public Customer findOne(String customerId);

	/**
	 * @param customerEntity a Customer
	 * @return a Customer
	 */
	public void update(Customer customer);

}
