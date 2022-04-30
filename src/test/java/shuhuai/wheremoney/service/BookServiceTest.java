package shuhuai.wheremoney.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class BookServiceTest {
    @Resource
    private BookService bookService;

    @Test
    public void compileTest() {
        log.info("编译测试");
    }

    @Test
    public void getPayMonthTest() {
        log.info("获取月支出测试");
        log.info("{}号账本月支出：{}", 23, bookService.getPayMonth(23));
    }

    @Test
    public void getIncomeMonthTest() {
        log.info("获取月收入测试");
        log.info("{}号账本月收入：{}", 23, bookService.getIncomeMonth(23));
    }

    @Test
    public void getBalanceMonthTest() {
        log.info("获取月结余测试");
        log.info("{}号账本月结余：{}", 23, bookService.getBalanceMonth(23));
    }

    @Test
    public void getRefundMonthTest() {
        log.info("获取月退款测试");
        log.info("{}号账本月退款：{}", 23, bookService.getRefundMonth(23));
    }
}