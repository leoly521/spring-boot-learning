package com.example.springbootlearning.impl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.springbootlearning.entity.Account;
import com.example.springbootlearning.intf.dao.AccountDao;

@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int add(Account account) {
        return jdbcTemplate.update("insert into TB_ACC(ACC_NAME, ACC_MONEY) values(?, ?)",
                account.getName(), account.getMoney());
    }

    @Override
    public int update(Account account) {
        return jdbcTemplate.update("update TB_ACC set ACC_NAME=?, ACC_MONEY=? where ACC_ID=?",
                account.getName(), account.getMoney(), account.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("delete from TB_ACC where ACC_ID=?",
                id);
    }

    @Override
    public Account getById(int id) {
        List<Account> list = jdbcTemplate.query("select * from TB_ACC where ACC_ID=?",
                new Object[]{id}, new AccountRowMapper());
        if (list != null && list.size() > 0) {
            Account account = list.get(0);
            return account;
        } else {
            return null;
        }
    }

    @Override
    public List<Account> findAll() {
        List<Account> list = jdbcTemplate.query("select * from TB_ACC",
                new Object[]{}, new AccountRowMapper());
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    private class AccountRowMapper implements RowMapper<Account> {

        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            Account account = new Account();
            account.setId(rs.getInt("ACC_ID"));
            account.setName(rs.getString("ACC_NAME"));
            account.setMoney(rs.getDouble("ACC_MONEY"));
            return account;
        }
    }
}
