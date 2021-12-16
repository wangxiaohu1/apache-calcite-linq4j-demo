package test.linq4jtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Classes;
import test.model.Student;

/**
 * @author wangxiaohu
 * @version Id: Test6_G2_join.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test1_08_product {

    /**
     * 排列
     */
    @Test
    public void test(){
        List<Enumerator<Integer>> list = new ArrayList<>();
        list.add(Linq4j.enumerator(Arrays.asList(1,5,6)));
        list.add(Linq4j.enumerator(Arrays.asList(3,8)));

        Enumerator<List<Integer>> product = Linq4j.product(list);
        while (product.moveNext()){
            System.out.println(product.current());
            //[1, 3]
            //[1, 8]
            //[5, 3]
            //[5, 8]
            //[6, 3]
            //[6, 8]
        }
    }
}
