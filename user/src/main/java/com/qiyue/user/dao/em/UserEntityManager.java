package com.qiyue.user.dao.em;

import com.qiyue.user.dao.entity.MenuLoanEntity;
import com.qiyue.user.dao.entity.UserMenuEntity;
import com.qiyue.common.response.Response;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserEntityManager {
    @Autowired
    private EntityManager entityManager;

    public Response userMenuAddBatch(List<UserMenuEntity> list) {
        try {
            String sql = "insert into user_menu (user_id,menu_code) values (?,?)";
            SessionImplementor session = entityManager.unwrap(SessionImplementor.class);
            Connection conn = session.connection();
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < list.size(); i++) {
                ps.setInt(1, list.get(i).getUserId());
                ps.setString(2, list.get(i).getMenuCode());
                ps.addBatch();
                if ((i + 1) % 50 == 0) {
                    int[] ints = ps.executeBatch();
                }
            }
            int[] ints = ps.executeBatch();
            return Response.success("ok");
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.fail("USER_MENU_INSERT_BATCH_ERROR");
        }
    }

    public Response menuLoanAddBatch(List<MenuLoanEntity> list) {
        try {
            String sql = "insert into menu_loan (user_id, menu_code, loan_user_id) " +
                    "	values (?,?,?)";
            SessionImplementor session = entityManager.unwrap(SessionImplementor.class);
            Connection conn = session.connection();
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < list.size(); i++) {
                ps.setInt(1, list.get(i).getUserId());
                ps.setString(2, list.get(i).getMenuCode());
                ps.setInt(3, list.get(i).getLoanUserId());
                ps.addBatch();
                if ((i + 1) % 50 == 0) {
                    int[] ints = ps.executeBatch();
                }
            }
            int[] ints = ps.executeBatch();
            return Response.success("ok");
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.fail("MENU_LOAN_INSERT_BATCH_ERROR");
        }
    }
}
