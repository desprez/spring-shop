package fr.training.samples.spring.shop.application.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;

@Service
@Transactional
public class  UserDetailsServiceImpl implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;

	// ici je transforme mes objets du domain en objets internes de Spring Security
	@Override
	public UserDetails loadUserByUsername(final String customerName) throws UsernameNotFoundException {

		final Customer customer = customerRepository.findByCustomerName(customerName);
		if (customer == null) {
			throw new UsernameNotFoundException("Email " + customerName + " not found");
		}
		logger.debug("Customer found with name {}", customerName);
		return new User(customer.getName(), customer.getPassword(), getAuthorities(customer));
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(final Customer customer) {
		final String[] userRoles = customer.getRoles().stream().map((role) -> role.name()).toArray(String[]::new);
		logger.debug("With User Roles {}", userRoles);
		final Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}
}
