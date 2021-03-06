package test.linq4jtest;

import java.util.ArrayList;
import java.util.List;

import org.apache.calcite.linq4j.JoinType;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Classes;
import test.model.Student;
import test.model.StudentView;

/**
 * join 处理 https://zhuanlan.zhihu.com/p/67725127
 * @author wangxiaohu
 * @version Id: Test6_G2_join.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test6_join_correlateJoin {
    List<Student> students = new ArrayList();
    List<Classes> classess = new ArrayList();

    @Before
    public void before(){
        students.add(new Student("小李",110));
        students.add(new Student("小王",110));

        students.add(new Student("大张",120));
        students.add(new Student("大刘",120));

        students.add(new Student("老夏",130));

        students.add(new Student("老陈",140));

        classess.add(new Classes("1年级",110));
        classess.add(new Classes("2年级",120));
        classess.add(new Classes("3年级",130));
    }

    /**
     * correlateJoin(
     *       JoinType joinType, Function1<TSource, Enumerable<TInner>> inner,
     *       Function2<TSource, TInner, TResult> resultSelector) : join查询
     * joinType: 链接类型，correlateJoin 不支持 FULL、RIGHT
     * inner: 联接条件处理器
     * resultSelector : 结果处理器
     *
     * 下面这个案例是相当于sql  :
     *   select s.name, c.name, c.no from studentList s left join on classesList c
     *      on s.classNo=c.no
     */
    @Test
    public void test_leftJoin(){
        List<StudentView> list1 = Linq4j.asEnumerable(students)
            .correlateJoin(
                //联接类型 JoinType.LEFT 代表 left join
                JoinType.LEFT,
                //联接条件处理器，相当于on
                student -> {
                    return Linq4j.asEnumerable(classess).where(y->y.getNo()==student.getClassNo());
                },
                //结果处理器
                (student,classes)-> {
                    if(classes!=null){
                        return new StudentView(student.getName(), classes.getName(), classes.getNo());
                    }else{
                        return new StudentView(student.getName(), null, 0);
                    }
                }
            ).toList();

        list1.stream().forEach(x-> System.out.println(x));
        //小李:1年级:110
        //小王:1年级:110
        //大张:2年级:120
        //大刘:2年级:120
        //老夏:3年级:130
        //老陈:null:0
    }

    /**
     * correlateJoin: join查询
     *   JoinType.INNER : 相当于INNER JOIN
     * 下面这个案例是相当于sql  :
     *   select s.name, c.name, c.no from studentList s inner join on classesList c
     *   on s.classNo=c.no
     */
    @Test
    public void test_innerJoin(){
        List<StudentView> list1 = Linq4j.asEnumerable(students)
            .correlateJoin(
                //联接类型 JoinType.INNER 代表 inner join
                JoinType.INNER,
                //联接条件处理器，相当于on
                student -> {
                    return Linq4j.asEnumerable(classess).where(y->y.getNo()==student.getClassNo());
                },
                //结果处理器
                (student,classes)-> {
                    return new StudentView(student.getName(), classes.getName(), classes.getNo());
                }
            ).toList();

        list1.stream().forEach(x-> System.out.println(x));
        //小李:1年级:110
        //小王:1年级:110
        //大张:2年级:120
        //大刘:2年级:120
        //老夏:3年级:130
    }

    /**
     * correlateJoin: join查询
     *    JoinType.ANTI : 相当于 NOT EXISTS
     *
     * 下面这个案例是相当于sql ：
     *    SELECT * FROM EMP
     *    WHERE NOT EXISTS (SELECT 1 FROM DEPT
     *     WHERE DEPT.DEPTNO = EMP.DEPTNO)
     */
    @Test
    public void test_notExists(){
        List<StudentView> list1 = Linq4j.asEnumerable(students)
            .correlateJoin(
                //联接类型 JoinType.ANTI 代表 NOT EXISTS
                JoinType.ANTI,
                //联接条件处理器，相当于on
                student -> {
                    return Linq4j.asEnumerable(classess).where(y->y.getNo()==student.getClassNo());
                },
                //结果处理器
                (student,classes)-> {
                    if(classes!=null){
                        return new StudentView(student.getName(), classes.getName(), classes.getNo());
                    }else{
                        return new StudentView(student.getName(), null, 0);
                    }
                }
            ).toList();

        list1.stream().forEach(x-> System.out.println(x));

        //老陈:null:0
    }


    /**
     * correlateJoin: join查询
     *    JoinType.SEMI : 相当于 EXISTS
     *
     * 下面这个案例是相当于sql ：
     *    SELECT * FROM EMP
     *    WHERE EXISTS (SELECT 1 FROM DEPT
     *      WHERE DEPT.DEPTNO = EMP.DEPTNO)
     */
    @Test
    public void test_exists(){
        List<StudentView> list1 = Linq4j.asEnumerable(students)
            .correlateJoin(
                //联接类型 JoinType.ANTI 代表 NOT EXISTS
                JoinType.SEMI,
                //联接条件处理器，相当于on
                student -> {
                    return Linq4j.asEnumerable(classess).where(y->y.getNo()==student.getClassNo());
                },
                //结果处理器
                (student,classes)-> {
                    if(classes!=null){
                        return new StudentView(student.getName(), classes.getName(), classes.getNo());
                    }else{
                        return new StudentView(student.getName(), null, 0);
                    }
                }
            ).toList();

        list1.stream().forEach(x-> System.out.println(x));

        //小李:null:0
        //小王:null:0
        //大张:null:0
        //大刘:null:0
        //老夏:null:0
    }
}
