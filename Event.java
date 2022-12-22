/*
Event class
Desc:
Daniel Kovalevskiy
Dec. 6, 2022 
*/

class Event implements Comparable<Event>{
    private String name;
    private int date;
    private int startTime;
    private int endTime;
    private String location;
    private String description;

    Event(){
        this.name = "unknown";
        this.date = 0;
        this.startTime = 0;
        this.endTime = 0;
        this.location = "unknown";
        this.description = "unknown";
    }

    Event(String name, int date, int startTime, int endTime, String location, String description){
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.description = description;
    }

    //format in which events are printed out to user
    @Override
    public String toString(){
        return ("--------------------\n" + "Name: " + this.name + "\nDate: " + this.date + "\nStart: " + this.startTime + "\nEnd: " + this.endTime + "\nLocation: " + this.location + "\nDescription: " + this.description);
    }

    //format in which events are printed to file
    public String toFile(){
        return (this.name + ", " + this.date + ", " + this.startTime + ", " + this.endTime + ", " + this.location + ", " + this.description);
    }

    public String getName(){
        return this.name;
    }

    public int getDate(){
        return this.date;
    }

    public int getStartTime(){
        return this.startTime;
    }

    //compares events by date
    public int compareDate(Event other){
        if (this.date > other.getDate()){
            return 1;
        }
        else if (this.date < other.getDate()){
            return -1;
        }
        else{
            return 0;
        }
    }

    //compares events by time; this is the way elements are sorted in the proirity queues in tree nodes
    @Override
    public int compareTo(Event other){
        if (this.startTime < other.getStartTime()){
            return -1;
        }
        else if (this.startTime > other.getStartTime()){
            return 1;
        }
        else{
            return 0;
        }
    }
}