package ir.ac.qom.final_project.ui;

import ir.ac.qom.final_project.data.dao.ProductDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialog extends JDialog implements ActionListener {
    JLabel messageLbl = new JLabel("");
    JLabel userLbl = UIUtility.createLabel("general.user");
    JLabel passwordLbl = UIUtility.createLabel("general.password");

    JTextField userFld = UIUtility.createField();
    JPasswordField passwordFld = UIUtility.createPasswordField();

    JButton loginBtn = UIUtility.createButton("login.btn.login");
    JButton cancelBtn = UIUtility.createButton("login.btn.cancel");

    ProductDAO productDAO = new ProductDAO();

    public LoginDialog() {
        setTitle(I18NUtility.getMessage("login.title"));
        messageLbl.setForeground(Color.RED);
        messageLbl.setHorizontalAlignment(JLabel.CENTER);
        loginBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(userLbl);
        panel.add(userFld);
        panel.add(passwordLbl);
        panel.add(passwordFld);
        panel.add(loginBtn);
        panel.add(cancelBtn);
        add(panel, BorderLayout.CENTER);
        add(messageLbl, BorderLayout.NORTH);
        UIUtility.setLocation(this, 250, 120);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginDialog();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == loginBtn) {
                String user = userFld.getText();
                String password = new String(passwordFld.getPassword());
                if (productDAO.login(user, password)) {
                    this.dispose();
                    ProductMngWindow umw = new ProductMngWindow();
                } else {
                    messageLbl.setText(I18NUtility.getMessage("login.user.password.invalid"));
                }
            } else {
                System.exit(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
