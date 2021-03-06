/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.crud;

import com.morethansimplycode.data.Data;
import com.morethansimplycode.data.DataAnnotationUtil;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Oscar Date 23-may-2015 Time 20:24:52
 */
public class DataDetail extends javax.swing.JPanel {

    private GridLayout layout;
    private Data data;
    private HashMap<String, JTextField> textFields;

    /**
     * Creates new form DataDetail
     */
    public DataDetail() {
        initComponents();
    }

    public void setClassData(Class<? extends Data> d) {

        String[] fields = DataAnnotationUtil.recoverDetailInfoFields(d);
        String[] names = DataAnnotationUtil.recoverDetailInfoNamesShow(d);

        layout = new GridLayout(fields.length, 2, 10, 10);
        this.setLayout(layout);

        if (textFields != null)
            textFields.clear();
        textFields = new HashMap<>(fields.length);

        if (names.length == 0) {

            JTextField textField;
            for (String field : fields) {

                this.add(new JLabel(field));
                textField = new JTextField();
                textFields.put(field, textField);
                this.add(textField);
            }
        } else {

            JTextField textField;
            for (int i = 0; i < fields.length; i++) {

                this.add(new JLabel(names[i]));
                textField = new JTextField();
                textFields.put(fields[i], textField);
                this.add(textField);
            }
        }
        this.updateUI();

    }

    public Data getData() {

        Set<String> keys = textFields.keySet();

        keys.stream().forEach((key) -> {

            if (data.get(key) instanceof Integer)
                data.put(key, Integer.parseInt(textFields.get(key).getText()));
            else
                data.put(key, textFields.get(key).getText());
        });

        return data;
    }

    public void setData(Data data) {

        this.data = data;
        if (data != null)
            fillFields();
        else
            cleanFields();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(153, 153, 255));
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private void fillFields() {

        Set<String> keys = textFields.keySet();

        keys.stream().forEach((key) -> {
            textFields.get(key).setText(data.get(key).toString());
        });
    }

    private void cleanFields() {

        for (Component c : this.getComponents()) {

            if (c instanceof JTextField)
                ((JTextField) c).setText("");
        }

    }
}
