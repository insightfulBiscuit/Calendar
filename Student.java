/*
Student class
Desc:
Daniel Kovalevskiy
Dec. 6, 2022 
*/

class Student extends Person{
    private String school;
    private int studentNumber;

    Student(){
        this.school = "unknown";
        this.studentNumber = 0;
    }
    
    Student(String name, int age, String gender, String school, int studentNumber){
        super(name, age, gender);
        this.school = school;
        this.studentNumber = studentNumber;
    }

    //format in which users are printed to file
    @Override
    public String toString(){
        return (super.toString() + ", " + this.school + ", " + this.studentNumber);
    }
}