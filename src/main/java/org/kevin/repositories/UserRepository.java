package org.kevin.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.kevin.entities.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {



}
