import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeKeepingTest {
    System system = new System();
    private int type = 0;

    @Test
    public void setTimeTest() {
        TimeKeeping timekeeping = new TimeKeeping(system);

        timekeeping.requestTimeSettingMode();

        String time = timekeeping.getCurTime().getTime();
        String date = timekeeping.getCurDate().getCurrentDate();
        int dayOfTheWeek;
        //1: 일요일~ 7: 토요일

        // 시간, 분, 초, 년, 월, 일 순으로 설정
        for (int i = 0; i < 5; i++) {
            timekeeping.changeValue(2);
            timekeeping.changeType();
        }
        timekeeping.changeValue(2);
        timekeeping.requestSave();

        String splitedTime[] = time.split(" ");
        int splitedTimeInt[] = new int[3];
        for (int i = 0; i < 3; i++)
            splitedTimeInt[i] = Integer.parseInt(splitedTime[i]) + 2;
        String splitedDate[] = date.split(" ");
        int splitedDateInt[] = new int[3];
        for (int i = 0; i < 3; i++)
            splitedDateInt[i] = Integer.parseInt(splitedDate[i]) + 2;

        time = splitedTimeInt[0] + " " + splitedTimeInt[1] + " " + splitedTimeInt[2];
        date = splitedDateInt[0] + " " + splitedDateInt[1] + " " + splitedDateInt[2];

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
        try {
            java.util.Date date1 = dateFormat.parse(date);
            calendar.setTime(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);

        assert (timekeeping.getCurTime().getTime().equals(time));
        assert (timekeeping.getCurDate().getCurrentDate().equals(date));
        assert (timekeeping.getDayOfTheWeek() == dayOfTheWeek);

        //1: 일요일~ 7: 토요일
    }

    @Test
    public void displayTimeTest() { //display는 test하기 애매해요
        TimeKeeping timekeeping = new TimeKeeping(system);

        //시간 출력
        java.lang.System.out.println(timekeeping.getCurTime().getTime());
        //날짜 출력
        java.lang.System.out.println(timekeeping.getCurDate().getCurrentDate());
        //요일 출력
        java.lang.System.out.println(timekeeping.getDayOfTheWeek());
        //d-day 출력
        java.lang.System.out.println(timekeeping.getD_day());
    }

    @Test
    public void setDisplayTest() {
        //java.lang.System.out.println(system.getSelectedFid());
        system.nextFunction();
        assert (system.getSelectedFid() == 2);
    }
}