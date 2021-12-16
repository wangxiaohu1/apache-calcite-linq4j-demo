package test.linq4jtest;

import java.util.ArrayList;
import java.util.List;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.function.Function1;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Classes;
import test.model.Student;

import static org.junit.Assert.assertEquals;

/**
 * Linq4j
 * @author wangxiaohu
 * @version Id: Test1.java, v0.1 2021年10月29日 15:04:13 wangxiaohu Exp $
 */
public class Test3_01_mapping_selectMany {

    List<Student> students = new ArrayList();

    List<Classes> classess = new ArrayList();

    @Before
    public void before(){
        students.add(new Student(1,20,"小李"));
        students.add(new Student(1,18,"小王"));
        students.add(new Student(2,32,"小张"));

        List<Student> s = new ArrayList<>();
        s.add(new Student(1,20,"daiwei"));
        s.add(new Student(1,20,"amay"));
        classess.add(new Classes("5年级",10,students));
        classess.add(new Classes("2年级",11,new ArrayList()));
        classess.add(new Classes("6年级",9,s));

    }
    /**
     * selectMany:将集合中的子集合字段提取出来
     * selectMany(Function1<TSource, Enumerable<TResult>> selector):Function1有1个参数，是元素
     */
    @Test
    public void test_selectMany_1(){
        List<String> list = Linq4j.asEnumerable(classess)
            .selectMany(c->Linq4j.asEnumerable(c.getStudents()))
            .select((stu, index) -> "#" + index + ":" + stu.getName()).toList();
        assertEquals("[#0:小李, #1:小王, #2:小张, #3:daiwei, #4:amay]", list.toString());
    }

    /**
     * selectMany:将集合中的子集合字段提取出来
     * selectMany(Function2<TSource, Integer, Enumerable<TResult>> selector):
     *  Function2有2个参数，第1个是元素，第2个是所索引
     */
    @Test
    public void test_selectMany_2(){

        List<String> list = Linq4j.asEnumerable(classess)
            .selectMany((c, index)->Linq4j.asEnumerable(c.getStudents()))
            .select((stu, index) -> "#" + index + ":" + stu.getName()).toList();
        assertEquals("[#0:小李, #1:小王, #2:小张, #3:daiwei, #4:amay]", list.toString());
    }



    /**
     * selectMany:将集合中的子集合字段提取出来
     * selectMany(Function1<TSource, Enumerable<TCollection>> collectionSelector, Function2<TSource, TCollection, TResult> resultSelector)
     *   Function1:有1个参数,是第一层集合迭代出来的元素（下面这个案例中，即classes。
     *   Function2:有2个参数,第一参数是子集合迭代出来的元素（下面这个案例中，即student)，第二个参数是父集合迭代的元素下面这个案例中，即classes)
     * 例子：
     *     index0:classes1
     *         index0:student1
     *         index2:student2
     *     index1:classes2
     *         index0:student3
     *         index2:student4
     */
    @Test
    public void test_selectMany_3(){

        List<String> list = Linq4j.asEnumerable(classess)
            .selectMany(
                x->Linq4j.asEnumerable(x.getStudents()),
                (element,subElement)->subElement.getName() + "@" + element.getName()
            )
            .toList();

        Assert.assertEquals("[小李@5年级, 小王@5年级, 小张@5年级, daiwei@6年级, amay@6年级]", list.toString());

    }

    /**
     * selectMany:将集合中的子集合字段提取出来
     * selectMany(Function2<TSource, Integer, Enumerable<TCollection>> collectionSelector,
     *       Function2<TSource, TCollection, TResult> resultSelector)
     *
     * collectionSelector: 第1个参数父集合迭代出来的元素, 第二个参数是索引index
     * resultSelector: 第1个参数子集合迭代出来的元素, 第二个负元素
     */
    @Test
    public void test_selectMany_4(){
        List<String> list = Linq4j.asEnumerable(classess)
            .selectMany(
                (classes, index) -> Linq4j.asEnumerable(classes.getStudents()),
                (element, subElement) -> subElement.getName() + "@" + element.getName()
            )
            .toList();
        Assert.assertEquals("[小李@5年级, 小王@5年级, 小张@5年级, daiwei@6年级, amay@6年级]", list.toString());
    }

}
