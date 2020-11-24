package com.stakelimitservicetask.stakelimitservice.dao;

import com.stakelimitservicetask.stakelimitservice.enums.TicketMessageStatus;
import com.stakelimitservicetask.stakelimitservice.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DeviceDataAccess implements  DeviceDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DeviceDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<Device> findDevice(UUID deviceid) {
        final String sql="SELECT * FROM device WHERE id=?";

        try {
            Device device=jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{deviceid},
                    (resultSet, i) -> {
                        UUID id=UUID.fromString(resultSet.getString("id"));
                        int timeduration=resultSet.getInt("timeduration");
                        int restrexpiry=resultSet.getInt("restrexpiry");
                        long blockedat=resultSet.getLong("blockedat");
                        long starttime=resultSet.getLong("starttime");
                        double stakelimit=resultSet.getDouble("stakelimit");
                        double hotamountpct=resultSet.getDouble("hotamountpct");
                        double sumstakes=resultSet.getDouble("sumstakes");

                        TicketMessageStatus devicestatus=TicketMessageStatus.castIntToEnum(resultSet.getInt("devicestatus"));

                        return new Device(id,timeduration,stakelimit,hotamountpct,sumstakes, restrexpiry, devicestatus,blockedat, starttime);
                    }
            );
            return Optional.ofNullable(device);
        }
        catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int updateDevice(UUID deviceId, Device device) {
        final String updateSql="UPDATE device SET "+
                "stakelimit=?," +
                "hotamountpct=?," +
                "restrexpiry=?," +
                "timeduration=?," +
                "devicestatus=?," +
                "sumstakes=?," +
                "blockedat=?," +
                "starttime=?" +
                " WHERE id=?";



        // define query arguments
        Object[] params = { device.getStakeLimit(),device.getHotAmountPctg(),device.getRestrExpiry(),
        device.getTimeDuration(),device.getDevicestatus().ordinal(),device.getSumStakes(),device.getBlockedat(), device.getStartTime(),
                deviceId
        };


        // define SQL types of the arguments
        int[] types = {Types.DOUBLE, Types.DOUBLE, Types.INTEGER, Types.INTEGER, Types.INTEGER,Types.DOUBLE,
        Types.BIGINT, Types.BIGINT ,Types.OTHER
        };


        return jdbcTemplate.update(updateSql, params, types);

    }

    @Override
    public int insertDevice(Device device) {
        final String sql="INSERT INTO device " +
                "(id,stakeLimit,hotAmountPct, restrExpiry,timeDuration,deviceStatus)"+
                " values"+
                "(?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                device.getId(),
                device.getStakeLimit(),
                device.getHotAmountPctg(),
                device.getRestrExpiry(),
                device.getTimeDuration(),
                device.getDevicestatus().ordinal()
        );
    }
}
