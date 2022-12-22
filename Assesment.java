/*
Assesment class
Desc:
Daniel Kovalevskiy
Dec. 6, 2022 
*/

class Assesment extends Event{
    private int weight;
    private String subject;
    private String topic;

    Assesment(){
        this.weight = 0;
        this.subject = "unknown";
        this.topic = "unknown";
    }

    Assesment(String name, int date, int startTime, int endTime, String location, String description, int weight, String subject, String topic){
        super(name, date, startTime, endTime, location, description);
        this.weight = weight;
        this.subject = subject;
        this.topic = topic;
    }

    //format in which assesments are printed out to the user
    @Override
    public String toString(){
        return (super.toString() + "\nWeight: " + this.weight + "\nSubject: " + this.subject + "\nTopic: " + this.topic);
    }

    //format in which assesments are printed to file
    public String toFile(){
        return (super.toFile() + ", " + this.weight + ", " + this.subject + ", " + this.topic);
    }
}