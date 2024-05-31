package org.kevin.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.kevin.entities.User;

import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {


    public Optional<User> findByEmail(String email) {
        return find("email", email).singleResultOptional();
    }

}
