package ac.av8242n.persistence.dao;

import ac.av8242n.persistence.entities.Officer;

import java.util.List;
import java.util.Optional;

public interface OfficerDao {
    Officer save(Officer officer);
    Optional<Officer> findById(Integer id);
    List<Officer> findAll();
    long count();
    void delete(Officer officer);
    boolean existsById(Integer id);
}
