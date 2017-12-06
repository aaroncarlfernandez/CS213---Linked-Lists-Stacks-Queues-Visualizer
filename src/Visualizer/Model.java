package src.Visualizer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Aaron Carl Fernandez - Mapua University
 */

public class Model {

    private LinkedList<Integer> ll = new LinkedList<>(); //acf
    private Stack<Integer> stack = new Stack<>();
    private Queue<Integer> queue = new LinkedList<>();

    //Stack Operations

    public void push(int x){
        stack.push(x);
    }

    public int pop(){
        return stack.pop();
    }

    public int peek(){
        return stack.peek();
    }

    public boolean empty(){
        return stack.empty();
    }

    public int search(int x){
        return stack.search(x);
    }

    public Stack<Integer> getStack(){
        return  stack;
    }

    //Queue Operations

    public void add(int x) {
        queue.add(x);
    }

    public int poll(){
        return queue.poll();
    }

    public int peekQueue(){
        return queue.peek();
    }

    public void offer(int x){
        queue.offer(x);
    }

    public int remove(){
        return queue.remove();
    }

    public int element(){
        return queue.element();
    }

    public Queue<Integer> getQueue(){
        return queue;
    }

    //Linked List Operations

    public void addFirstLL(int x) {
        ll.addFirst(x);
    }

    public void addLastLL(int x) { ll.addLast(x);}

    public void removeFirstLL() { ll.removeFirst();}

    public void removeLastLL() { ll.removeLast();}

    public void clearLL() {ll.clear();}

    public int peekLL(){
        return ll.peek();
    }

    public int searchLL(int x){
        return ll.indexOf(x);
    }

    public int sizeLL() { return ll.size();}

    public LinkedList<Integer> getll(){
        return ll;
    }

}
