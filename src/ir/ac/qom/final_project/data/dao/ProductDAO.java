package ir.ac.qom.final_project.data.dao;

import ir.ac.qom.final_project.data.entity.Product;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class ProductDAO extends AbstractDAO<Product> {

    String productMaxID = "select max(id) from product_tbl";
    String saveSQL = "insert into product_tbl (id, name_product, date_manufacture, price, quantity) " + "values (?, ?, ?, ?, ?)";
    String updateSQL = "update product_tbl set name_product=?, date_manufacture=?, price=? , quantity=? where id=?";
    String deleteSQL = "delete from product_tbl where id=?";
    //String changePasswordSQL = "update product_tbl set password=? where name=? and password=?";
    String countSQL = "select count(*) from product_tbl ";
    //String loginSQL = "select * from product_tbl where name=? and password=?";
    String findAllSQL = "select * from product_tbl ";
    Long currentId;

    Logger logger = Logger.getLogger(ProductDAO.class.getName());

    public ProductDAO() {
        try {
            PreparedStatement ps = connection.prepareStatement(productMaxID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                currentId = rs.getLong(1);
            } else {
                currentId = 0L;
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product save(Product product) throws SQLException {
        logger.info("Save: " + product);
        PreparedStatement ps = connection.prepareStatement(saveSQL);
        currentId++;
        product.setId(currentId);
        ps.setLong(1, product.getId());
        ps.setString(2, product.getNameOfProduct());
        ps.setDate(3, new Date(product.getDateOfManufacture().getTime()));
        ps.setDouble(4, product.getPrice());
        ps.setInt(5, product.getQuantity());
        ps.executeUpdate();
        return product;
    }

    @Override
    public Product update(Product product) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(updateSQL);
        ps.setString(1, product.getNameOfProduct());
        ps.setDate(2, new Date(product.getDateOfManufacture().getTime()));
        ps.setDouble(3, product.getPrice());
        ps.setInt(4, product.getQuantity());
        ps.setLong(5, product.getId());
        ps.executeUpdate();
        connection.commit();
        return product;
    }

    @Override
    public Product delete(Product product) throws SQLException {
        logger.info("Delete: " + product);
        PreparedStatement ps = connection.prepareStatement(deleteSQL);
        ps.setLong(1, product.getId());
        ps.executeUpdate();
        connection.commit();
        return product;
    }

    @Override
    public int count() throws SQLException {
        PreparedStatement ps = connection.prepareStatement(countSQL);
        Long count = 0L;
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getLong(1);
        }
        connection.commit();
        return count.intValue();
    }

    @Override
    public List<Product> findAll() throws SQLException {
        PreparedStatement ps = connection.prepareStatement(findAllSQL);
        List<Product> products = new ArrayList<Product>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Long id = rs.getLong("id");
            String nameOfProduct = rs.getString("name_product");
            java.util.Date dateOfManufacture = rs.getDate("date_manufacture");
            Double price = rs.getDouble("price");
            Integer quantity = rs.getInt("quantity");
            products.add(new Product(id, nameOfProduct, dateOfManufacture, price, quantity));
        }
        connection.commit();
        return products;
    }

    public boolean login(String name, String password) throws SQLException, IOException {
//        PreparedStatement ps = connection.prepareStatement(loginSQL);
//        ps.setString(1, name);
//        ps.setString(2, password);
//        ResultSet rs = ps.executeQuery();
//
//        if (rs.next()) {
//            result = true;
//        }
//        connection.commit();
        boolean result = false;
        InputStream is = ProductDAO.class.getClassLoader().getResourceAsStream("META-INF/login.properties");
        Properties properties = new Properties();
        properties.load(is);
        if (properties.getProperty("username").equals(name) && properties.getProperty("password").equals(password))
            result = true;
        return result;
    }


    public void commit() throws SQLException {
        connection.commit();
    }

}
