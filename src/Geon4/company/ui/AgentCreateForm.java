package Geon4.company.ui;

import Geon4.company.entity.AgentEntity;
import Geon4.company.manager.AgentEntityManager;
import Geon4.company.util.BaseFrame;

import javax.swing.*;
import java.sql.SQLException;

public class AgentCreateForm extends BaseFrame {
    private JPanel mainPanel;
    private JTextField titleField;
    private JTextField priorityField;
    private JTextField agentTypeField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField logoField;
    private JButton saveButton;
    private JButton backButton;

    public AgentCreateForm() {
        setContentPane(mainPanel);

        initButtons();

        setVisible(true);

    }

    private void initButtons() {
        saveButton.addActionListener(a -> {
            String title = titleField.getText();
            if (title.length()>20 || title.isEmpty()){
                JOptionPane.showMessageDialog(this, "Название не введено или слишком большое", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (priorityField.getText().isEmpty()|| priorityField.getText().length()>30){
                JOptionPane.showMessageDialog(this, "Неверный формат ввода приоритета", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                Double priority = Double.valueOf(priorityField.getText());
            } catch (Exception e){
                JOptionPane.showMessageDialog(this, "Неверный формат ввода приоритета", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Double priority = Double.valueOf(priorityField.getText());
            String agentType = agentTypeField.getText();
            if (agentType.length()>20 || agentType.isEmpty()){
                JOptionPane.showMessageDialog(this, "Тип агента не введен или слишком большой", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }


            String address = addressField.getText();
            if (address.length()>40 || address.isEmpty()){
                JOptionPane.showMessageDialog(this, "Адресс не введен или слишком большой", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }


            String phone = phoneField.getText();
            if (phone.length()>20 || phone.isEmpty()){
                JOptionPane.showMessageDialog(this, "Телефон не введен или слишком большой", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String logo = logoField.getText();
            if (logo.length()>20){
                JOptionPane.showMessageDialog(this, "Путь до логотипа слишком большой", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String email = emailField.getText();
            if (email.length()>20 || email.isEmpty()){
                JOptionPane.showMessageDialog(this, "Почта не введена или слишком большая", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            AgentEntity agent = new AgentEntity(
                    title,
                    agentType,
                    address,
                    phone,
                    email,
                    logo,
                    priority
            );
            try {
                AgentEntityManager.insert(agent);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Данные введены некорректно", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(this, "Клиент добавлен", "Уведомление",JOptionPane.INFORMATION_MESSAGE);


        });

        backButton.addActionListener(e ->{

new AgentTableForm();
            dispose();
        });
    }


}
