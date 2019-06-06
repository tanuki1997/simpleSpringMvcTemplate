package my_blog;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人: Mr Chen
 * @创建时间: 2019/3/7
 * @描述:
 */
public class TestExcetion {
    @Test
    public void test1() {
        //测试String.valueOf(Exception)
        try {
            if (true) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2() {
        List<Integer> list = new ArrayList<>();
        list.forEach(item -> {
            System.out.println(item);
        });

    }
}
