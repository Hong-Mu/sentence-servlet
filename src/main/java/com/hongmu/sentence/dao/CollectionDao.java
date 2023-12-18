package com.hongmu.sentence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hongmu.sentence.model.Collection;
import com.hongmu.sentence.model.Sentence;
import com.hongmu.sentence.model.User;
import com.hongmu.sentence.util.JDBCUtil;

public class CollectionDao {

    public int insert(String title, int userId) {
        int result = 0;
        try (Connection connection = JDBCUtil.getConnection()) {

            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO collections (title, user_id) VALUES (?, ?);");

            statement.setString(1, title);
            statement.setInt(2, userId);

            System.out.println(statement);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtil.printSQLException(e);
        }
        return result;
    }

    public List<Collection> getAll() {
        List<Collection> list = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection();) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM collections");
            ResultSet rs = statement.executeQuery(); // 쿼리 실행
            System.out.println(statement);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int userId = rs.getInt("user_id");
                list.add(new Collection(id, title, userId));
            }
        } catch (SQLException e) {
            JDBCUtil.printSQLException(e);
        }
        return list;
    }

    public Collection get(int id) {
        Collection collection = null;
        try (Connection connection = JDBCUtil.getConnection();) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM collections WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery(); // 쿼리 실행
            System.out.println(statement);
            while (rs.next()) {
                String title = rs.getString("title");
                int userId = rs.getInt("user_id");
                collection = new Collection(id, title, userId);
            }
        } catch (SQLException e) {
            JDBCUtil.printSQLException(e);
        }
        return collection;
    }
}
