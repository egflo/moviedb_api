package com.moviedb_api.security;



import com.moviedb_api.customer.Customer;
import com.moviedb_api.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
class MyUserDetailsService implements UserDetailsService {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private CustomerRepository customerRepository;

    public Customer loadUserByUsername(String email) {
        Optional<Customer> user = customerRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(email);
        }
        return user.get();
    }

    /**
    private Set<SimpleGrantedAuthority> getAuthority(Customer user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getAuthorities().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

     **/
}
