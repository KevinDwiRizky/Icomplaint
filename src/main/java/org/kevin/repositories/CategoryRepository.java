package org.kevin.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.kevin.entities.Category;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category> {

}
