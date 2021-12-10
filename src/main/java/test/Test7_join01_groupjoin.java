package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.function.EqualityComparer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Classes;
import test.model.Student;

/**
 * @author wangxiaohu
 * @version Id: Test6_G1_groupjoin.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test7_join01_groupjoin {
    List<Student> students = new ArrayList();
    List<Classes> classess = new ArrayList();

    @Before
    public void before(){
        Student student = new Student("小李",110);

        students.add(new Student("小李",110));
        students.add(new Student("小王",110));

        students.add(new Student("大张",120));
        students.add(new Student("大刘",120));

        students.add(new Student("老夏",130));

        classess.add(new Classes("1年级",110));
        classess.add(new Classes("2年级",120));
        classess.add(new Classes("3年级",130));

    }

    /**
     * groupjoin(e2, e1_selecor, e2_selecor, result_selector):
     * 将两个集合内联并且分组
     *   e1_selecor:第1个集合内联的key
     *   e2_selecor:第1个集合内联的key
     *   result_selector:内联结果处理器
     *
     * 例如这个例子，将学生按照班级分组，没有学生的班级会被过去掉，没有班级的学生也会被过滤掉
     *  班级：classeslist
     *      Classess(1年级,班级classNo110)
     *      Classess(2年级,班级classNo120)
     *      Classess(3年级,班级classNo130)
     *  学生：studentlist
     *      Student(学生name1, 班级classNo110)
     *      Student(学生name2, 班级classNo110)
     *      Student(学生name3, 班级classNo120)
     *      Student(学生name4, 班级classNo130)
     *
     * classeslist.groupJoin(studentlist) 按照classNo进行分组链接,结果为
     *      1年级
     *          学生name1
     *          学生name2
     *      2年级
     *          学生name3
     *      3年级
     *          学生name4
     *
     */
    @Test
    public void test_groupjoin() {
        List<String> strlist = Linq4j.asEnumerable(classess)
            .groupJoin(
                Linq4j.asEnumerable(students),
                //内联key
                classes ->classes.getNo(),
                //内联key
                student ->student.getClassNo(),
                //内联分组结果处理
                (classes, studentList) -> {
                    StringBuffer sb = new StringBuffer("学生['");
                    for(Student studnet : studentList) {
                        sb.append(studnet.getName()).append(",");
                    }
                    return sb.append("'] in 班级('").append(classes.getName()).append("')").toString();
                }
            )
            .toList();

        for (String str:strlist) {
            System.out.println(str);
            //学生['小李,小王,'] in 班级('1年级')
            //学生['大张,大刘,'] in 班级('2年级')
            //学生['老夏,'] in 班级('3年级')
        }

        Assert.assertEquals("[学生['小李,小王,'] in 班级('1年级'), 学生['大张,大刘,'] in 班级('2年级'), 学生['老夏,'] in 班级('3年级')]", strlist.toString());

    }
}
