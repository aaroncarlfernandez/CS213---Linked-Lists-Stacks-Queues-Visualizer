package src.Visualizer;

/**
 * Created by Aaron Carl Fernandez - Mapua University.
 * Submitted to Mr. Elcid Serrano
 * In partial fulfillment of the requirements for CS213 - Advance Data * Structures and Algorithms
 */
public class Visualizer {

    public static void main(String[] args) {

        Model theModel = new Model();
        View theView = new View(theModel);

        src.Visualizer.Controller theController = new src.Visualizer.Controller(theView, theModel);

        theView.setVisible(true);


    }

}
