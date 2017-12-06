package src.Visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Queue;

/**
 * Created by Aaron Carl Fernandez - Mapua University
 */
public class View extends JFrame {
    private JTabbedPane tabbedPanel;
    private JPanel rootPanel;
    private JButton addButton;
    private JButton pollButton;
    private JButton peekButton1;
    private JButton offerButton;
    private JButton removeButton;
    private JButton elementButton;
    private JButton pushButton;
    private JButton popButton;
    private JButton peekButton;
    private JButton emptyButton;
    private JButton searchButton;
    private JPanel queueDisplay;
    private JPanel stackDisplay;
    private JLabel pushLabel;
    private JLabel popLabel;
    private JLabel peekLabel;
    private JLabel emptyLabel;
    private JLabel searchLabel;
    private JPanel queuePanel;
    private JLabel addLabel;
    private JLabel pollLabel;
    private JLabel peekQueueLabel;
    private JLabel offerLabel;
    private JLabel removeLabel;
    private JLabel elementLabel;
    private JPanel stackPanel;
    private JList previousOperations;
    private JList previousOperationsQueue;
    private JPanel llPanel;
    private JList previousOperationsLL;
    private JPanel llDisplay;
    private JButton addFirstButtonLL;
    private JButton peekButtonLL;
    private JButton searchButtonLL;
    private JButton removeHeadButtonLL;
    private JButton addLastButtonLL;
    private JButton removeLastButtonLL;
    private JButton sizeButtonLL;
    private JButton clearButtonLL;
    private JPanel aboutPanel;
    private JLabel aboutLabel;
    private JLabel creatorLabel;
    private JLabel profLabel;
    private JLabel courseLabel;
    private JButton Add;
    private LinkedList<String> operationsList = new LinkedList<>();
    private LinkedList<String> operationsListQueue = new LinkedList<>();
    private LinkedList<String> operationsListLL = new LinkedList<>();

