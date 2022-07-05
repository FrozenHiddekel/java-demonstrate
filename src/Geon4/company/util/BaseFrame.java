package Geon4.company.util;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {
    //ImageIcon icon = new ImageIcon("C:/Users/sever/Desktop/dec-task-2/Общие ресурсы/icon.png");
    public BaseFrame() {
        setTitle("Прыгай мой мячик");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("C:/Users/sever/Desktop/dec-task-2/Общие ресурсы/icon.png").getImage());
        setLocation(450, 300);
        setMinimumSize(new Dimension(400, 400));

    }
}
