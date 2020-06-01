import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.lang.System;

public class TimeKeeping extends Function {

    //Time 쓰레드 테스트 코드
//    public static void main(String args[]) {
//        TimeKeeping tk = new TimeKeeping();
//
//        System.out.println(tk.curDate.getCurrentDate());
//        System.out.println(tk.dayOfTheWeek);
//
//        for(int i=0; i<100; ++i) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            tk.printCurTime();
//            tk.printCurDate();
//        }
//    }
//    public void printCurTime() {
//        System.out.println(curTime.getCurrentTime());
//    }
//    public void printCurDate() {
//        System.out.println(curDate.getCurrentDate());
//    }

    private final int TYPE_SIZE = 6;

    // TODO 년, 월, 일, 시, 분, 초 LIMIT 변수 논의 필요

    // 0 -> Default Mode, 1 -> TimeSettingMode
    private int mode;
    private Time curTime;
    private Date curDate;
    private int d_day;
    private int alarmCnt;
    private int dayOfTheWeek; // 1 : 일요일 7 : 토요일
    private int type;

    // TODO timeSettingValue 논의 필요
    // TimeSettingMode일 때, 사용자가 변화시키는 값을 임시 저장하는 배열
    private int timeSettingValue[] = {-1,-1,-1,-1,-1,-1};

    public TimeKeeping() {
        mode = 0;
        curTime = new Time(1);
        curTime.startTime();
        curTime.setListener(() -> curDate.raiseDate());
        curDate = new Date();
        d_day = -1; // TODO 초기값 의논 필요
        alarmCnt = 0;

        // 년,월,일에 맞는 요일 설정
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
        try {
            java.util.Date date = dateFormat.parse(curDate.getCurrentDate());
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);

        type = 0;
    }

    public void timeout() {}
    public void cancel() {}

    public void changeMode() {
        mode ^= 1;

        // 현재 년, 월, 일, 시, 분, 초 로 timeSettingValue 초기화
        if(mode == 1) {
            String currentTime = curTime.getCurrentTime();
            String currentDate = curDate.getCurrentDate();

            String ymd[] = currentDate.split(" ");
            String hms[] = currentTime.split(" ");
            timeSettingValue[0] = Integer.parseInt(ymd[0]);
            timeSettingValue[1] = Integer.parseInt(ymd[1]);
            timeSettingValue[2] = Integer.parseInt(ymd[2]);
            timeSettingValue[3] = Integer.parseInt(hms[0]);
            timeSettingValue[4] = Integer.parseInt(hms[1]);
            timeSettingValue[5] = Integer.parseInt(hms[2]);
        } else {
            // timeSettingValue -1로 비활성화
            for(int i=0; i<TYPE_SIZE; ++i)
                timeSettingValue[i] = -1;
        }
    }

    public void changeValue(int diff) {
        timeSettingValue[type] += diff;

        // 각 type 값 검사 (년, 월, 일, 시, 분, 초 순)
        switch(type) {
            case 0 :
                if(timeSettingValue[type] < curDate.YEAR_BOTTON_LIMIT)
                    timeSettingValue[type] = curDate.YEAR_TOP_LIMIT;
                else if(timeSettingValue[type] > curDate.YEAR_TOP_LIMIT)
                    timeSettingValue[type] = curDate.YEAR_BOTTON_LIMIT;
            case 1 :
                if(timeSettingValue[type] < curDate.MONTH_BOTTON_LIMIT)
                    timeSettingValue[type] = curDate.MONTH_TOP_LIMIT;
                else if(timeSettingValue[type] > curDate.MONTH_TOP_LIMIT)
                    timeSettingValue[type] = curDate.MONTH_BOTTON_LIMIT;
            case 2 :
                if(timeSettingValue[type] < curDate.numOfDays[timeSettingValue[0]])
                    timeSettingValue[type] = curDate.numOfDays[timeSettingValue[type]];
                else if(timeSettingValue[type] > curDate.numOfDays[timeSettingValue[type]])
                    timeSettingValue[type] = curDate.numOfDays[0];
            case 3 :
                if(timeSettingValue[type] < curTime.TIME_BOTTOM_LIMIT)
                    timeSettingValue[type] = curTime.HOUR_TOP_LIMIT;
                else if(timeSettingValue[type] > curTime.HOUR_TOP_LIMIT)
                    timeSettingValue[type] = curTime.TIME_BOTTOM_LIMIT;
            case 4 :
                if(timeSettingValue[type] < curTime.TIME_BOTTOM_LIMIT)
                    timeSettingValue[type] = curTime.MINUTE_TOP_LIMIT;
                else if(timeSettingValue[type] > curTime.MINUTE_TOP_LIMIT)
                    timeSettingValue[type] = curTime.TIME_BOTTOM_LIMIT;
            case 5 :
                if(timeSettingValue[type] < curTime.TIME_BOTTOM_LIMIT)
                    timeSettingValue[type] = curTime.SECOND_TOP_LIMIT;
                else if(timeSettingValue[type] > curTime.SECOND_TOP_LIMIT)
                    timeSettingValue[type] = curTime.TIME_BOTTOM_LIMIT;
        }
    }

    public void changeType() {
        type = (type + 1) % TYPE_SIZE;
    }

    // TODO system 클래스에서 GUI 제어하기 위해선 type, value에 대한 getter가 필요할 것으로 보임. 논의 필요
}