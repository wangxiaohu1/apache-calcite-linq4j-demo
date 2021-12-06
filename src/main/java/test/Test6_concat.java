package test;

import java.util.Arrays;
import java.util.List;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangxiaohu
 * @version Id: Test6_concat.java, v0.1 2021年12月06日 17:45:01 wangxiaohu Exp $
 */
public class Test6_concat {
    /**
     * concat：拼接集合，相当于list.addAll
     */
    @Test
    public void test(){
        List<String> strings1 = Arrays.asList("a", "c", "b");
        List<String> strings2 = Arrays.asList("ew", "3", "b");

        Enumerable<String> concat = Linq4j.asEnumerable(strings1).concat(Linq4j.asEnumerable(strings2));
        System.out.println(concat.toList().toString());
        Assert.assertEquals("[a, c, b, ew, 3, b]",concat.toList().toString());
    }
}
