package Geon4.company.ui;

import Geon4.company.entity.AgentEntity;
import Geon4.company.manager.AgentEntityManager;
import Geon4.company.util.BaseFrame;

import javax.swing.*;
import java.sql.SQLException;

public class AgentEditForm extends BaseFrame {
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
    private JTextField IdField;
    private JButton deleteButton;

    private AgentEntity agent;

    public AgentEditForm(AgentEntity agent) {
        setContentPane(mainPanel);
        this.agent =agent;
        initFields();
        initButtons();

        setVisible(true);



    }

    private void initFields(){
        IdField.setText(String.valueOf(agent.getId()));
        titleField.setText(String.valueOf(agent.getTitle()));
        priorityField.setText(String.valueOf(agent.getPriority()));
        agentTypeField.setText(agent.getAgentType());
        addressField.setText(agent.getAddress());
        phoneField.setText(agent.getPhone());
        emailField.setText(agent.getEmail());
        logoField.setText(agent.getLogo());
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
            if (address.length()>100 || address.isEmpty()){
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

            agent.setTitle(title);
            agent.setAgentType(agentType);
            agent.setAddress(address);
            agent.setEmail(email);
            agent.setLogo(logo);
            agent.setPhone(phone);
            agent.setPriority(priority);

            try {
                AgentEntityManager.update(agent);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Данные введены некорректно", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(this, "Клиент изменён", "Уведомление",JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new AgentTableForm();

        });
        deleteButton.addActionListener(e -> {
            if(JOptionPane.showConfirmDialog(this, "Вы точно хотите удалить эту запись?", "Предупреждение",
                    JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                try {
                    AgentEntityManager.delete(agent);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Выбранный агент уже удалён", "ошибка", JOptionPane.ERROR_MESSAGE);
                }
                JOptionPane.showMessageDialog(this, "Агент успешно удалён", "уведомление", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new AgentTableForm();
            }

        });
        backButton.addActionListener(a ->{
            new AgentTableForm();
            dispose();

        });

    }


}
