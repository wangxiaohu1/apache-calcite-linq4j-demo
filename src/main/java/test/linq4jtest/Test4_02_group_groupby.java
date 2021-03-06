package test.linq4jtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Grouping;
import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.function.Function0;
import org.apache.calcite.linq4j.function.Function1;
import org.apache.calcite.linq4j.function.Functions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Classes;
import test.model.Student;

/**
 * @author wangxiaohu
 * @version Id: Test6_groupby.java, v0.1 2021年12月06日 09:47:58 wangxiaohu Exp $
 */
public class Test4_02_group_groupby {
    List<Student> students = new ArrayList();

    @Before
    public void before(){
        students.add(new Student(1,20,"小李"));
        students.add(new Student(1,18,"小王"));
        students.add(new Student(2,32,"小张"));
    }

    /**
     * groupBy: 分组
     */
    @Test
    public void test_groupBy(){
        Enumerable<Grouping<Integer, Student>> groupings = Linq4j.asEnumerable(students)
            .groupBy(x->x.getSex());

        for(Grouping<Integer,Student> g : groupings) {
            List<String> list = Linq4j.asEnumerable(g.<Student>toList()).select(x -> x.getName()).toList();
            if(1 == g.getKey()){
                Assert.assertEquals("[小李, 小王]" , list.toString());
            }else if (2 == g.getKey()) {
                Assert.assertEquals("[小张]" , list.toString());
            }else{
                Assert.fail("无法匹配");
            }
        }
    }

    /**
     *
     * groupBy(Function1<TSource, TKey> keySelector,
     *       Function0<TAccumulate> accumulatorInitializer,
     *       Function2<TAccumulate, TSource, TAccumulate> accumulatorAdder,
     *       Function2<TKey, TAccumulate, TResult> resultSelector)
     *  :分组，并且按照每个分组来处理分组中元素
     *    keySelector：分组key
     *    accumulatorInitializer:用于拼接字段的初始化处理器
     *    accumulatorAdder: 分组内部的元素处理器
     *    resultSelector:每个分组处理器
     */
    @Test
    public void test_groupBy2(){
        String str = Linq4j.asEnumerable(students)
            .groupBy(
                student->student.getSex(),
                //appenderStr初始值
                (Function0<String>) ()->null,
                //appenderStr分组中元素列表迭代上一次处理的结果
                (appenderStr, student)-> appenderStr==null?student.getName():appenderStr+"+"+student.getName(),
                //key是每个分组的key，appenderStr是对每个分组中元素列表处理的最终结果(我们案例中，就是拼接name的结果)
                (groupKey,appenderStr)->groupKey+":"+appenderStr
            )
            .orderBy(Functions.identitySelector())
            .toList().toString();
        System.out.println(str);
        Assert.assertEquals("[1:小李+小王, 2:小张]", str);

        //使用java api实现上面的需求案例，比较麻烦
        Map<Integer,List<Student>> map = new HashMap<>();
        for(Student student : students) {
            if(map.get(student.getSex())==null){
                map.put(student.getSex(),new ArrayList<Student>());
            }
            map.get(student.getSex()).add(student);
        }
        List<String> appenderll = new ArrayList<>();
        Set<Integer> set = map.keySet();
        for(Integer k:set){
            List<Student> ll = map.get(k);
            String strappender = null;
            for(Student st : ll){
                if(strappender==null){
                    strappender = st.getName();
                }else{
                    strappender = strappender + "+" +st.getName();
                }
            }
            appenderll.add(k+":"+strappender);

        }
        System.out.println(appenderll.toString());
        //[1:小李+小王, 2:小张]
    }


    /**
     * groupBy(Function1<TSource, TKey> keySelector,Function1<TSource, TElement> elementSelector)
     *
     * keySelector: 分组key提取器
     * elementSelector: 每个分组元素的的value提取器
     */
    @Test
    public void test_groupBy_3(){
        List<String> list = Linq4j.asEnumerable(students)
            .groupBy(x->x.getSex(), x->x.getName())
            .select(
                group -> group.getKey() + ":" + group.select(x->x).toList().toString()

            )
            .toList();

        Assert.assertEquals("[1:[小李, 小王], 2:[小张]]",list.toString());
    }

    /**
     * groupBy(
     *       Function1<TSource, TKey> keySelector,
     *       Function2<TKey, Enumerable<TSource>, TResult> resultSelector)
     *
     * keySelector: 分组key选择器
     * resultSelector: 结果选择器，第1个参数是分组key, 第2个参数分组结果group
     */
    @Test
    public void test_groupBy_4(){
        List<String> list = Linq4j.asEnumerable(students)
            .groupBy(
                x->x.getSex(),
                (key, group) -> key + ":" + group.select(element -> element.getName()).toList().toString()
            )

            .toList();

        Assert.assertEquals("[1:[小李, 小王], 2:[小张]]",list.toString());
    }
}
