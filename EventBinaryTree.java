/*
EventBinaryTree class
Desc:
Daniel Kovalevskiy
Dec. 6, 2022 
*/

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Stack;

public class EventBinaryTree<T, E extends Event>{
    private Node<T> root;
    
    EventBinaryTree(){
        this.root = null;
    }

    //Adds element to tree using recursion
    public Node add(Node<T> root, E item){
        //base case
        if (root == null){
            root = new Node(new PriorityQueue<E>(), item);
            return root;
        }
        else{
            //places element into the tree, sorted by date
            int comp = root.getEvent().compareDate(item);
            if (comp == 0){
                root.addToQueue(item);
                return root;
            }
            else if (comp > 0){
                root.setLeft(add(root.getLeft(), item));
            }
            else{
                root.setRight(add(root.getRight(), item));
            }
            return root;
        }
    }

    //finds element at specific branch (date) of the tree using recursion
    public void findEvent(Node<T> root, int date, ArrayList<E> eventList){
        //list all elements in each node
        if (root != null){
            for (E element: root.getQueue()){
                if (element.getDate() == (date)){
                    eventList.add(element);
                }
            }

            //go into next node
            if (root.getRight() != null){
                findEvent(root.getRight(), date, eventList);
            }
            if (root.getLeft() != null){
                findEvent(root.getLeft(), date, eventList);
            }
        }
    }

    //finds elements that contain the name of event using recursion
    public void findEvent(Node<T> root, String name, ArrayList<E> eventList){
        //base case
        if (root == null){
            return;
        }

        //finds all events that contain the name in a sorted fashion (date and time)
        findEvent(root.getLeft(), name, eventList);
        for (E element: root.getQueue()){
            if (element.getName().contains(name)){
                eventList.add(element);
            }
        }
        findEvent(root.getRight(), name, eventList); 
    }

    //finds all element within the tree using recursion
    public void listEvents(Node<T> root, ArrayList<E> eventList){
        //base case
        if (root == null){
            return;
        }

        //finds all elements in a tree in a sorted fashion (date and time)
        listEvents(root.getLeft(), eventList);
        for (E element: root.getQueue()){
            eventList.add(element);
        }
        listEvents(root.getRight(), eventList);
    }

    public Node<T> getRoot(){
        return this.root;
    }

    public void setRoot(Node<T> item){
        this.root = item;
    }

    private class Node<T>{
        private PriorityQueue<E> queue;
        private Node<T> left;
        private Node<T> right;
        
        public Node(PriorityQueue<E> queue, E event){
            this.queue = queue;
            this.queue.add(event);
            this.left = null;
            this.right = null;
        } 
        public E getEvent(){
            return this.queue.peek();
        }
        public void addToQueue(E event){
            this.queue.add(event);
        }
        public PriorityQueue<E> getQueue(){
            return this.queue;
        }
        public void setQueue(PriorityQueue<E> queue){
            this.queue = queue;
        }
        public Node<T> getLeft(){
            return this.left;
        }        
        public void setLeft(Node<T> node){
            this.left = node;
        }
        public Node<T> getRight(){
            return this.right;
        } 
        public void setRight(Node<T> node){
            this.right = node;
        }   
    }
}