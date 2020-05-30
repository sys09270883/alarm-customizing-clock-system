import javafx.util.Pair;

import java.text.SimpleDateFormat;

/**
 * @author Yoonseop Shin
 */
public class Date {
    /*
     * D-day 계산시 year, month, day를 가져오는함수가 필요해
     * 추가했습니다. 추후 방법개선 시 삭제
     */
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    // 년, 월의 상위, 하위 한계값
    public final int YEAR_TOP_LIMIT = 2099;
    public final int YEAR_BOTTON_LIMIT = 2020;
    public final int MONTH_TOP_LIMIT = 12;
    public final int MONTH_BOTTON_LIMIT = 1;

    // 인덱스 1 ~ 12가 월에 대응하는 일 수. 인덱스 0는 TimeSettingMode일 때 순환적인 처리를 위해 '일'의 최솟값 1을 넣어놨다.
    public int numOfDays[] = {1,31,28,31,30,31,30,31,31,30,31,30,31};

    private int year;
    private int month;
    private int day;
    private Object lock = new Object(); // 쓰레드 race condition 방지 위한 lock

    public Date() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
        String curTime = format.format(java.lang.System.currentTimeMillis());

        String splited[] = curTime.split(" ");

        year = Integer.parseInt(splited[0]);
        month = Integer.parseInt(splited[1]);
        day = Integer.parseInt(splited[2]);
    }

    public String getCurrentDate() {
        String currentDate;
        synchronized(lock) {
            currentDate = Integer.toString(year) + " " + Integer.toString(month) + " " + Integer.toString(day);
        }
        return currentDate;
    }

    public void deleteDday() {
        synchronized(lock) {
            year = 0;
            month = 0;
            day = 0;
        }
    }

    public void raiseDate() {
        if(++day == numOfDays[month]) { day = 0; ++month; }
        if(month > 12) { month = 0; ++year; }

        // TODO 2099년이 넘어가면 어떻게 처리할지 의논해야됨
        //if(year > 2099) {  }
    }
}