package com.hongmu.sentence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.hongmu.sentence.model.Sentence;
import com.hongmu.sentence.util.JDBCUtil;

import jakarta.annotation.Resource;

public class SentenceDao {

    public int insert(String title, String subtitle, int userId) {
        int result = 0;
        try (Connection connection = JDBCUtil.getConnection()) {

            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO sentences (title, subtitle, user_id) VALUES (?, ?, ?);");

            statement.setString(1, title);
            statement.setString(2, subtitle);
            statement.setInt(3, userId);

            System.out.println(statement);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtil.printSQLException(e);
        }
        return result;
    }

    public int setCollection(int sentenceId, int collectionId) {
        int result = 0;
        try (Connection connection = JDBCUtil.getConnection()) {

            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO sentencecollections (sentence_id, collection_id) VALUES (?, ?);");

            statement.setInt(1, sentenceId);
            statement.setInt(2, collectionId);

            System.out.println(statement);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtil.printSQLException(e);
        }
        return result;
    }

    public List<Sentence> getAll() {
        List<Sentence> list = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection();) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM sentences");
            ResultSet rs = statement.executeQuery(); // 쿼리 실행
            System.out.println(statement);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String subtitle = rs.getString("subtitle");
                int userId = rs.getInt("user_id");
                list.add(new Sentence(id, title, subtitle, userId));
            }
        } catch (SQLException e) {
            JDBCUtil.printSQLException(e);
        }
        return list;
    }

    public List<Sentence> getByCollectionId(int collectionId) {
        List<Sentence> list = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection();) {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "SELECT * FROM sentences WHERE id IN (SELECT sentence_id FROM sentencecollections WHERE collection_id = ?)");

            statement.setInt(1, collectionId);
            ResultSet rs = statement.executeQuery(); // 쿼리 실행
            System.out.println(statement);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String subtitle = rs.getString("subtitle");
                int userId = rs.getInt("user_id");
                list.add(new Sentence(id, title, subtitle, userId));
            }
        } catch (SQLException e) {
            JDBCUtil.printSQLException(e);
        }
        return list;
    }

    public int setSentence(int sentenceId, int collectionId) {
        int result = 0;
        try (Connection connection = JDBCUtil.getConnection()) {

            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO sentencecollections (sentence_id, collection_id) VALUES (?, ?);");

            statement.setInt(1, sentenceId);
            statement.setInt(2, collectionId);

            System.out.println(statement);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtil.printSQLException(e);
        }
        return result;
    }

    public Sentence getRandom() {
        Sentence sentence = null;
        try (Connection connection = JDBCUtil.getConnection();) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM sentences ORDER BY RAND() LIMIT 1");
            ResultSet rs = statement.executeQuery(); // 쿼리 실행

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String subtitle = rs.getString("subtitle");
                int userId = rs.getInt("user_id");
                sentence = new Sentence(id, title, subtitle, userId);
            }
        } catch (SQLException e) {
            JDBCUtil.printSQLException(e);
        }
        return sentence;
    }
}
