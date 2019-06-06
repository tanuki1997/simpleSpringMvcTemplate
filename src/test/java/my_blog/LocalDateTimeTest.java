package my_blog;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @创建人: Mr Chen
 * @创建时间: 2019/4/8
 * @描述:
 */
public class LocalDateTimeTest {
    @Test
    public void test1() {
        //比较时间
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime depDateLoc = LocalDateTime.parse("2019-04-05 15:30:00", dtf);
        System.out.println(depDateLoc.getYear());
        System.out.println(depDateLoc.getMonthValue());
        System.out.println(depDateLoc.getDayOfMonth());
        LocalDateTime arrDateLoc = LocalDateTime.parse("2019-04-06 16:30:00", dtf);
        Duration duration = Duration.between(depDateLoc, arrDateLoc);
        System.out.println(duration.toDays() + "天");
        System.out.println(duration.toHours() + "时");
        System.out.println(duration.toMinutes() + "分");
        System.out.println(depDateLoc.isBefore(arrDateLoc));
    }

    @Test
    public void test2() {
        //比较时间
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate depDateLoc = LocalDate.parse("2019-04-05 15:30:00", dtf);
        System.out.println(depDateLoc.getYear());
        System.out.println(depDateLoc.getMonthValue());
        System.out.println(depDateLoc.getDayOfMonth());
        LocalDate arrDateLoc = LocalDate.parse("2019-04-05 16:30:00", dtf);
        System.out.println(depDateLoc.isBefore(arrDateLoc));
        System.out.println(depDateLoc.isAfter(arrDateLoc));
        System.out.println(depDateLoc.isEqual(arrDateLoc));
    }
}
