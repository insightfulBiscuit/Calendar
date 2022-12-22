/*
Calendar class
Desc:
Daniel Kovalevskiy
Dec. 6, 2022 
*/

import java.util.PriorityQueue;
import java.util.ArrayList;

class Calendar{
    private EventBinaryTree<PriorityQueue, Event> tree;
    private ArrayList<Event> eventList;

    Calendar(){
        this.tree = new EventBinaryTree<PriorityQueue, Event>();
        this.eventList = new ArrayList<Event>();
    }

    //adds element to tree
    public void addEvent(Event event){
        this.tree.setRoot(tree.add(tree.getRoot(), event));
    }

    //lists all element in the tree
    public ArrayList<Event> listEvents(){
        this.eventList = new ArrayList<Event>();
        this.tree.listEvents(tree.getRoot(), eventList);

        return (this.eventList);
    }

    //finds all events at given date
    public ArrayList<Event> findEvent(int date){
        this.eventList = new ArrayList<Event>();
        this.tree.findEvent(tree.getRoot(), date, eventList);

        return (this.eventList);
    }

    //finds all events by specific name
    public ArrayList<Event> findEvent(String name){
        this.eventList = new ArrayList<Event>();
        this.tree.findEvent(tree.getRoot(), name, eventList);

        return (this.eventList);
    }

    //deletes event in the tree
    public void deleteEvent(Event item){
        this.eventList = new ArrayList<Event>();
        this.tree.listEvents(tree.getRoot(), eventList);
        this.tree = new EventBinaryTree<PriorityQueue, Event>();
        for (Event element: eventList){
            if (!element.equals(item)){
                this.tree.setRoot(tree.add(tree.getRoot(), element));
            }
        }
    }

    //return tree of events
    public EventBinaryTree getTree(){
        return this.tree;
    }
}