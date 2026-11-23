package StudentManagement;

public class Student {
    private int rollNum;
    private String name;
    private int marks;

    public Student(int rollNum, String name, int marks) {
        this.rollNum = rollNum;
        this.name = name;
        this.marks = marks;
    }

    public int getRollNum() {
        return rollNum;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }
}


