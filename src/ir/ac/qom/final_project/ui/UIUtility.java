package ir.ac.qom.final_project.ui;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class UIUtility {

    public static void setLocation(Window window, int width, int height){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        double screenWidth = dimension.getWidth();
        double screenHeight = dimension.getHeight();
        int x = (int) ((screenWidth-width)/2);
        int h = (int) ((screenHeight-height)/2);
        window.setLocation(x, h);
        window.setSize(width, height);
    }

    public static JLabel createLabel(String key){
        JLabel lbl = new JLabel(I18NUtility.getMessage(key));
        setOrientation(lbl);
        return lbl;
    }

    public static JTextField createField(){
        JTextField fld = new JTextField();
        setOrientation(fld);
        return fld;
    }

    public static JPasswordField createPasswordField(){
        JPasswordField fld = new JPasswordField();
        setOrientation(fld);
        return fld;
    }

    public static JButton createButton(String key){
        JButton btn = new JButton(I18NUtility.getMessage(key));
        setOrientation(btn);
        return btn;
    }

    public static JMenuBar createMenuBar(){
        JMenuBar bar = new JMenuBar();
        setOrientation(bar);
        return bar;
    }

    public static JMenu createMenu(String key){
        JMenu menu= new JMenu(I18NUtility.getMessage(key));
        setOrientation(menu);
        return menu;
    }

    public static JMenuItem createMenuItem(String key){
        JMenuItem item= new JMenuItem(I18NUtility.getMessage(key));
        setOrientation(item);
        return item;
    }

    public static JRadioButtonMenuItem createRadioButtonMenuItem(String key, boolean selected){
        JRadioButtonMenuItem item= new JRadioButtonMenuItem(I18NUtility.getMessage(key), selected);
        setOrientation(item);
        return item;
    }
    public static JTable createTable(TableModel model){
        JTable table = new JTable(model);
        setOrientation(table);
        return table;
    }

    public static boolean isLTR(){
        return I18NUtility.getLanguage().equals("en");
    }

    private static void setOrientation(JComponent component){
        if(isLTR()){
            component.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            component.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        }else{
            component.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            component.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
    }
}
