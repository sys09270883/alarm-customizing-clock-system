import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

public class TimeKeeping extends Function {

    final static String[] DAY_OF_THE_WEEK = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    private final int TYPE_SIZE = 6;

    // 0 -> Default Mode, 1 -> TimeSettingMode
    private int mode;
    private Time curTime;
    private Date curDate;
    private int d_day;
    private int alarmCnt;
    private int dayOfTheWeek; // 1 : 일요일 7 : 토요일
    private int type;

    // TimeSettingMode일 때, 사용자가 변화시키는 값을 임시 저장하는 배열
    private int timeSettingValue[] = {-1, -1, -1, -1, -1, -1};

    public TimeKeeping(System system) {
        fid = 1;
        curDate = new Date();
        d_day = -1;
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

        mode = 0;
        curTime = new Time(1);

        curTime.setDateListener(() -> {
            curDate.raiseDate();
        });

        curTime.setSecondListener(() -> {
            if (mode == 0) {
                String str = curTime.getCurrentTime();
                StringTokenizer st = new StringTokenizer(str, " ");
                system.GUI.timekeepingView.setCurTime1(String.format("%2s", st.nextToken())
                        + String.format("%2s", st.nextToken()));
                system.GUI.timekeepingView.setCurTime2(String.format("%2s", st.nextToken()));
                system.GUI.timekeepingView.setDayofweek(DAY_OF_THE_WEEK[dayOfTheWeek - 1]);
                str = curDate.getCurrentDate();
                st = new StringTokenizer(str, " ");
                system.GUI.timekeepingView.setDate(String.format("%2s", st.nextToken().substring(2, 4))
                        + String.format("%2s", st.nextToken())
                        + String.format("%2s", st.nextToken()));

                // D-day
                if (system.d_day != null) {
                    int dDay = system.d_day.getD_day();
                    if (dDay == -1)
                        system.GUI.timekeepingView.setdDay("NONE");
                    else
                        system.GUI.timekeepingView.setdDay(String.format("%3s", Integer.toString(dDay)));
                } else
                    system.GUI.timekeepingView.setdDay("NONE");

                // Alarm
                if (system.alarm != null) {
                    int alarmNum = system.alarm.getSize();
                    system.GUI.timekeepingView.setAlarmNum(String.format("%2s", Integer.toString(alarmNum)));
                } else
                    system.GUI.timekeepingView.setAlarmNum("0");

            }
        });

        curTime.startTime();
        type = 0;
    }

    public void changeMode() {
        mode ^= 1;

        // 현재 년, 월, 일, 시, 분, 초 로 timeSettingValue 초기화
        if (mode == 1) {
            this.type = 0;

            String currentTime = curTime.getCurrentTime();
            String currentDate = curDate.getCurrentDate();

            String ymd[] = currentDate.split(" ");
            String hms[] = currentTime.split(" ");
            timeSettingValue[0] = Integer.parseInt(hms[0]);
            timeSettingValue[1] = Integer.parseInt(hms[1]);
            timeSettingValue[2] = Integer.parseInt(hms[2]);
            timeSettingValue[3] = Integer.parseInt(ymd[0]);
            timeSettingValue[4] = Integer.parseInt(ymd[1]);
            timeSettingValue[5] = Integer.parseInt(ymd[2]);

            lastOperateTime = java.lang.System.currentTimeMillis();
        } else {
            // timeSettingValue -1로 비활성화
            for (int i = 0; i < TYPE_SIZE; ++i)
                timeSettingValue[i] = -1;
        }
    }

    public void changeValue(int diff) {
        timeSettingValue[type] += diff;
        lastOperateTime = java.lang.System.currentTimeMillis();

        // 각 type 값 검사
        switch (type) {
            case 0:
                if (timeSettingValue[type] < curTime.TIME_BOTTOM_LIMIT)
                    timeSettingValue[type] = curTime.TIME_BOTTOM_LIMIT;
                else if (timeSettingValue[type] > curTime.HOUR_TOP_LIMIT)
                    timeSettingValue[type] = curTime.HOUR_TOP_LIMIT;
                break;
            case 1:
                if (timeSettingValue[type] < curTime.TIME_BOTTOM_LIMIT)
                    timeSettingValue[type] = curTime.TIME_BOTTOM_LIMIT;
                else if (timeSettingValue[type] > curTime.MINUTE_TOP_LIMIT)
                    timeSettingValue[type] = curTime.MINUTE_TOP_LIMIT;
                break;
            case 2:
                if (timeSettingValue[type] < curTime.TIME_BOTTOM_LIMIT)
                    timeSettingValue[type] = curTime.TIME_BOTTOM_LIMIT;
                else if (timeSettingValue[type] > curTime.SECOND_TOP_LIMIT)
                    timeSettingValue[type] = curTime.SECOND_TOP_LIMIT;
                break;
            case 3:
                if (timeSettingValue[type] < curDate.YEAR_BOTTON_LIMIT)
                    timeSettingValue[type] = curDate.YEAR_BOTTON_LIMIT;
                else if (timeSettingValue[type] > curDate.YEAR_TOP_LIMIT)
                    timeSettingValue[type] = curDate.YEAR_TOP_LIMIT;
                break;
            case 4:
                if (timeSettingValue[type] < curDate.MONTH_BOTTON_LIMIT)
                    timeSettingValue[type] = curDate.MONTH_BOTTON_LIMIT;
                else if (timeSettingValue[type] > curDate.MONTH_TOP_LIMIT)
                    timeSettingValue[type] = curDate.MONTH_TOP_LIMIT;
                break;
            case 5:
                if (timeSettingValue[type] < curDate.numOfDays[0])
                    timeSettingValue[type] = curDate.numOfDays[timeSettingValue[0]];
                else if (timeSettingValue[type] > curDate.numOfDays[timeSettingValue[4]])
                    timeSettingValue[type] = curDate.numOfDays[4];
                break;
        }
    }

    public void changeType() {
        type = (type + 1) % TYPE_SIZE;
        lastOperateTime = java.lang.System.currentTimeMillis();
    }

    public void requestSave() {
        if (timeSettingValue[5] > curDate.numOfDays[timeSettingValue[4]]) {
            changeMode();
            return;
        }

        curTime.pauseTime();
        try {
            curTime.getTimeThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        curTime.setTime(timeSettingValue[0], timeSettingValue[1], timeSettingValue[2]);
        curDate.setDate(timeSettingValue[3], timeSettingValue[4], timeSettingValue[5]);
        curTime.startTime();
        changeMode();
    }

    public void requestTimeSettingMode() {
        this.changeMode();
    }

    public int[] getTimeSettingValue() {
        return timeSettingValue;
    }

    public int getType() {
        return type;
    }

    public int getMode() {
        return mode;
    }

    public long getLastOperateTime() {
        return this.lastOperateTime;
    }
}