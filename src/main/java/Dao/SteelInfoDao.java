package Dao;

import model.SteelInfo;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SteelInfoDao {
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet results;

    // 插入新的钢材信息
    public void insertSteelInfo(SteelInfo steelInfo) {
        con = ConnectionManager.getConnection();
        if (con == null) {
            System.out.println("错误：无法获取数据库连接");
            return;
        }

        try {
            String sql = "INSERT INTO steel_info (steel_type, steel_origin, production_date, manufacturer, price, volume) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, steelInfo.getSteelType());
            pstmt.setString(2, steelInfo.getSteelOrigin());
            pstmt.setDate(3, new java.sql.Date(steelInfo.getProductionDate().getTime()));
            pstmt.setString(4, steelInfo.getManufacturer());
            pstmt.setDouble(5, steelInfo.getPrice());
            pstmt.setDouble(6, steelInfo.getVolume());
            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
            ConnectionManager.closePreparedStatement(pstmt);
            ConnectionManager.closeResultSet(results);
        }
    }

    // 更新钢材信息
    public void updateSteelInfo(SteelInfo steelInfo) {
        con = ConnectionManager.getConnection();
        try {
            String sql = "UPDATE steel_info SET steel_type = ?, steel_origin = ?, production_date = ?, manufacturer = ?, price = ?, volume = ? WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, steelInfo.getSteelType());
            pstmt.setString(2, steelInfo.getSteelOrigin());
            pstmt.setDate(3, new java.sql.Date(steelInfo.getProductionDate().getTime()));
            pstmt.setString(4, steelInfo.getManufacturer());
            pstmt.setDouble(5, steelInfo.getPrice());
            pstmt.setDouble(6, steelInfo.getVolume());
            pstmt.setInt(7, steelInfo.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
            ConnectionManager.closePreparedStatement(pstmt);
            ConnectionManager.closeResultSet(results);
        }
    }

    // 删除钢材信息
    public void deleteSteelInfo(int id) {
        con = ConnectionManager.getConnection();
        try {
            String sql = "DELETE FROM steel_info WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
            ConnectionManager.closePreparedStatement(pstmt);
            ConnectionManager.closeResultSet(results);
        }
    }

    // 根据ID查找钢材信息
    public SteelInfo findSteelInfoById(int id) {
        SteelInfo steelInfo = new SteelInfo();
        con = ConnectionManager.getConnection();
        try {
            String sql = "SELECT * FROM steel_info WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            results = pstmt.executeQuery();
            if (results.next()) {
                steelInfo.setId(results.getInt("id"));
                steelInfo.setSteelType(results.getString("steel_type"));
                steelInfo.setSteelOrigin(results.getString("steel_origin"));
                steelInfo.setProductionDate(results.getDate("production_date"));
                steelInfo.setManufacturer(results.getString("manufacturer"));
                steelInfo.setPrice(results.getDouble("price"));
                steelInfo.setVolume(results.getDouble("volume"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
            ConnectionManager.closePreparedStatement(pstmt);
            ConnectionManager.closeResultSet(results);
        }
        return steelInfo;
    }

    // 获取所有钢材信息
    public List<SteelInfo> findAll() {
        List<SteelInfo> steelInfoList = new ArrayList<>();
        con = ConnectionManager.getConnection();
        try {
            String sql = "SELECT * FROM steel_info";
            pstmt = con.prepareStatement(sql);
            results = pstmt.executeQuery();
            while (results.next()) {
                SteelInfo steelInfo = new SteelInfo();
                steelInfo.setId(results.getInt("id"));
                steelInfo.setSteelType(results.getString("steel_type"));
                steelInfo.setSteelOrigin(results.getString("steel_origin"));
                steelInfo.setProductionDate(results.getDate("production_date"));
                steelInfo.setManufacturer(results.getString("manufacturer"));
                steelInfo.setPrice(results.getDouble("price"));
                steelInfo.setVolume(results.getDouble("volume"));
                steelInfoList.add(steelInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
            ConnectionManager.closePreparedStatement(pstmt);
            ConnectionManager.closeResultSet(results);
        }
        return steelInfoList;
    }

    // 搜索钢材信息
    public List<SteelInfo> searchSteelInfo(String type, String origin, String manufacturer, String date) {
        List<SteelInfo> steelInfoList = new ArrayList<>();
        con = ConnectionManager.getConnection();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM steel_info WHERE 1=1");
            List<Object> params = new ArrayList<>();

            if (type != null && !type.trim().isEmpty()) {
                sql.append(" AND steel_type LIKE ?");
                params.add("%" + type.trim() + "%");
            }
            if (origin != null && !origin.trim().isEmpty()) {
                sql.append(" AND steel_origin LIKE ?");
                params.add("%" + origin.trim() + "%");
            }
            if (manufacturer != null && !manufacturer.trim().isEmpty()) {
                sql.append(" AND manufacturer LIKE ?");
                params.add("%" + manufacturer.trim() + "%");
            }
            if (date != null && !date.trim().isEmpty()) {
                sql.append(" AND DATE(production_date) = ?");
                params.add(date.trim());
            }

            pstmt = con.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            results = pstmt.executeQuery();
            while (results.next()) {
                SteelInfo steelInfo = new SteelInfo();
                steelInfo.setId(results.getInt("id"));
                steelInfo.setSteelType(results.getString("steel_type"));
                steelInfo.setSteelOrigin(results.getString("steel_origin"));
                steelInfo.setProductionDate(results.getDate("production_date"));
                steelInfo.setManufacturer(results.getString("manufacturer"));
                steelInfo.setPrice(results.getDouble("price"));
                steelInfo.setVolume(results.getDouble("volume"));
                steelInfoList.add(steelInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
            ConnectionManager.closePreparedStatement(pstmt);
            ConnectionManager.closeResultSet(results);
        }
        return steelInfoList;
    }
} 