    View(Model model) {
        super("CS213 Advance Data Structures and Algortihms");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.add(tabbedPanel);

        DrawLLStuff drawLLStuff = new DrawLLStuff(model);
        llDisplay.add(drawLLStuff);

        DrawStuff drawStuff = new DrawStuff(model);
        stackDisplay.add(drawStuff);

        DrawQueueStuff drawQueueStuff = new DrawQueueStuff(model);
        queueDisplay.add(drawQueueStuff);

        /*
        Start of the Stack button action listeners
         */
        pushButton.addActionListener(new ActionListener() { //anonymous inner class
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getStack().size() == 10) {
                    JOptionPane.showMessageDialog(null, "Sorry the Stack is full and nothing can be pushed", "Stack Full", JOptionPane.ERROR_MESSAGE);
                } else {
                    while (true) {
                        try {
                            int toBePushed = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number you want to push on to the Stack", "Push", JOptionPane.DEFAULT_OPTION));
                            addOperation(toBePushed + " is pushed at the top of the Stack");
                            model.push(toBePushed);
                            stackDisplay.updateUI();
                            break;
                        } catch (NumberFormatException exception) {
                            if (exception.getMessage().equals("null")) {
                                break;
                            }
                            JOptionPane.showMessageDialog(null, "Sorry that was not an integer, please try again", "Not an Integer", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        popButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int popped = model.pop();
                    addOperation(popped + " is popped from the top of the Stack");
                    stackDisplay.updateUI();
                } catch (EmptyStackException exception) {
                    addOperation("Cannot Pop - Stack Empty");
                    JOptionPane.showMessageDialog(null, "Sorry the Stack is empty and nothing can be popped", "Stack Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        peekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int peeked = model.peek();
                    addOperation(peeked + " is peeked");
                    drawStuff.highlight(peeked); //highlight thing
                    stackDisplay.updateUI();
                } catch (EmptyStackException exception) {
                    addOperation("Cannot Peek - Stack Empty");
                    JOptionPane.showMessageDialog(null, "Sorry the Stack is empty and nothing can be peeked", "Stack Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        emptyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOperation("Is Stack Empty?: " + model.empty());
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (true) {
                    try {
                        int toBeSearched = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number you want to search for in the Stack", "Search", JOptionPane.DEFAULT_OPTION));
                        int searched = model.search(toBeSearched);
                        if (searched > -1) {
                            addOperation(toBeSearched + " is found at index " + searched + " of the Stack");
                            drawStuff.highlight(toBeSearched);
                        } else {
                            addOperation(toBeSearched + " is not in the Stack");
                        }
                        stackDisplay.updateUI();
                        break;
                    } catch (NumberFormatException exception) {
                        if (exception.getMessage().equals("null")) {
                            break;
                        }
                        JOptionPane.showMessageDialog(null, "Sorry that was not an integer, please try again", "Not an Integer", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

         /*
        Start of the Queue button action listeners
         */
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getQueue().size() == 15) {
                    JOptionPane.showMessageDialog(null, "Sorry the Queue is full and nothing can be added", "Queue Full", JOptionPane.ERROR_MESSAGE);
                } else {
                    while (true) {
                        try {
                            int toBeQueued = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number you want to add to the Queue", "Add", JOptionPane.DEFAULT_OPTION));
                            addQueueOperation(toBeQueued + " is enqueued into the Queue");
                            model.add(toBeQueued);
                            queueDisplay.updateUI();
                            break;
                        } catch (NumberFormatException exception) {
                            if (exception.getMessage().equals("null")) {
                                break;
                            }
                            JOptionPane.showMessageDialog(null, "Sorry that was not an integer, please try again", "Not an Integer", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        pollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int polled = model.poll();
                    addQueueOperation(polled + " is dequeued from the Queue" );
                    queueDisplay.updateUI();
                } catch (NullPointerException exception) {
                    addQueueOperation("Cannot Poll - Queue Empty");
                    JOptionPane.showMessageDialog(null, "Sorry the Queue is empty and nothing can be polled", "Queue Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        peekButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int peeked = model.peekQueue();
                    addQueueOperation(peeked + " is peeked" );
                    drawQueueStuff.highlight(peeked);
                    queueDisplay.updateUI();
                } catch (NullPointerException exception) {
                    addQueueOperation("Cannot Peek - Queue Empty");
                    JOptionPane.showMessageDialog(null, "Sorry the Queue is empty and nothing can be peeked", "Queue Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        offerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getQueue().size() == 15) {
                    JOptionPane.showMessageDialog(null, "Sorry the Queue is full and nothing can be offered", "Queue Full", JOptionPane.ERROR_MESSAGE);
                } else {
                    while (true) {
                        try {
                            int toBeOffered = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number you want to add to the Queue", "Offer", JOptionPane.DEFAULT_OPTION));
                            addQueueOperation(toBeOffered + " is offered at the end of the Queue");
                            model.add(toBeOffered);
                            queueDisplay.updateUI();
                            break;
                        } catch (NumberFormatException exception) {
                            if (exception.getMessage().equals("null")) {
                                break;
                            }
                            JOptionPane.showMessageDialog(null, "Sorry that was not an integer, please try again", "Not an Integer", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int removed = model.remove();
                    addQueueOperation(removed + " is removed from the start of the Queue" );
                    queueDisplay.updateUI();
                } catch (NoSuchElementException exception) {
                    addQueueOperation("Cannot Remove - Queue Empty");
                    JOptionPane.showMessageDialog(null, "Sorry the Queue is empty and nothing can be removed", "Queue Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        elementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int element = model.element();
                    addQueueOperation(element + " is an element at the start of the Queue");
                    drawQueueStuff.highlight(element);
                    queueDisplay.updateUI();
                } catch (NoSuchElementException exception) {
                    addQueueOperation("Cannot Element - Queue Empty");
                    JOptionPane.showMessageDialog(null, "Sorry 'Element' cannot be performed because the Queue is empty", "Queue Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /*
        Start of the LL button action listeners
        */
        addFirstButtonLL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getll().size() == 15) {
                    JOptionPane.showMessageDialog(null, "Sorry the Linked list is full and nothing can be added", "Linked list Full", JOptionPane.ERROR_MESSAGE);
                } else {
                    while (true) {
                        try {
                            int toBeQueued = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number you want to add into the Linked list", "Add a Node", JOptionPane.DEFAULT_OPTION));
                            addLLOperation(toBeQueued + " is added at the beginning");
                            model.addFirstLL(toBeQueued);
                            llDisplay.updateUI();
                            break;
                        } catch (NumberFormatException exception) {
                            if (exception.getMessage().equals("null")) {
                                break;
                            }
                            JOptionPane.showMessageDialog(null, "Sorry that was not an integer, please try again", "Not an Integer", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        addLastButtonLL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getll().size() == 15) {
                    JOptionPane.showMessageDialog(null, "Sorry the Linked list is full and nothing can be added", "Linked list Full", JOptionPane.ERROR_MESSAGE);
                } else {
                    while (true) {
                        try {
                            int toBeQueued = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number you want to add into the Linked list", "Add a Node", JOptionPane.DEFAULT_OPTION));
                            addLLOperation(toBeQueued + " is added at the end");
                            model.addLastLL(toBeQueued);
                            llDisplay.updateUI();
                            break;
                        } catch (NumberFormatException exception) {
                            if (exception.getMessage().equals("null")) {
                                break;
                            }
                            JOptionPane.showMessageDialog(null, "Sorry that was not an integer, please try again", "Not an Integer", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        removeHeadButtonLL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    model.removeFirstLL();
                    addLLOperation("Head node removed");
                    llDisplay.updateUI();
                } catch (NullPointerException exception) {
                    addLLOperation("Cannot remove head node - Linked list Empty");
                    JOptionPane.showMessageDialog(null, "Sorry the Linked list is empty", "Linked list Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeLastButtonLL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    model.removeLastLL();
                    addLLOperation("Tail node removed: ");
                    llDisplay.updateUI();
                } catch (NullPointerException exception) {
                    addLLOperation("Cannot remove tail node - Linked list Empty");
                    JOptionPane.showMessageDialog(null, "Sorry the Linked list is empty", "Linked list Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        clearButtonLL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    model.clearLL();
                    addLLOperation("Linked list is destroyed ");
                    llDisplay.updateUI();
                } catch (NullPointerException exception) {
                    addLLOperation("Clear failed - Linked list Empty");
                    JOptionPane.showMessageDialog(null, "Linked list is already empty", "Linked list Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        peekButtonLL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int peeked = model.peekLL();
                    addLLOperation(peeked + " is peeked" );
                    drawLLStuff.highlight(peeked);
                    llDisplay.updateUI();
                } catch (NullPointerException exception) {
                    addLLOperation("Cannot Peek - Linked list is Empty");
                    JOptionPane.showMessageDialog(null, "Sorry the Linked list is empty and cannot be peeked", "Linked list Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        searchButtonLL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (true) {
                    try {
                        int toBeSearched = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number you want to Search for in the Linked list", "Search", JOptionPane.DEFAULT_OPTION));
                        int searched = model.searchLL(toBeSearched);
                        if (searched > -1) {
                            addLLOperation(toBeSearched + " is found at index " + searched + " of the Linked list");
                            drawLLStuff.highlight(toBeSearched);
                        } else {
                            addLLOperation(toBeSearched + " is not in the Linked list");
                        }
                        llDisplay.updateUI();
                        break;
                    } catch (NumberFormatException exception) {
                        if (exception.getMessage().equals("null")) {
                            break;
                        }
                        JOptionPane.showMessageDialog(null, "Sorry that was not an integer, please try again", "Not an Integer", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        sizeButtonLL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int llSize = model.sizeLL();
                    addLLOperation("Linked list size is " + llSize);
                    llDisplay.updateUI();
                } catch (NullPointerException exception) {
                    addLLOperation("Cannot remove tail node - Linked list Empty");
                    JOptionPane.showMessageDialog(null, "Sorry the Linked list is empty", "Linked list Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Add operations to Stack list.
    public void addOperation(String op) {
        if (operationsList.size() < 25) { //Max size of stack operations list
            operationsList.push(op);
        } else {
            operationsList.removeLast();
            operationsList.push(op);
        }
        String listData[] = operationsList.toArray(new String[operationsList.size()]);
        previousOperations.setListData(listData);
    }

    // Add operations to the queue list
    public void addQueueOperation(String op) {
        if (operationsListQueue.size() < 25) { //Max size of queue operations list
            operationsListQueue.push(op);
        } else {
            operationsListQueue.removeLast();
            operationsListQueue.push(op);
        }
        String listData[] = operationsListQueue.toArray(new String[operationsListQueue.size()]);
        previousOperationsQueue.setListData(listData);
    }

    // Add operations to the Linked list
    public void addLLOperation(String op) {
        if (operationsListLL.size() < 25) { //Max size of queue operations list
            operationsListLL.push(op);
        } else {
            operationsListLL.removeLast();
            operationsListLL.push(op);
        }
        String listData[] = operationsListLL.toArray(new String[operationsListLL.size()]);
        previousOperationsLL.setListData(listData);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


    private class DrawStuff extends JComponent {
        Model model;
        int toBeHighlighted = -1;

        public DrawStuff(Model model) {
            this.model = model;
        }

        public void paint(Graphics g) {

            Graphics2D graph2 = (Graphics2D) g;
            graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Stack<Integer> s = model.getStack();
            Stack<Integer> stack = new Stack<>();
            stack.addAll(s);
            ArrayList<src.Visualizer.boxElement> stackRepresentation = new ArrayList<>();
            FontMetrics fm = graph2.getFontMetrics();
            int textx;

            for (int i = stack.size(); i > 0; i--) {
                stackRepresentation.add(new src.Visualizer.boxElement(stack.pop()));
            }

            //Draws the boxes with their numbers within them.
            int y = 30;
            int x = ((this.getWidth() / 2) - 20);
            for (src.Visualizer.boxElement b : stackRepresentation) {
                //get correct spacing for boxes
                if (b.getText().length() == 1) {
                    textx = (x + 12);
                } else if (b.getText().length() == 2) {
                    textx = (x + 8);
                } else {
                    textx = (x + 5);
                }

                //highlight searched thing
                if (b.elementNo == toBeHighlighted) {
                    graph2.setColor(new Color(204, 255, 0));
                    graph2.fillRect(x, y, 30, 30);
                    graph2.setColor(Color.black);
                    toBeHighlighted = -1;
                }

                graph2.draw(new Rectangle(x, y, 30, 30));
                graph2.drawString(b.getText(), textx, (y + 20));
                y = y + 45;
            }

            /*
            This code draws the lines in between the boxes
             */
            y = 75;
            for (int i = 1; i < stackRepresentation.size(); i++) {
                graph2.drawLine((x + 15), (y), (x + 15), (y - 15));
                y += 45;
            }

            //This add top and bottom to the graphics, showing the stack more clearly.
            if (stackRepresentation.size() > 0) {
                graph2.drawString("Top", (x - 40), 50);
            }
            if (stackRepresentation.size() > 1) {
                graph2.drawString("Bottom", (x - 55), ((45 * stackRepresentation.size()) + 5));
            }

        }

        public void highlight(int x) {
            toBeHighlighted = x;
        }

    }

    private class DrawQueueStuff extends Component {
        Model model;
        int toBeHighlighted = -1;

        public DrawQueueStuff(Model model) {
            this.model = model;
        }

        public void paint(Graphics g) {

            Graphics2D graph2 = (Graphics2D) g;
            graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Queue<Integer> queue = model.getQueue();
            ArrayList<src.Visualizer.boxElement> queueRepresentation = new ArrayList<>();
            FontMetrics fm = graph2.getFontMetrics();
            int textx;

            for (Integer x : queue) {
                queueRepresentation.add(new src.Visualizer.boxElement(x));
            }

            //Draws the boxes with their numbers within them.
            int y = ((this.getHeight() / 2) - 20);
            int x = 30;
            for (src.Visualizer.boxElement b : queueRepresentation) {
                //get correct spacing for boxes
                if (b.getText().length() == 1) {
                    textx = (x + 12);
                } else if (b.getText().length() == 2) {
                    textx = (x + 8);
                } else {
                    textx = (x + 5);
                }

                //highlight searched thing
                if (b.elementNo == toBeHighlighted) {
                    graph2.setColor(new Color(204, 255, 0));
                    graph2.fillRect(x, y, 30, 30);
                    graph2.setColor(Color.black);
                    toBeHighlighted = -1;
                }

                graph2.draw(new Rectangle(x, y, 30, 30));
                graph2.drawString(b.getText(), textx, (y + 20));
                x += 45;
            }

            /*
            This code draws the lines in between the boxes
             */
            x = 75;
            for (int i = 1; i < queueRepresentation.size(); i++) {
                graph2.drawLine((x), (y + 15), (x - 15), (y + 15));
                x += 45;
            }

            //This add head and tail to the graphics, showing the queue more clearly.
            if (queueRepresentation.size() > 0) {
                graph2.drawString("Head", 30, (y - 25));
            }
            if (queueRepresentation.size() > 1) {
                graph2.drawString("Tail", ((45 * queueRepresentation.size()) - 10), (y - 25));
            }

        }

        public void highlight(int x) {
            toBeHighlighted = x;
        }
    }

    //acf:
    private class DrawLLStuff extends Component {
        Model model;
        int toBeHighlighted = -1;

        public DrawLLStuff(Model model) {
            this.model = model;
        }

        public void paint(Graphics g) {

            Graphics2D graph2 = (Graphics2D) g;
            graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Queue<Integer> ll = model.getll();
            ArrayList<src.Visualizer.boxElement> llRepresentation = new ArrayList<>();
            FontMetrics fm = graph2.getFontMetrics();
            int textx;

            for (Integer x : ll) {
                llRepresentation.add(new src.Visualizer.boxElement(x));
            }

            //Draws the boxes with their numbers within them.
            int y = ((this.getHeight() / 2) - 20);
            int x = 30;
            for (src.Visualizer.boxElement b : llRepresentation) {
                //get correct spacing for boxes
                if (b.getText().length() == 1) {
                    textx = (x + 12);
                } else if (b.getText().length() == 2) {
                    textx = (x + 8);
                } else {
                    textx = (x + 5);
                }

                //highlight searched thing
                if (b.elementNo == toBeHighlighted) {
                    graph2.setColor(new Color(204, 255, 0));
                    graph2.fillRect(x, y, 30, 30);
                    graph2.setColor(Color.black);
                    toBeHighlighted = -1;
                }

                graph2.draw(new Rectangle(x, y, 30, 30));
                graph2.drawString(b.getText(), textx, (y + 20));
                x += 45;
            }

            /*
            This code draws the lines in between the boxes
             */
            x = 75;
            for (int i = 1; i < llRepresentation.size(); i++) {
                graph2.drawLine((x), (y + 15), (x - 15), (y + 15));
                x += 45;
            }

            //This add head and tail to the graphics, showing the queue more clearly.
            if (llRepresentation.size() > 0) {
                graph2.drawString("Head", 30, (y - 25));
            }
            if (llRepresentation.size() > 1) {
                graph2.drawString("Tail", ((45 * llRepresentation.size()) - 10), (y - 25));
            }

        }

        public void highlight(int x) {
            toBeHighlighted = x;
        }

    }

}
