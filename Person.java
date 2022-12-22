/*
Person class
Desc:
Daniel Kovalevskiy
Dec. 6, 2022 
*/

class Person{
    private String name;
    private int age;
    private String gender;
    private Calendar calendar;

    Person(){
        this.name = "unknown";
        this.age = 0;
        this.gender = "unknown";
        this.calendar = new Calendar();
    }

    Person(String name, int age, String gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.calendar = new Calendar();
    }

    //format in which users are printed to file
    @Override
    public String toString(){
        return ("" + this.name + ", " + this.age + ", " + this.gender);
    }
    
    public Calendar getCalendar(){
        return this.calendar;
    }

    public String getName(){
        return this.name;
    }
}