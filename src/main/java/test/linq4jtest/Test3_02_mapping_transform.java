package test.linq4jtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Test;
import test.model.Classes;
import test.model.Student;
import test.model.StudentView;

/**
 * @author wangxiaohu
 * @version Id: Test7_transform.java, v0.1 2021年12月06日 16:49:36 wangxiaohu Exp $
 */
public class Test3_02_mapping_transform {

    /**
     * transform: 从集合中将对象元素中的字段提取出来, 转换元素，和select方法一样
     *  常用场景:集合元素小写转大写
     */
    @Test
    public void test_transform1(){
        List<String> strings = Arrays.asList("a", "b", "c");
        Enumerator<String> enumerator = Linq4j.enumerator(strings);
        Enumerator<String> transform = Linq4j.transform(enumerator, x->x.toUpperCase());
        while(transform.moveNext()){
            String current = transform.current();
            System.out.println(current);
        }
    }


    /**
     * Linq4j.transform:转换集合
     *  常用场景:集合元素转长度
     */
    @Test
    public void test_transform2(){
        List<String> strings = Arrays.asList("a", "zx", "49ds");
        Enumerator<String> enumerator = Linq4j.enumerator(strings);
        Enumerator<Integer> transform = Linq4j.transform(enumerator, x->x.length());
        while(transform.moveNext()){
            Integer current = transform.current();
            System.out.println(current);
        }
    }

    /**
     * transform:转换集合
     *  常用场景:集合元素转长度
     */
    @Test
    public void test_transform3(){
        List<String> strings = Arrays.asList("2", "1", "89");
        Enumerator<String> enumerator = Linq4j.enumerator(strings);
        Enumerator<Integer> transform = Linq4j.transform(enumerator, x->Integer.parseInt(x));
        while(transform.moveNext()){
            Integer current = transform.current();
            System.out.println(current);
        }
    }

    @Test
    public void test(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("小明",110));
        students.add(new Student("小张",120));
        students.add(new Student("小张",130));

        List<Classes> classess = new ArrayList<>();
        classess.add(new Classes("1年级",110));
        classess.add(new Classes("1年级",120));

        Enumerator<Student> enumerator = Linq4j.enumerator(students);

        Enumerator<StudentView> transform = Linq4j.transform(enumerator, student -> {
            Classes cls = Linq4j.asEnumerable(classess).where(classes -> {
                return classes.getNo() == student.getClassNo();
            }).singleOrDefault();

            if (cls != null) {
                return new StudentView(student.getName(), cls.getName(), cls.getNo());
            }
            return null;
        });

        while(transform.moveNext()){
            StudentView current = transform.current();
            System.out.println(current);
        }

    }

}
