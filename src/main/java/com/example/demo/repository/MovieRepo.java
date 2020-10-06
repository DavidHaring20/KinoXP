package com.example.demo.repository;

import com.example.demo.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.List;
;
@Repository
public class MovieRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Movie> fetchAll(){
        String query = "SELECT m.*, a.name, d.start_date, d.end_date, s.start_time, r.type " +
                "FROM movie m, actor a, date d, showtime s, movie_has_date_has_showtime_has_room mhdhshr, movie_has_actor mha, room r " +
                "WHERE mhdhshr.movie_id = m.id AND mhdhshr.date_id = d.id AND mhdhshr.showtime_id = s.id AND mha.movie_id = m.id " +
                "AND mha.actor_id = a.id AND mhdhshr.room_id = r.id";
        RowMapper<Movie> rm = new BeanPropertyRowMapper<>(Movie.class);
        return jdbcTemplate.query(query, rm);
    }

}