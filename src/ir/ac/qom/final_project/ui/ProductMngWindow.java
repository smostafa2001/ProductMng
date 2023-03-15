package ir.ac.qom.final_project.ui;

import ir.ac.qom.final_project.data.dao.ProductDAO;
import ir.ac.qom.final_project.data.entity.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ProductMngWindow extends JFrame implements ActionListener {

    Logger logger = Logger.getLogger(ProductMngWindow.class.getName());

    ProductTableModel model = new ProductTableModel();
    JTable table = UIUtility.createTable(model);

    JButton addBtn = new JButton(new ImageIcon("icon/add.png"));
    JButton editBtn = new JButton(new ImageIcon("icon/edit.png"));
    JButton deleteBtn = new JButton(new ImageIcon("icon/delete.png"));
    JButton excelBtn = new JButton(new ImageIcon("icon/excel.png"));


    JRadioButtonMenuItem farsiItem = UIUtility.createRadioButtonMenuItem("main.menu.farsi", !UIUtility.isLTR());
    JRadioButtonMenuItem englishItem = UIUtility.createRadioButtonMenuItem("main.menu.english", UIUtility.isLTR());

    ProductDAO productDAO = new ProductDAO();

    public ProductMngWindow() {
        setJMenuBar(createMenuBar());
        add(createToolBar(), BorderLayout.PAGE_START);
        try {
            model.setProducts(productDAO.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        add(new JScrollPane(table));
        UIUtility.setLocation(this, 500, 250);
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = UIUtility.createMenuBar();
        JMenu fileMenu = UIUtility.createMenu("main.menu.file");
        ButtonGroup group = new ButtonGroup();
        group.add(farsiItem);
        group.add(englishItem);
        bar.add(fileMenu);
        farsiItem.addActionListener(this);
        englishItem.addActionListener(this);

        fileMenu.add(farsiItem);
        fileMenu.add(englishItem);
        return bar;
    }

    private JToolBar createToolBar() {
        JToolBar bar = new JToolBar();
        addBtn.addActionListener(this);
        editBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        excelBtn.addActionListener(this);
        bar.add(addBtn);
        bar.add(editBtn);
        bar.add(deleteBtn);
        bar.addSeparator();
        bar.add(excelBtn);
        return bar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == addBtn) {
                ProductDialog dialog = new ProductDialog();
                if (dialog.isOk()) {
                    Product user = dialog.getProduct();
                    dialog.dispose();
                    productDAO.save(user);
                    model.setProducts(productDAO.findAll());
                }
            } else if (e.getSource() == editBtn) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    Product user = model.getSelectedUser(row);
                    ProductDialog dialog = new ProductDialog(user);
                    if (dialog.isOk()) {
                        productDAO.update(user);
                        dialog.dispose();
                        model.setProducts(productDAO.findAll());
                    }
                }
            } else if (e.getSource() == deleteBtn) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    Product user = model.getSelectedUser(row);
                    int result = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        productDAO.delete(user);
                        model.setProducts(productDAO.findAll());
                    }
                }
            } else if (e.getSource() == farsiItem) {
                if (!I18NUtility.getLanguage().equals("fa")) {
                    I18NUtility.setLanguage("fa");
                    this.setVisible(true);
                    new ProductMngWindow();
                    this.dispose();
                }
            } else if (e.getSource() == englishItem) {
                if (!I18NUtility.getLanguage().equals("en")) {
                    I18NUtility.setLanguage("en");
                    this.setVisible(true);
                    new ProductMngWindow();
                    this.dispose();
                }
            } else if (e.getSource() == excelBtn) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    ExcelExporter.export(model.getProducts(), file);
                }
            }
        } catch (Exception ex) {
            logger.severe("Error: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}
