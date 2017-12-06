package src.Visualizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Aaron Carl Fernandez - Mapua University
 */
public class Controller {

    private View theView;
    private Model theModel;

    public Controller(View view, Model model){
        theView = view;
        theModel = model;
    }

    class PopListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
