package com.stakelimitservicetask.stakelimitservice.dao;

import com.stakelimitservicetask.stakelimitservice.model.TicketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class TicketMessageDataAccess implements TicketMessageDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TicketMessageDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertTicketMessage(TicketMessage tm) {
        final String sql="INSERT INTO ticketmessage " +
                "(id,deviceId,stake)"+
                " values"+
                "(?,?,?)";
       return jdbcTemplate.update(sql,
                tm.getId(),
                tm.getDeviceId(),
                tm.getStake()
                );
    }

    @Override
    public Optional<TicketMessage> findTicketMessage(UUID ticketMessageId) {
        final String sql="SELECT * FROM ticketmessage WHERE id=?";

        try {
            TicketMessage ticketMessage=jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{ticketMessageId},
                    (resultSet, i) -> {
                        UUID id=UUID.fromString(resultSet.getString("id"));
                        UUID deviceid=UUID.fromString(resultSet.getString("deviceid"));
                        double stake=resultSet.getDouble("stake");

                        return new TicketMessage(id, deviceid, stake);
                    }
            );
            return Optional.ofNullable(ticketMessage);
        }
        catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }
}
