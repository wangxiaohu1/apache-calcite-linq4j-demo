package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Classes;
import test.model.Student;

/**
 * @author wangxiaohu
 * @version Id: Test6_G2_join.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test7_join02_hashjoin {
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
     * hashJoin(e2, e1_selecor, e2_selecor, result_selector):
     * 将两个集合内联
     *   e1_selecor:第1个集合内联的key
     *   e2_selecor:第1个集合内联的key
     *   result_selector:内联结果处理器
     *
     * 例如这个例子是内联，没有学生的班级会被过去掉，没有班级的学生也会被过滤掉
     *  学生：studentlist
     *      Student(学生name1, 班级classNo110)
     *      Student(学生name2, 班级classNo110)
     *      Student(学生name3, 班级classNo120)
     *      Student(学生name4, 班级classNo130)
     *  班级：classeslist
     *      Classess(1年级,班级classNo110)
     *      Classess(2年级,班级classNo120)
     *      Classess(3年级,班级classNo130)
     *
     * studentlist.hashJoin(classeslist) 按照classNo进行join,结果为
     *      学生name1 - 1年级
     *      学生name2 - 1年级
     *      学生name3 - 2年级
     *      学生name4 - 3年级
     *
     */
    @Test
    public void test_hashjoin() {
        List<String> list = Linq4j.asEnumerable(students)
            .hashJoin(
                Linq4j.asEnumerable(classess),
                student ->student.getClassNo(),
                classes ->classes.getNo(),
                (student,classes) -> {
                    return"学生['" + student.getName() + "'] in 班级('" + classes.getName() + "')";
                }
            )
            .toList();

        for (String string:list) {
            System.out.println(string);
            //学生['小李'] in 班级('1年级')
            //学生['小王'] in 班级('1年级')
            //学生['大张'] in 班级('2年级')
            //学生['大刘'] in 班级('2年级')
            //学生['老夏'] in 班级('3年级')
        }

        Assert.assertEquals("[学生['小李'] in 班级('1年级'), 学生['小王'] in 班级('1年级'), 学生['大张'] in 班级('2年级'), 学生['大刘'] in 班级"
            + "('2年级'), 学生['老夏'] in 班级('3年级')]", list.toString());
    }

    /**
     * hashJoin(e2, e1_selecor, e2_selecor, result_selector, null, boolean generateNullsOnLeft, boolean generateNullsOnRight)
     * generateNullsOnLeft=false,generateNullsOnRight=true，为左联(left join)
     * generateNullsOnLeft=true,generateNullsOnRight=false，为右联(right join)
     * generateNullsOnLeft=true,generateNullsOnRight=true，为左右都联(full join)
     */
    @Test
    public void test_leftjoin(){
        List<Student> students = new ArrayList<>();
            students.add(new Student("小李",110));
            students.add(new Student("大张",120));
            students.add(new Student("老夏",130)); //没有班级的学生

        List<Classes> classess = new ArrayList<>();
            classess.add(new Classes("1年级",110));
            classess.add(new Classes("2年级",120));

        List<String> list = Linq4j.asEnumerable(students).hashJoin(
                Linq4j.asEnumerable(classess),
                student ->student.getClassNo(),
                cls ->cls.getNo(),
                (student, cls) -> {
                    String clsName = cls!=null?cls.getName():null;
                    return  "学生['" + student.getName() + "'] in 班级('" + clsName + "')";
                },
                null,
                //左侧为空的时候，是否展示右侧数据
                false,
                //右侧为空的时候，是否展示左侧数据
                true
            )
            .toList();

        for (String string:list) {
            System.out.println(string);
        }
    }

    //排列组合
    @Test
    public void test_leftjoin2(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("小李",110));
        students.add(new Student("大张",120));
        students.add(new Student("老夏",130)); //没有班级的学生

        List<Classes> classess = new ArrayList<>();
        classess.add(new Classes("1年级",110));
        classess.add(new Classes("2年级",120));

        List<Integer> list = Linq4j.asEnumerable(students)
            .<Classes, Integer, Integer>hashJoin(
                Linq4j.asEnumerable(classess),
                student -> 1,
                cls -> 1,
                (student, cls) -> 1
            )
            .toList();

        for (Integer integer:list) {
            System.out.println(integer);
        }
    }
}
