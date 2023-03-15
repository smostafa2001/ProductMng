package ir.ac.qom.final_project.ui;

import ir.ac.qom.final_project.data.entity.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class ProductDialog extends JDialog implements ActionListener {
    JLabel messageLbl = new JLabel("");
    JLabel nameLbl = new JLabel(I18NUtility.getMessage("product.dialog.name"));
    JLabel dateLbl = new JLabel(I18NUtility.getMessage("product.date"));
    JLabel priceLbl = new JLabel(I18NUtility.getMessage("product.price"));
    JLabel quantityLbl = new JLabel(I18NUtility.getMessage("product.quantity"));

    JTextField nameFld = new JTextField();
    JTextField dateFld = new JTextField();
    JTextField priceFld = new JTextField();
    JTextField quantityFld = new JTextField();


    JButton okBtn = new JButton(I18NUtility.getMessage("ok"));
    JButton cancelBtn = new JButton(I18NUtility.getMessage("login.btn.cancel"));

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Product product;
    boolean ok;

    public ProductDialog(Product product) {
        this.product = product;
        messageLbl.setForeground(Color.RED);
        messageLbl.setHorizontalAlignment(JLabel.CENTER);
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.add(nameLbl);
        panel.add(nameFld);
        panel.add(dateLbl);
        panel.add(dateFld);
        panel.add(priceLbl);
        panel.add(priceFld);
        panel.add(quantityLbl);
        panel.add(quantityFld);

        panel.add(okBtn);
        panel.add(cancelBtn);
        okBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        add(messageLbl, BorderLayout.NORTH);
        UIUtility.setLocation(this, 250, 220);
        setModal(true);
        if (product != null) {
            nameFld.setText(product.getNameOfProduct());
            dateFld.setText(dateFormat.format(product.getDateOfManufacture()));
            priceFld.setText(String.valueOf(product.getPrice()));
            quantityFld.setText(String.valueOf(product.getQuantity()));

        }
        setVisible(true);
    }

    public ProductDialog() {
        this(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == okBtn) {
                ok = true;
                String name = nameFld.getText();
                String dateOfManufacture = dateFld.getText();
                String price = priceFld.getText();
                String quantity = quantityFld.getText();

                if (product == null) {
                    product = new Product();
                }
                product.setNameOfProduct(name);
                product.setDateOfManufacture(dateFormat.parse(dateOfManufacture));
                product.setPrice(Double.parseDouble(price));
                product.setQuantity(Integer.parseInt(quantity));


            }
            setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Product getProduct() {
        return product;
    }

    public boolean isOk() {
        return ok;
    }
}
