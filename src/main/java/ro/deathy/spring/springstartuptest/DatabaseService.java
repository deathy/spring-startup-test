package ro.deathy.spring.springstartuptest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DatabaseService {

    @PersistenceContext(unitName = "default")
    private final EntityManager entityManager;

    public DatabaseService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
