/*
Extracurricular class
Desc:
Daniel Kovalevskiy
Dec. 6, 2022 
*/

class Extracurricular extends Event{
    private String organization;

    Extracurricular(){
        this.organization = "unknown";
    }

    Extracurricular(String name, int date, int startTime, int endTime,String location, String description, String organization){
        super(name, date, startTime, endTime, location, description);
        this.organization = organization;
    }

    //format in which extracurriculars are printed to user
    @Override
    public String toString(){
        return (super.toString() + "\nOrganization: " + this.organization);
    }

    //format in which extracurriculars are printed to file
    public String toFile(){
        return (super.toFile() + ", " + this.organization);
    }
}