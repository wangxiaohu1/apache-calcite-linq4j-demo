package test.linq4jtest;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.function.EqualityComparer;
import org.apache.calcite.linq4j.function.Function1;
import org.apache.calcite.linq4j.function.Functions;
import org.junit.Before;
import org.junit.Test;
import test.model.Classes;
import test.model.Student;
import static org.junit.Assert.*;

/**
 * @author wangxiaohu
 * @version Id: Test2_tomao.java, v0.1 2021年11月06日 17:56:58 wangxiaohu Exp $
 */
public class Test1_05_toMap {
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
     * toMap(Function1 keySelector): 数组、列表转换成map，
     * 1、value可以为空
     *      collect(HashMap::new, (m, v) -> m.put(v.getSex(), v.getName() ), HashMap::putAll);
     *
     * 2、key值重复后，会用后面的key值覆盖前面的key值，java8 stream需要自己处理
     *       java8：Collectors.toMap(x -> x.getSex(), x->x, (v1,v2)->v2)
     */
    @Test
    public void test_tomap(){
        List<Student> list = new ArrayList();
        list.add(new Student(1,20,"小王"));
        list.add(new Student(1,18,"小李"));
        list.add(new Student(2,32,null));

        Map<Integer, Student> map = Linq4j.asEnumerable(list).toMap(x -> x.getSex());
        assertEquals(2,map.size());
        assertEquals("小李", map.get(1).getName());
        assertEquals(null, map.get(2).getName());

        //java8 stream list to map，需要处理用"(v1,v2)->v2)"处理key重复的情况
        Map<Integer, Student> map2 = list.stream().collect(
            Collectors.toMap(x -> x.getSex(), x->x, (v1,v2)->v2));

    }

    @Test
    public void test_tomap2(){
        List<Student> list = new ArrayList();
        list.add(new Student(1,20,"小王"));
        list.add(new Student(1,18,"小李"));
        list.add(new Student(2,32,"小张"));
        list.add(new Student(3,32,null));

        Map<Integer, String> map = Linq4j.asEnumerable(list).toMap(x -> x.getSex(), x -> x.getName());
        System.out.println(map);
        //{1=小李, 2=小张, 3=null}
        assertEquals(3,map.size());
        assertEquals(null, map.get(3));

        /**
         *  java8 stream list to map
         *  1、需要处理用"(v1,v2)->v2)"处理key重复的情况
         *  2、需要处理value为空的情况
         */
        Map<Integer, String> map2 = list.stream().collect(
            HashMap::new, (m, v) -> m.put(v.getSex(), v.getName() ), HashMap::putAll);
        System.out.println(map);
        //{1=小李, 2=小张, 3=null}
        assertEquals(3,map.size());
        assertEquals(null, map.get(3));
    }

    @Test
    public void testToMap2WithComparer() {
        final Map<String, String> map =
                Linq4j.asEnumerable(Arrays.asList("foo", "bar", "far"))
                        .toMap(Functions.identitySelector(),
                                x -> x == null ? null : x.toUpperCase(Locale.ROOT),
                                new EqualityComparer<String>() {
                                    public boolean equal(String v1, String v2) {
                                        //忽略大小写比较
                                        return String.CASE_INSENSITIVE_ORDER.compare(v1, v2) == 0;
                                    }
                                    public int hashCode(String s) {
                                        return s == null ? Objects.hashCode(null)
                                                : s.toLowerCase(Locale.ROOT).hashCode();
                                    }
                                });
        assertEquals(3, map.size());
        //assertTrue(map.get("foo").equals("FOO"));
        //assertTrue(map.get("Foo").equals("FOO"));
        assertTrue(map.get("FOo").equals("FoO"));

        System.out.println(map);
    }


    /**
     * toMap(Function1<TSource, TKey> var1, EqualityComparer<TKey> var2)
     */

    @Test
    public void testToMap2WithComparer2() {
        final Map<String, String> map =
                Linq4j.asEnumerable(Arrays.asList("foo", "bar", "far"))
                        .toMap(Functions.identitySelector(),
                                new EqualityComparer<String>() {
                                    public boolean equal(String v1, String v2) {
                                        //忽略大小写比较
                                        return String.CASE_INSENSITIVE_ORDER.compare(v1, v2) == 0;
                                    }
                                    public int hashCode(String s) {
                                        return s == null ? Objects.hashCode(null)
                                                : s.toLowerCase(Locale.ROOT).hashCode();
                                    }
                                });
        assertEquals(3, map.size());
        assertTrue(map.get("foo").equals("foo"));
        assertTrue(map.get("Foo").equals("foo"));
        assertTrue(map.get("FOO").equals("foo"));

        System.out.println(map);
    }
}
