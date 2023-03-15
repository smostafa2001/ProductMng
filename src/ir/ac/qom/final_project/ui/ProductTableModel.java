package ir.ac.qom.final_project.ui;

import ir.ac.qom.final_project.data.entity.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProductTableModel extends AbstractTableModel {

    List<Product> products = new ArrayList<>();

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return I18NUtility.getMessage("product.dialog.title");
        } else if (column == 1) {
            return I18NUtility.getMessage("product.date");
        } else if (column == 2) {
            return I18NUtility.getMessage("product.price");
        } else if (column == 3) {
            return I18NUtility.getMessage("product.quantity");
        }
        return "";
    }

    @Override
    public Object getValueAt(int row, int column) {
        Product user = products.get(row);
        if (column == 0) {
            return user.getNameOfProduct();
        } else if (column == 1) {
            return user.getDateOfManufacture();
        } else if (column == 2) {
            return user.getPrice();
        } else if (column == 3) {
            return user.getQuantity();
        }
        return "";
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        fireTableDataChanged();
    }

    public Product getSelectedUser(int row) {
        return products.get(row);
    }
}
