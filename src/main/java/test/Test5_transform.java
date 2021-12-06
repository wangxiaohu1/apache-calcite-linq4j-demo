package test;

import java.util.Arrays;
import java.util.List;

import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangxiaohu
 * @version Id: Test7_transform.java, v0.1 2021年12月06日 16:49:36 wangxiaohu Exp $
 */
public class Test5_transform {

    /**
     * transform:转换集合
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
     * transform:转换集合
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

}
