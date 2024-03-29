package org.lessons.java.springlamiapizzeriacrud.security;

import org.lessons.java.springlamiapizzeriacrud.model.User;
import org.lessons.java.springlamiapizzeriacrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    //ci serve uno UserRepository per fare query su db sulla tabella users
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //devo recuperare uno User da db a partire da stringa username
        Optional<User> result = userRepository.findByEmail(username);
        if (result.isPresent()) {
            //devo costruire uno UserDetails a partire da quello User
            return new DatabaseUserDetails(result.get());
        } else {
            //se non trovo utente con quella email sollevo eccezione
            throw new UsernameNotFoundException("User with email " + username + " not found");
        }
    }
}
