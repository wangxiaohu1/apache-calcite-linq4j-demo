package test.model;

/**
 * @author wangxiaohu
 * @version Id: StudentView.java, v0.1 2021年12月10日 18:11:06 wangxiaohu Exp $
 */
public class StudentView {

    public String studentName;
    public String classesName;
    public int classesNo;
    public StudentView(String studentName, String classesName, int classesNo){
        this.studentName = studentName;
        this.classesName = classesName;
        this.classesNo = classesNo;
    }

    @Override
    public String toString(){
        return studentName+":"+classesName+":"+classesNo;
    }

}
