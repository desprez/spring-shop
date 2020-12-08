package fr.training.samples.spring.shop.application.customer;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.training.samples.spring.shop.domain.common.exception.AlreadyExistingException;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.customer.RoleTypeEnum;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	private final PasswordEncoder passwordEncoder;

	/**
	 * Constructor for Bean injection
	 */
	public CustomerServiceImpl(final CustomerRepository customerRepository, final PasswordEncoder passwordEncoder) {
		this.customerRepository = customerRepository;
		this.passwordEncoder = passwordEncoder;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.application.customer.CustomerService#create(
	 * fr.training.samples.spring.shop.domain.customer.Customer)
	 */
	@Transactional
	@Override
	public Customer create(final Customer customer) {

		final Customer existingCustomer = customerRepository.findByCustomerName(customer.getName());
		if (existingCustomer != null) {
			throw new AlreadyExistingException("A customer with this name already exist");
		}
		// Encode given password
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		// New customer has user role by default
		customer.addRole(RoleTypeEnum.ROLE_USER);

		customerRepository.save(customer);

		return customer;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.application.customer.CustomerService#findOne(
	 * java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public Customer findOne(final String customerId) {
		return customerRepository.findById(customerId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.application.customer.CustomerService#update(
	 * fr.training.samples.spring.shop.domain.customer.Customer)
	 */
	@Secured("ROLE_USER")
	@Transactional
	@Override
	public void update(final String customerId, final Customer customer) {
		final Customer customerToUpdate = customerRepository.findById(customerId);
		customerToUpdate.setName(customer.getName());
		customerToUpdate.setPassword(passwordEncoder.encode(customer.getPassword()));
		customerRepository.save(customer);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.training.samples.spring.shop.application.customer.CustomerService#
	 * findByName(java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public Customer findByName(final String name) {
		return customerRepository.findByCustomerName(name);
	}

}
