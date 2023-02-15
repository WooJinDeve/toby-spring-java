package com.helloboot;

import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepositoryJdbc implements HelloRepository{

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Hello> ROW_MAPPER = (rs, rowNum) ->
            new Hello(
                    rs.getString("name"),
                    rs.getInt("count")
            );

    public HelloRepositoryJdbc(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<Hello> findHello(String name) {
        try {
            String sql = String.format("SELECT * FROM hello WHERE name = '%s'", name);
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, ROW_MAPPER));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void increaseCount(String name) {
        Optional<Hello> hello = findHello(name);
        if (hello.isEmpty())
            jdbcTemplate.update("INSERT INTO hello VALUES(?, ?)", name, 1);
        else
            jdbcTemplate.update("UPDATE hello SET count = count + 1 WHERE name = ?",  name);
    }
}
