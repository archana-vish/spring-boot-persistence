package ac.av8242n.persistence.dao;

import ac.av8242n.persistence.entities.Officer;
import ac.av8242n.persistence.entities.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcOfficerDao implements OfficerDao {

    private JdbcTemplate jdbcTemplate;


    public JdbcOfficerDao(@Qualifier("dataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Officer save(Officer officer) {
        return null;
    }

    @Override
    public Optional<Officer> findById(Integer id) {
        if (!existsById(id)) return Optional.empty();
        // Java 7 anonymous inner class
        return Optional.of(jdbcTemplate.queryForObject(
                "SELECT * FROM officers WHERE id=?",
                (rs, rowNum) -> new Officer(rs.getInt("id"),
                        Rank.valueOf(rs.getString("rank")),
                        rs.getString("first_name"),
                        rs.getString("last_name")),
                id));
    }

    @Override
    public List<Officer> findAll() {
        return null;
    }

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("select count(*) from officers", Long.class);
    }

    @Override
    public void delete(Officer officer) {
        jdbcTemplate.update("DELETE FROM officers WHERE id=?", officer.getId());
    }

    @Override
    public boolean existsById(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM officers where id=?)", Boolean.class, id);
    }
}
