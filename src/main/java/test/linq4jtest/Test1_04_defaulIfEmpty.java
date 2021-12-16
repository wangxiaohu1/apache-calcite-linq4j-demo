package test.linq4jtest;

import java.util.ArrayList;
import java.util.List;

import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangxiaohu
 * @version Id: Test0_enumerable.java, v0.1 2021年12月06日 16:49:36 wangxiaohu Exp $
 */
public class Test1_04_defaulIfEmpty {

    /**
     * defaultIfEmpty:如果集合或者数组为非空长度为0(size length=0),可以设置一个默认值
     */
    @Test
    public void test() {

        List<String> list3 = new ArrayList<>();
        Enumerator<String> enumerator = Linq4j.asEnumerable(list3).enumerator();
        while(enumerator.moveNext()){
            Assert.assertEquals("", enumerator.current());
        }
        List<String> list4 = new ArrayList<>();
        Enumerator<String> enumerator2 = Linq4j.asEnumerable(list4).defaultIfEmpty().enumerator();
        while(enumerator2.moveNext()){
            Assert.assertNull(enumerator2.current());
        }

        List<String> list5 = new ArrayList<>();
        Enumerator<String> enumerator3 = Linq4j.asEnumerable(list5).defaultIfEmpty("defaultValue").enumerator();
        while(enumerator2.moveNext()){
            Assert.assertEquals("defaultValue",enumerator2.current());
        }


    }
}
