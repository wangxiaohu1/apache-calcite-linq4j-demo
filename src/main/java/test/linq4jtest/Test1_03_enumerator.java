package test.linq4jtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangxiaohu
 * @version Id: Test7_enumerable.java, v0.1 2021年12月06日 16:49:36 wangxiaohu Exp $
 */
public class Test1_03_enumerator {


    /**
     * enumerator:  转 Enumerator
     */
    @Test
    public void test_enumerator(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        Enumerator<String> stringEnumerator = Linq4j.enumerator(list);
        while (stringEnumerator.moveNext()){
            System.out.println(stringEnumerator.current());
            //a
            //b
        }
    }


    /**
     * emptyEnumerator:空集合迭代器
     */
    @Test
    public void test_emptyEnumerator(){
        Enumerator<String> e = Linq4j.<String>emptyEnumerator();
        Assert.assertEquals(false,e.moveNext());
    }

    /**
     * singletonEnumerator:单元素结合迭代器
     */
    @Test
    public void test_singletonEnumerator(){
        Enumerator<String> e = Linq4j.<String>singletonEnumerator("test");
        Assert.assertEquals("test",e.current());

        Assert.assertEquals(true,e.moveNext());
        Assert.assertEquals("test",e.current());

        Assert.assertEquals(false,e.moveNext());
    }

    /**
     * singletonEnumerator:单元素null值集合迭代器
     */
    @Test
    public void test_singletonNullEnumerator(){
        Enumerator<String> e = Linq4j.<String>singletonNullEnumerator();
        Assert.assertEquals(null,e.current());

        Assert.assertEquals(true,e.moveNext());
        Assert.assertEquals(null,e.current());

        Assert.assertEquals(false,e.moveNext());
    }

    /**
     * iterableEnumerator: Iterable/List 转 Enumerator
     */
    @Test
    public void test_iterableEnumerator(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        Enumerator<String> stringEnumerator = Linq4j.iterableEnumerator(list);
        while (stringEnumerator.moveNext()){
            System.out.println(stringEnumerator.current());
            //a
            //b
        }
    }


    /**
     * iterableEnumerator: Iterable 转 Enumerator
     */
    @Test
    public void test_enumeratorIterator(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        Enumerator<String> enumerator = Linq4j.transform(
            Linq4j.enumerator(list),
            x->x.toUpperCase()
        );

        Iterator<String> iterator = Linq4j.enumeratorIterator(enumerator);

        while(iterator.hasNext()){
            System.out.println(iterator.next());

            //A
            //B
        }
    }



}
