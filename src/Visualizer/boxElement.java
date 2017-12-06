package src.Visualizer;

/**
 * Created by Aaron Carl Fernandez - Mapua University
 */
public class boxElement {
    int elementNo;

    public boxElement(int element){
        elementNo = element;
    }
    
    public String getText(){
        return Integer.toString(elementNo);
    }
}
