package test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.function.Function1;
import org.apache.calcite.linq4j.function.IntegerFunction1;
import org.junit.Before;
import org.junit.Test;
import test.model.Classes;
import test.model.Student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Linq4j
 * @author wangxiaohu
 * @version Id: Test1.java, v0.1 2021年10月29日 15:04:13 wangxiaohu Exp $
 */
public class Test1 {

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

    @Test
    public void test_select(){
        List<String> nameList = Linq4j.asEnumerable(students).select(x -> x.getName()).toList();
        assertEquals("[小李, 小王, 小张]", nameList.toString());
    }

    @Test
    public void test_where(){
        List<String> nameList = Linq4j.asEnumerable(students).where(x -> x.getSex() == 1).select(x -> x.getName()).toList();
        assertEquals("[小李, 小王]", nameList.toString());
    }

    @Test
    public void test_where_index(){
        List<String> nameList = Linq4j.asEnumerable(students).where((student, index) -> index % 2 == 1)
            .select(x -> x.getName()).toList();
        assertEquals("[小王]", nameList.toString());
    }

    @Test
    public void test_selectMany(){
        List<String> list = Linq4j.asEnumerable(classess).selectMany(c->Linq4j.asEnumerable(c.getStudents()))
            .select((stu, index) -> "#" + index + ":" + stu.getName()).toList();
        System.out.println(list);
        assertEquals("[#0:小李, #1:小王, #2:小张, #3:daiwei, #4:amay]", list.toString());
    }

    @Test
    public void test_count(){
        int count = Linq4j.asEnumerable(classess).count();
        assertEquals(3,count);
    }

    @Test
    public void test_countFilter(){
        int count = Linq4j.asEnumerable(classess).count(x -> x.getStudents().size() > 0);
        assertEquals(2,count);
    }
    
    @Test
    public void test_longCount(){
        long count = Linq4j.asEnumerable(classess).longCount();
        assertEquals(3,count);

        long count2 = Linq4j.asEnumerable(classess).longCount(x -> x.getStudents().size() > 0);
        assertEquals(2,count2);
    }

    @Test
    public void test_allFilter(){
        boolean all = Linq4j.asEnumerable(students).all(x -> x.getAge() > 10);
        boolean all2 = Linq4j.asEnumerable(students).all(x -> x.getAge() > 20);
        assertTrue(all);
        assertFalse(all2);
    }

    /**
     * any:判断集合是否有元素，有则为true
     */
    @Test
    public void test_any(){
        boolean any = Linq4j.asEnumerable(students).any();

        List<Student> emptys = Collections.emptyList();
        boolean any2 = Linq4j.asEnumerable(emptys).any();
        assertTrue(any);
        assertFalse(any2);
    }

    /**
     * any(predicate):判断否有符合过滤器指定条件的元素，有则为true
     */
    @Test
    public void test_anyFilter(){
        boolean any = Linq4j.asEnumerable(students).any(x -> "小王".equals(x.getName()));
        boolean any2 = Linq4j.asEnumerable(students).any(x -> "sim".equals(x.getName()));
        assertTrue(any);
        assertFalse(any2);
    }

    /**
     * average:求平均数
     */
    @Test
    public void test_average(){
        IntegerFunction1<Classes> func = c -> c.getNo();
        int average = Linq4j.asEnumerable(classess).average(func);
        assertEquals(10,average);
    }

    /**
     * min:最小值
     */
    @Test
    public void test_min(){
        int min = Linq4j.asEnumerable(classess).select(x -> x.getNo()).min();
        assertEquals(9,min);
    }

    /**
     * min(IntegerFunction1 selector)
     * min(Function1 selector):求最小值
     */
    @Test
    public void test_minSelector(){
        IntegerFunction1<Classes> func = c->c.getNo();
        int min = Linq4j.asEnumerable(classess).min(func);
        assertEquals(9,min);

        Function1<Classes,Integer> func2 = c->c.getNo();
        int min2 = Linq4j.asEnumerable(classess).min(func2);
        assertEquals(9,min2);
    }


    /**
     * max()
     * max(IntegerFunction1 selector)
     * max(Function1 selector):求最小值
     */
    @Test
    public void test_max(){
        int max = Linq4j.asEnumerable(classess).select(x -> x.getNo()).max();
        assertEquals(11,max);

        IntegerFunction1<Classes> func = c->c.getNo();
        int max2 = Linq4j.asEnumerable(classess).max(func);
        assertEquals(11,max2);

        Function1<Classes,Integer> func2 = c->c.getNo();
        int max3 = Linq4j.asEnumerable(classess).max(func2);
        assertEquals(11,max3);
    }

    /**
     * aggregate : 累加值
     */
    @Test
    public void test_aggregate(){
        String str = Linq4j.asEnumerable(classess).select(x->x.getName()).
            aggregate(null, (v1, v2) -> v1 == null ? v2 : v1 + "," + v2);
        System.out.println(str);
        assertEquals("5年级,2年级,6年级", str);


        String str2 = Linq4j.asEnumerable(classess).select(x->x.getName()).
            aggregate("12", (v1, v2) -> v1 == null ? v2 : v1 + "," + v2);
        System.out.println(str2);
        assertEquals("12,5年级,2年级,6年级", str2);

    }
}
