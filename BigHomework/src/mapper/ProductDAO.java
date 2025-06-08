package mapper;
import products.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class ProductDAO<T extends Product> {
    protected Connection connection;

    public ProductDAO() throws SQLException, SQLException {
        String url = "jdbc:mysql://127.0.0.1/jj";
        String name = "root";
        String pass = "2856208614";
        connection = DriverManager.getConnection(url, name, pass);
    }

    protected void insertProductTable(Product product) throws SQLException {
        String sql = "insert into product(id, name, type, price, description) values(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, product.getID());
        ps.setString(2, product.getName());
        ps.setString(3, product.getType());
        ps.setDouble(4, product.getTotalPrice());
        ps.setString(5, product.getDescription());
        ps.executeUpdate();

        ps.close();
    }

    public ArrayList<Product> loadProductTable() throws SQLException {
        String sql = "SELECT * FROM product p " +
                "LEFT JOIN fruit f on f.id = p.id " +
                "LEFT JOIN wood w ON w.id = p.id ";

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<Product> products = new ArrayList<>();
        HashMap<T, Integer> productsCount = new HashMap<>();
        while (rs.next()) {
            //Product表
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String type = rs.getString("type");
            double price = rs.getDouble("price");
            String description = rs.getString("description");

            switch (type) {
                case "Fruit" -> {
                    String color = rs.getString("color");
                    double weight = rs.getDouble("weight");
                    String unit = rs.getString("unit");
                    String origin = rs.getString("origin");
                    double unitPrice = rs.getDouble("unitPrice");
                    LocalDate purchdate = LocalDate.of(2024, 1, 1);
                    Fruit fruit = new Fruit(name,id,color,weight,unit,origin,purchdate,unitPrice);
                    products.add(fruit);
                }
                case "Wood" -> {
                    String color = rs.getString("color");
                    double weight = rs.getDouble("weight");
                    double height = rs.getDouble("height");
                    double thickness = rs.getDouble("thickness");
                    LocalDate purchdate = rs.getDate("purchday").toLocalDate();
                }
                case "Herb" -> {

                }
            }
        }
        ps.close();
        return products;
    }


    public abstract void insert(T product) throws SQLException;

    public abstract boolean update(T product) throws SQLException;
}
