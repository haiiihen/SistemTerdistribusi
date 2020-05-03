package data;

import java.io.Serializable;

public class Employee implements Serializable {
    private String name;
    private int age;
    private float salary;
    public Employee(String name, int age, float salary){
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    public void print(){
        System.out.println(("Record untuk :" + this.name));
        System.out.println("Nama :" + this.name);
        System.out.println("Age :" + this.age);
        System.out.println("Salary : " + this.salary);
    }

}