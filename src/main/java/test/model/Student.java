package test.model;


/**
 * @author wangxiaohu
 * @version Id: Student.java, v0.1 2021年11月04日 20:01:17 wangxiaohu Exp $
 */
public class Student{
    // 1:男；2:女
    private int sex;

    private int age;

    private String name;

    private int classNo;


    public Student(String name, int classNo){
        this.name = name;
        this.classNo = classNo;
    }
    public Student(int sex, int age, String name) {
        this.sex = sex;
        this.age = age;
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getClassNo() {
        return classNo;
    }

    public void setAge(int age){
        this.age = age;
    }

    @Override
    public String toString(){
        return "Student(name:"+name+",age:"+age+",sex:"+sex+",classNo:"+classNo+")";
    }
}
