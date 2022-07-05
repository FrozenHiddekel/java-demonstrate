package Geon4.company.ui;

import Geon4.company.entity.AgentEntity;
import Geon4.company.manager.AgentEntityManager;
import Geon4.company.util.BaseFrame;
import Geon4.company.util.CustomTable;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class AgentTableForm extends BaseFrame {
    private JPanel mainPanel;
    private JTable table;
    private JButton createButton;
    private CustomTable<AgentEntity> model;

    public AgentTableForm() {
        setContentPane(mainPanel);
        initTable();
        initButtons();
        setVisible(true);
    }

private void initTable(){

        table.getTableHeader().setReorderingAllowed(false);

    try {
        model = new CustomTable<>(
                new String[]{"ID", "Название", "Тип агента", "Адпресс", "Телефон", "Почта", "Путь до картинки", "Приоритет"},
                AgentEntity.class,
                AgentEntityManager.selectAll());
    } catch (SQLException e) {
        e.printStackTrace();
    }
table.setModel(model);


    table.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() ==2){
                int row = table.rowAtPoint(e.getPoint());
                if (row != -1){
                    new AgentEditForm((AgentEntity) model.getRows().get(row));
                    dispose();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    });

}

private void initButtons(){
        createButton.addActionListener(e -> {
            dispose();
            new AgentCreateForm();
        });
}

}
