package src.Visualizer;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Aaron Carl Fernandez - Mapua University
 */
public class testView extends JFrame {

    private JButton stackPop = new JButton("Pop");


    testView(){
        JPanel stackPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);

        stackPanel.add(stackPop);

        this.add(stackPanel);
    }

    void addListener(ActionListener listenerForStackPop){

        stackPop.addActionListener(listenerForStackPop);

    }
}
