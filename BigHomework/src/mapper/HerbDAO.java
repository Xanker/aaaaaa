package mapper;

import products.Herb;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HerbDAO extends ProductDAO<Herb> {
    public HerbDAO() throws SQLException {
        super();

    }
    public void insert(Herb herb) throws SQLException {
         insertProductTable(herb);

         String sql = "insert into herb values(id,name,origin,pSeason,pMonth,property,TotalPrice)values(?,?,?,?,?,?,?)";
         PreparedStatement ps = connection.prepareStatement(sql);
         ps.setInt(1,herb.getID());
         ps.setString(2,herb.getName());
         ps.setString(3,herb.getOrigin());
         ps.setString(4,herb.getSeason());
         ps.setInt(5,herb.getMonth());
         ps.setString(6,herb.getProperty());
         ps.setDouble(7,herb.getTotalPrice());
         ps.executeUpdate();
         ps.close();


    }
    public boolean update(Herb herb) throws SQLException {
        // 1. 更新product表
        boolean productUpdated = updateProductTable(herb);

        // 2. 更新herb表
        boolean herbUpdated = updateHerbTable(herb);

        return productUpdated && herbUpdated;
    }

    // 更新product表的单独方法
    private boolean updateProductTable(Herb herb) throws SQLException {
        String sql = "UPDATE product SET name = ?, type = ?, price = ?, description = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, herb.getName());
            ps.setString(2, herb.getType());
            ps.setDouble(3, herb.getTotalPrice());
            ps.setString(4, herb.getDescription());
            ps.setInt(5, herb.getID());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // 更新herb表的单独方法
    private boolean updateHerbTable(Herb herb) throws SQLException {
        String sql = "UPDATE herb SET name = ?, origin = ?, pSeason = ?, pMonth = ?,  property = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, herb.getName());
            ps.setString(2, herb.getOrigin());
            ps.setString(3, herb.getSeason());
            ps.setInt(4, herb.getMonth());
            ps.setDate(5, Date.valueOf(herb.getPurchday()));
            ps.setString(6, herb.getProperty());
            ps.setInt(7, herb.getID());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
