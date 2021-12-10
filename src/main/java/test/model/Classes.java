package test.model;

import java.util.List;

/**
 * @author wangxiaohu
 * @version Id: Classes.java, v0.1 2021年11月05日 22:21:49 wangxiaohu Exp $
 */
public class Classes {
    private String name;
    private int no;
    private List<Student> students;

    public Classes(String name, int no){
        this.name = name;
        this.no = no;
    }
    public Classes(String name, int no, List<Student> students) {
        this.name = name;
        this.no = no;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public int getNo() {
        return no;
    }

    public List<Student> getStudents() {
        return students;
    }
}
