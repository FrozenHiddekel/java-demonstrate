package Geon4.company.manager;

import Geon4.company.Application;
import Geon4.company.entity.AgentEntity;

import java.sql.*;
import java.util.*;

public class AgentEntityManager {
    public static void insert(AgentEntity agent) throws SQLException {

        try (Connection c = Application.getConnection()){
            String sql = "insert into agent(Title, AgentType, Address, Phone, Email, Logo, Priority) values(?,?,?,?,?,?,?)";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, agent.getTitle());
            ps.setString(2,agent.getAgentType());
            ps.setString(3, agent.getAddress());
            ps.setString(4, agent.getPhone());
            ps.setString(5, agent.getEmail());
            ps.setString(6, agent.getLogo());
            ps.setDouble(7, agent.getPriority());
            ps.executeUpdate();
        }
    }
    public static AgentEntity selectById(int id) throws SQLException {
        try (Connection c = Application.getConnection()) {
            String sql = "select * from agent where id=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return new AgentEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("Title"),
                        resultSet.getString("AgentType"),
                        resultSet.getString("address"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email"),
                        resultSet.getString("logo"),
                        resultSet.getInt("Priority")
                );
            }
        }
        return null;
    }
    public static List<AgentEntity> selectAll() throws SQLException {
        try (Connection c = Application.getConnection()) {
            String sql = "select * from agent";
            Statement ps = c.createStatement();
            ResultSet resultSet = ps.executeQuery(sql);
            List<AgentEntity> list = new ArrayList<>();
            while (resultSet.next()){
                list.add(new AgentEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("Title"),
                        resultSet.getString("AgentType"),
                        resultSet.getString("address"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email"),
                        resultSet.getString("logo"),
                        resultSet.getInt("Priority")
                ));
            }
            return list;
        }
    }
    public static void update(AgentEntity agent) throws SQLException {

        try (Connection c = Application.getConnection()){
            String sql = "update agent set Title=?, AgentType=?, Address=?, Phone=?, Email=?, Logo=?, Priority=? where id = ?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, agent.getTitle());
            ps.setString(2,agent.getAgentType());
            ps.setString(3, agent.getAddress());
            ps.setString(4, agent.getPhone());
            ps.setString(5, agent.getEmail());
            ps.setString(6, agent.getLogo());
            ps.setDouble(7, agent.getPriority());
            ps.setInt(8,agent.getId());

            ps.executeUpdate();
        }

    }


    public static void deleteById(int id) throws SQLException {
        try (Connection c = Application.getConnection()){
            String sql = "Delete from agent where ID = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }


    public static void delete(AgentEntity agent) throws SQLException {
        deleteById(agent.getId());
    }


}
