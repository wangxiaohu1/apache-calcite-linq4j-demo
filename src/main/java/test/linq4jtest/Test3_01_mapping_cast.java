package test.linq4jtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangxiaohu
 * @version Id: Test5_cast.java, v0.1 2021年12月06日 17:23:08 wangxiaohu Exp $
 */
public class Test3_01_mapping_cast {

    /**
     * 集合元素类型转换
     *
     * 用法注意：
     *   1.如果Xxx.class不是子类， cast(Xxx.class).toList()会抛出类型转换异常
     *   2.如果Xxx.class不是子类， Enumerator cast = cast(Xxx.class).enumerator，不会抛出异常，在迭代Enumerator的时会自动过滤
     *      掉不是Xxx.class子类的元素
     *
     *
     */
    @Test
    public void test_cast_1(){
        //1. 强制类型转换，
        List<Map> mapList = new ArrayList<>();
        Map map = new HashMap();
        map.put("key","v");
        mapList.add(map);

        Map map2 = new HashMap();
        map2.put("key","v2");
        mapList.add(map2);

        List<HashMap> hashMaps = Linq4j.asEnumerable(mapList).cast(HashMap.class).toList();
        System.out.println(hashMaps.get(0).get("key"));
        System.out.println(hashMaps.get(1).get("key"));
        //v
        //v2
    }

    /**
     * 将格式为Number类型换成Integer类型
     */

    @Test
    public void test_cast(){
        List<Number> numbers = Arrays.asList(2, 2.12, null);
        Enumerable<Integer> cast = Linq4j.asEnumerable(numbers).cast(Integer.class);

        Enumerator<Integer> enumerator = cast.enumerator();
        enumerator.moveNext();
        Integer current = enumerator.current();
        Assert.assertEquals(Integer.valueOf(2), current);

        Exception e = null;
        enumerator.moveNext();
        try{
            Object current2 = enumerator.current();
            Assert.fail("应该异常");
        }catch (ClassCastException ex) {
            e = ex;
        }
        Assert.assertNotNull(e);

        enumerator.moveNext();
        Integer i = enumerator.current();
        Assert.assertNull(i);
    }

    /**
     * Linq4j.cast
     */
    @Test
    public void test_Linq4j_cast2() {
        List<Number> numbers = Arrays.asList(2, 2.12, null);
        List<Integer> cast = Linq4j.cast(numbers,Integer.class).toList();
        System.out.println(cast);
    }
}
