import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Yoonseop Shin
 */
public class System extends Function {

    // 6개 중 4개 인스턴스만 갖고있음.
    // 알람, 알람커스텀은 항상 둘 다 포함되거나 포함되지 않아야 한다.
    MainFrame GUI;
    TimeKeeping timeKeeping;
    Stopwatch stopwatch;
    Timer timer;
    D_day d_day;
    Alarm alarm;
    AlarmCustom alarmCustom;
    Buzzer buzzer;
    public Blink blink;
    private int functionNumIdx = 0;
    private int[] functionNum;
    private int selectedFid;
    private int status; // 비트마스킹: 0b00 0b01 0b10 0b11
    private int type;
    private Thread checkTimeOut;
    private long lastOperateTime;

    public System() {
        GUI = new MainFrame(this);

        functionNum = new int[4];
        Arrays.fill(functionNum, 0);
        functionNum[0] = 1;
        functionNum[1] = 2;
        functionNum[2] = 3;
        functionNum[3] = 4;
        status = 0b00;
        type = 0;
        selectedFid = 1;

        timeKeeping = new TimeKeeping(this);
        stopwatch = new Stopwatch(this);
//        timer = null;
//        d_day = null;
        timer = new Timer(this);
        d_day = new D_day(this);
//        alarm = new Alarm(this);
//        alarmCustom = new AlarmCustom(this);

        buzzer = new Buzzer();

        blink = new Blink(this);


    }

    public static void main(String[] args) {
        System system = new System();
    }

    public void startCheckTimeOut() {
        checkTimeOut = new Thread() {
            public void run() {
                Function curFunction;
                switch(functionNum[functionNumIdx]) {
                    case 1 : curFunction = timeKeeping; break;
                    case 2 : curFunction = stopwatch; break;
                    case 3 : curFunction = timer; break;
                    case 4 : curFunction = d_day; break;
                    case 5 : curFunction = alarm; break;
                    case 6 : curFunction = alarmCustom; break;
                    default: curFunction = null;
                }

                while(curFunction.getMode() == 1) {
                    try {
                        Thread.sleep(1000);
                        if(java.lang.System.currentTimeMillis() - lastOperateTime >= 600000) {
                            cancel(curFunction);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        checkTimeOut.start();
    }

    public void modeBtnLongPressed() {
        lastOperateTime = java.lang.System.currentTimeMillis();
        if (updateStatus() > -1)
            return;
        switch (selectedFid) {
            case 2:
                if (stopwatch.getMode() == 2) {
                    // cancel
                    stopwatch.changeMode(0);
                    GUI.stopwatchView.borderPanel.setVisible(false);
                }
                break;
            case 4: // dday
                cancel(d_day);
                GUI.d_dayView.borderPanel.setVisible(false);
                if (d_day.getD_day() == -1) {
                    GUI.d_dayView.setYear("  ");
                    GUI.d_dayView.setMonth("NO");
                    GUI.d_dayView.setDate("NE");
                }
                else {
                    String curDate = d_day.getD_dayDate().getCurrentDate();
                    StringTokenizer st = new StringTokenizer(curDate, " ");
                    GUI.d_dayView.setYear(String.format("%02d", Integer.parseInt(st.nextToken()) % 100));
                    GUI.d_dayView.setMonth(String.format("%02d", Integer.parseInt(st.nextToken())));
                    GUI.d_dayView.setDate(String.format("%02d", Integer.parseInt(st.nextToken())));
                }
        }
    }

    public void selectBtnLongPressed() {
        lastOperateTime = java.lang.System.currentTimeMillis();
        if (updateStatus() > -1)
            return;
        switch (selectedFid) {
            case 2: // 스탑워치
                if (stopwatch.getMode() == 0) {
                    stopwatch.requestRecordCheckMode();
                    GUI.stopwatchView.borderPanel.setVisible(true);
                }
            case 4: // d-day
                if (d_day.getMode() == 0) {
                    d_day.requestDeleteDday();

                    GUI.d_dayView.setYear("  ");
                    GUI.d_dayView.setMonth("NO");
                    GUI.d_dayView.setDate("NE");
                    GUI.d_dayView.setDday("000");
                }
        }
    }

    public void startBtnPressed() {
        lastOperateTime = java.lang.System.currentTimeMillis();
        if (updateStatus() > -1)
            return;
        switch (selectedFid) {
            case 1: // timekeeping에서 현재시간 설정하는 것
                if (timeKeeping.getMode() == 0) {
                    return;
                }
                // GUI에 반영해야 함.
                timeKeeping.changeValue(1);
                int[] timeSettingValue = timeKeeping.getTimeSettingValue();
                GUI.timekeepingView.setHour(String.format("%2s", timeSettingValue[0]));
                GUI.timekeepingView.setMinute(String.format("%2s", timeSettingValue[1]));
                GUI.timekeepingView.setCurTime2(String.format("%2s", timeSettingValue[2]));
                GUI.timekeepingView.setDate(String.format("%2s", timeSettingValue[3]).substring(2, 4)
                        + String.format("%2s", timeSettingValue[4])
                        + String.format("%2s", timeSettingValue[5]));
                break;
            case 2: // stopwatch
                if (stopwatch.getMode() == 0) {
                    stopwatch.requestStartStopwatch();
                }
                else if (stopwatch.getMode() == 1){
                    stopwatch.requestPauseStopwatch();
                }
                else {
                    String[] tmp = stopwatch.getStopwatchRecord();

                    int curRecordPointer = stopwatch.getRecordPointer();
                    if (curRecordPointer > 2 && tmp[0 + curRecordPointer] == null)
                        break;

                    stopwatch.movePointer(1);
                    String[] str = new String[3];
                    if (tmp[0 + curRecordPointer] == null)
                        str[0] = "      ";
                    else {
                        StringTokenizer st = new StringTokenizer(tmp[0 + curRecordPointer], " ");
                        str[0] = String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken()));
                    }
                    if (tmp[1 + curRecordPointer] == null)
                        str[1] = "      ";
                    else {
                        StringTokenizer st = new StringTokenizer(tmp[1 + curRecordPointer], " ");
                        str[1] = String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken()));

                    }
                    if (tmp[2 + curRecordPointer] == null)
                        str[2] = "      ";
                    else {
                        StringTokenizer st = new StringTokenizer(tmp[2 + curRecordPointer], " ");
                        str[2] = String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken()));
                    }

                    GUI.stopwatchView.setStopwatchList(str[0] + str[1] + str[2]);
                }
                break;
            case 3: // timer
                if (timer.getMode() == 0) {
                    timer.requestStartTimer();
                }
                else if (timer.getMode() == 1){
                    timer.changeValue(1);
                    int[] tsv = timer.getTimeSettingValue();
                    GUI.timerView.setHour(String.format("%02d", tsv[0]));
                    GUI.timerView.setMinute(String.format("%02d", tsv[1]));
                    GUI.timerView.setSecond(String.format("%02d", tsv[2]));
                }
                else {
                    timer.requestPauseTimer();
                }

                break;
            case 4: // d-day
                if (d_day.getMode() == 0) {
                    // 아무것도 없음.
                }
                else {
                    d_day.changeValue(1);
                    int[] curDate = d_day.getDateSettingValue();
                    GUI.d_dayView.setYear(String.format("%02d", curDate[0] % 100));
                    GUI.d_dayView.setMonth(String.format("%02d", curDate[1]));
                    GUI.d_dayView.setDate(String.format("%02d", curDate[2]));
                }
                break;
            case 5: // alarm
                if (alarm.getMode() == 0) {

                }
                else {

                }

                break;
            case 6: // alarm custom

                break;
        }
    }

    public void resetBtnPressed() {
        lastOperateTime = java.lang.System.currentTimeMillis();
        if (updateStatus() > -1)
            return;
        switch (selectedFid) {
            case 1: // timekeeping에서 현재시간 설정하는 것
                if (timeKeeping.getMode() == 0) {
                    return;
                }
                // GUI에 반영해야 함.
                timeKeeping.changeValue(-1);
                int[] timeSettingValue = timeKeeping.getTimeSettingValue();
                GUI.timekeepingView.setHour(String.format("%2s", timeSettingValue[0]));
                GUI.timekeepingView.setMinute(String.format("%2s", timeSettingValue[1]));
                GUI.timekeepingView.setCurTime2(String.format("%2s", timeSettingValue[2]));
                GUI.timekeepingView.setDate(String.format("%2s", timeSettingValue[3]).substring(2, 4)
                        + String.format("%2s", timeSettingValue[4])
                        + String.format("%2s", timeSettingValue[5]));

                break;
            case 2: // stopwatch
                if (stopwatch.getMode() == 2) {

                    stopwatch.movePointer(-1);
                    String[] tmp = stopwatch.getStopwatchRecord();
                    int curRecordPointer = stopwatch.getRecordPointer();
                    String[] str = new String[3];

                    if (tmp[0 + curRecordPointer] == null)
                        str[0] = "      ";
                    else {
                        StringTokenizer st = new StringTokenizer(tmp[0 + curRecordPointer], " ");
                        str[0] = String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken()));
                    }
                    if (tmp[1 + curRecordPointer] == null)
                        str[1] = "      ";
                    else {
                        StringTokenizer st = new StringTokenizer(tmp[1 + curRecordPointer], " ");
                        str[1] = String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken()));

                    }
                    if (tmp[2 + curRecordPointer] == null)
                        str[2] = "      ";
                    else {
                        StringTokenizer st = new StringTokenizer(tmp[2 + curRecordPointer], " ");
                        str[2] = String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken()));
                    }

                    GUI.stopwatchView.setStopwatchList(str[0] + str[1] + str[2]);
                }
                else {
                    stopwatch.requestResetStopwatch();
                    GUI.stopwatchView.setStopwatch("000000");
                    GUI.stopwatchView.setStopwatchList("  NONE  NONE  NONE");
                }
                break;
            case 3: // timer
                if (timer.getMode() == 1) {
                    timer.changeValue(-1);
                    int[] tsv = timer.getTimeSettingValue();
                    GUI.timerView.setHour(String.format("%02d", tsv[0]));
                    GUI.timerView.setMinute(String.format("%02d", tsv[1]));
                    GUI.timerView.setSecond(String.format("%02d", tsv[2]));
                }
                else {
                    timer.requestResetTimer();
                    GUI.timerView.setHour("00");
                    GUI.timerView.setMinute("00");
                    GUI.timerView.setSecond("00");
                }
                break;
            case 4: // d-day
                if (d_day.getMode() == 0) {
                    // 아무것도 없음.
                }
                else {
                    d_day.changeValue(-1);
                    int[] curDate = d_day.getDateSettingValue();
                    GUI.d_dayView.setYear(String.format("%02d", curDate[0] % 100));
                    GUI.d_dayView.setMonth(String.format("%02d", curDate[1]));
                    GUI.d_dayView.setDate(String.format("%02d", curDate[2]));
                }
                break;
            case 5: // alarm

                break;
            case 6: // alarm custom

                break;
        }

    }

    public void selectBtnPressed() {
        lastOperateTime = java.lang.System.currentTimeMillis();
        if (updateStatus() > -1)
            return;
        switch (selectedFid) {
            case 1: // timekeeping에서 현재시간 설정하는 것
                if (timeKeeping.getMode() == 0) {
                    timeKeeping.requestTimeSettingMode();
                    startCheckTimeOut();
                    GUI.timekeepingView.borderPanel.setVisible(true);
                    GUI.timekeepingView.borderPanel.setBounds(
                            GUI.timekeepingView.curTimePanel1.getX() - 5,
                            GUI.timekeepingView.curTimePanel1.getY() - 5,
                            (GUI.timekeepingView.curTimePanel1.getWidth() + 10) / 2,
                            GUI.timekeepingView.curTimePanel1.getHeight() + 10
                    );
                }
                else {
                    timeKeeping.changeType();
                    int type = timeKeeping.getType();
                    GUI.timekeepingView.borderPanel.setVisible(true);
                    if (type == 0) {
                        GUI.timekeepingView.borderPanel.setBounds(
                                GUI.timekeepingView.curTimePanel1.getX() - 5,
                                GUI.timekeepingView.curTimePanel1.getY() - 5,
                                (GUI.timekeepingView.curTimePanel1.getWidth() + 10) / 2,
                                GUI.timekeepingView.curTimePanel1.getHeight() + 10
                        );
                    }
                    else if (type == 1) {
                        GUI.timekeepingView.borderPanel.setBounds(
                                GUI.timekeepingView.curTimePanel1.getX() - 5 +
                                        (GUI.timekeepingView.curTimePanel1.getWidth() + 10) / 2,
                                GUI.timekeepingView.curTimePanel1.getY() - 5,
                                (GUI.timekeepingView.curTimePanel1.getWidth() + 10) / 2,
                                GUI.timekeepingView.curTimePanel1.getHeight() + 10
                        );
                    }
                    else if (type == 2) {
                        GUI.timekeepingView.borderPanel.setBounds(
                                GUI.timekeepingView.curTimePanel2.getX() - 5,
                                GUI.timekeepingView.curTimePanel2.getY() - 5,
                                GUI.timekeepingView.curTimePanel2.getWidth() + 10,
                                GUI.timekeepingView.curTimePanel2.getHeight() + 10
                        );
                    }
                    else if (type == 3) {
                        GUI.timekeepingView.borderPanel.setBounds(
                                GUI.timekeepingView.datePanel.getX() - 5,
                                GUI.timekeepingView.datePanel.getY() - 5,
                                (GUI.timekeepingView.datePanel.getWidth() + 10) / 3,
                                GUI.timekeepingView.datePanel.getHeight() + 10
                        );
                    }
                    else if (type == 4) {
                        GUI.timekeepingView.borderPanel.setBounds(
                                GUI.timekeepingView.datePanel.getX() - 5
                                        + (GUI.timekeepingView.datePanel.getWidth() + 10) / 3,
                                GUI.timekeepingView.datePanel.getY() - 5,
                                (GUI.timekeepingView.datePanel.getWidth() + 10) / 3,
                                GUI.timekeepingView.datePanel.getHeight() + 10
                        );
                    }
                    else if (type == 5) {
                        GUI.timekeepingView.borderPanel.setBounds(
                                GUI.timekeepingView.datePanel.getX() - 5
                                        + 2 * (GUI.timekeepingView.datePanel.getWidth() + 10) / 3,
                                GUI.timekeepingView.datePanel.getY() - 5,
                                (GUI.timekeepingView.datePanel.getWidth() + 10) / 3,
                                GUI.timekeepingView.datePanel.getHeight() + 10
                        );
                    }
                }
                break;
            case 2: // stopwatch
                if (stopwatch.getMode() == 0) {

                }
                else {
                    stopwatch.requestSaveRecord();
                    String[] tmp = stopwatch.getStopwatchRecord();

                    String[] str = new String[3];
                    if (tmp[0] == null)
                        str[0] = "      ";
                    else {
                        StringTokenizer st = new StringTokenizer(tmp[0], " ");
                        str[0] = String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken()));
                    }
                    if (tmp[1] == null)
                        str[1] = "      ";
                    else {
                        StringTokenizer st = new StringTokenizer(tmp[1], " ");
                        str[1] = String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken()));

                    }
                    if (tmp[2] == null)
                        str[2] = "      ";
                    else {
                        StringTokenizer st = new StringTokenizer(tmp[2], " ");
                        str[2] = String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken())) +
                                String.format("%2d", Integer.parseInt(st.nextToken()));
                    }

                    GUI.stopwatchView.setStopwatchList(str[0] + str[1] + str[2]);
                }

                break;
            case 3: // timer
                if (timer.getMode() == 0) {
                    timer.requestTimerSettingMode();
                    int[] tmp = timer.getTimeSettingValue();
                    GUI.timerView.setHour(String.format("%02d", tmp[0]));
                    GUI.timerView.setMinute(String.format("%02d", tmp[1]));
                    GUI.timerView.setSecond(String.format("%02d", tmp[2]));
                    GUI.timerView.borderPanel.setVisible(true);

                    int w = GUI.timerView.borderPanel.getWidth();
                    int h = GUI.timerView.borderPanel.getHeight();
                    int x = GUI.timerView.borderPanel.getX() % w + 2 * w;
                    int y = GUI.timerView.borderPanel.getY();
                    GUI.timerView.borderPanel.setBounds(x, y, w, h);
                }
                else {
                    timer.changeType();
                    int timerType = timer.getType();
                    int w = GUI.timerView.borderPanel.getWidth();
                    int h = GUI.timerView.borderPanel.getHeight();
                    int x = GUI.timerView.borderPanel.getX() % w + 2 * w;
                    int y = GUI.timerView.borderPanel.getY();

                    if (timerType == 0) {
                        GUI.timerView.borderPanel.setBounds(x, y, w, h);
                    }
                    else if (timerType == 1) {
                        GUI.timerView.borderPanel.setBounds(x + w, y, w, h);
                    }
                    else if (timerType == 2) {
                        GUI.timerView.borderPanel.setBounds(x + 2 * w, y, w, h);
                    }
                }

                break;
            case 4: // d-day
                if (d_day.getMode() == 0) {
                    d_day.requestDdaySettingMode();
                    GUI.d_dayView.borderPanel.setVisible(true);

                    int w = GUI.d_dayView.borderPanel.getWidth();
                    int h = GUI.d_dayView.borderPanel.getHeight();
                    int x = GUI.d_dayView.borderPanel.getX() % w + 2 * w;
                    int y = GUI.d_dayView.borderPanel.getY();
                    GUI.d_dayView.borderPanel.setBounds(x, y, w, h);

                    String curDate = d_day.getD_dayDate().getCurrentDate();
                    StringTokenizer st = new StringTokenizer(curDate, " ");
                    GUI.d_dayView.setYear(String.format("%02d", Integer.parseInt(st.nextToken()) % 100));
                    GUI.d_dayView.setMonth(String.format("%02d", Integer.parseInt(st.nextToken())));
                    GUI.d_dayView.setDate(String.format("%02d", Integer.parseInt(st.nextToken())));
                }
                else {
                    d_day.changeType();
                    int d_dayType = d_day.getType();
                    int w = GUI.d_dayView.borderPanel.getWidth();
                    int h = GUI.d_dayView.borderPanel.getHeight();
                    int x = GUI.d_dayView.borderPanel.getX() % w + 2 * w;
                    int y = GUI.d_dayView.borderPanel.getY();
                    if (d_dayType == 0) {
                        GUI.d_dayView.borderPanel.setBounds(x, y, w, h);
                    }
                    else if (d_dayType == 1) {
                        GUI.d_dayView.borderPanel.setBounds(x + w, y, w, h);
                    }
                    else if (d_dayType == 2) {
                        GUI.d_dayView.borderPanel.setBounds(x + 2 * w, y, w, h);
                    }
                }
                break;
            case 5: // alarm

                break;
            case 6: // alarm custom

                break;
        }

    }

    public void modeBtnPressed() {
        lastOperateTime = java.lang.System.currentTimeMillis();
        if (updateStatus() > -1)
            return;
        switch (selectedFid) {
            case 1: // timekeeping에서 현재시간 설정하는 것
                if (timeKeeping.getMode() == 0) {
                    nextFunction();
                }
                else {
                    // 저장을 하면 d-day를 다시 계산하면 됨.
                    timeKeeping.requestSave();
                    GUI.timekeepingView.borderPanel.setVisible(false);
                    d_day.setDate(d_day.getD_dayDate());

                    GUI.d_dayView.borderPanel.setVisible(false);
                    String curDate = d_day.getD_dayDate().getCurrentDate();
                    StringTokenizer st = new StringTokenizer(curDate, " ");

                    if (d_day.getD_day() == -1) {
                        GUI.d_dayView.setYear("00");
                        GUI.d_dayView.setMonth("NO");
                        GUI.d_dayView.setDate("NE");
                    }
                    else {
                        GUI.d_dayView.setYear(String.format("%02d", Integer.parseInt(st.nextToken()) % 100));
                        GUI.d_dayView.setMonth(String.format("%02d", Integer.parseInt(st.nextToken())));
                        GUI.d_dayView.setDate(String.format("%02d", Integer.parseInt(st.nextToken())));
                    }
                    if (d_day.getD_day() > 999)
                        GUI.d_dayView.setDday("999");
                    else if (d_day.getD_day() == -1) {
                        GUI.d_dayView.setDday("000");
                    }
                    else
                        GUI.d_dayView.setDday(String.format("%03d", d_day.getD_day()));



                    // 바뀐 d-day로 d-day view도 바꾸면 됨.

                }

                break;
            case 2: // stopwatch
                if (stopwatch.getMode() == 0) {
                    nextFunction();
                }

                break;
            case 3: // timer
                if (timer.getMode() == 0) {
                    nextFunction();
                }
                else {
                    timer.requestSave();
                    GUI.timerView.borderPanel.setVisible(false);
                }

                break;
            case 4: // d-day
                if (d_day.getMode() == 0) {
                    GUI.d_dayView.borderPanel.setVisible(false);
                    String curDate = d_day.getD_dayDate().getCurrentDate();
                    StringTokenizer st = new StringTokenizer(curDate, " ");

                    if (d_day.getD_day() == -1) {
                        GUI.d_dayView.setYear("00");
                        GUI.d_dayView.setMonth("NO");
                        GUI.d_dayView.setDate("NE");
                    }
                    else {
                        GUI.d_dayView.setYear(String.format("%02d", Integer.parseInt(st.nextToken()) % 100));
                        GUI.d_dayView.setMonth(String.format("%02d", Integer.parseInt(st.nextToken())));
                        GUI.d_dayView.setDate(String.format("%02d", Integer.parseInt(st.nextToken())));
                    }
                    if (d_day.getD_day() > 999)
                        GUI.d_dayView.setDday("999");
                    else if (d_day.getD_day() == -1)
                        GUI.d_dayView.setDday("000");
                    else
                        GUI.d_dayView.setDday(String.format("%03d", d_day.getD_day()));
                    nextFunction();
                }
                else {
                    d_day.requestSave();
                    GUI.d_dayView.borderPanel.setVisible(false);
                    String curDate = d_day.getD_dayDate().getCurrentDate();
                    StringTokenizer st = new StringTokenizer(curDate, " ");
                    GUI.d_dayView.setYear(String.format("%02d", Integer.parseInt(st.nextToken()) % 100));
                    GUI.d_dayView.setMonth(String.format("%02d", Integer.parseInt(st.nextToken())));
                    GUI.d_dayView.setDate(String.format("%02d", Integer.parseInt(st.nextToken())));
                    if (d_day.getD_day() > 999)
                        GUI.d_dayView.setDday("999");
                    else if (d_day.getD_day() == -1)
                        GUI.d_dayView.setDday("000");
                    else
                        GUI.d_dayView.setDday(String.format("%03d", d_day.getD_day()));

                }

                break;
            case 5: // alarm
                if (alarm.getMode() == 0) {
                    nextFunction();
                }

                break;
            case 6: // alarm custom
                if (alarmCustom.getMode() == 0) {
                    nextFunction();
                }

                break;
        }
    }

    public void cancel(Function curFunction) {
        curFunction.changeMode();
    }

    @Override
    public void changeMode() {

    }

    public void changeType() {
        type = (type + 1) % 6;
    }

    public void changeValue(int diff) {
        // TODO implement here
    }

    public void setFunction(int[] selected) {
        // TODO implement here
    }

    public void selectFunction() {
        // TODO implement here
    }

    public void beepBuzzer() {
        status |= 1;
        buzzer.beepBuzzer();
    }

    public int updateStatus() {
        if (status == 0b11) {
            status = 0b01;
            blink.stopBlink();
            return 2;
        }
        else if (status == 0b10) {
            status = 0b00;
            blink.stopBlink();
            return 1;
        }
        else if (status == 0b01) {
            status = 0b00;
            try {
                buzzer.stopBuzzer();
                buzzer.getBeepThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 0;
        }
        return -1;
    }

    public void set() {
        // TODO implement here
    }

    public void nextFunction() {
        functionNumIdx = (functionNumIdx + 1) % 4;
        selectedFid = functionNum[functionNumIdx];

        switch (selectedFid) {
            case 1:
                GUI.setView(GUI.timekeepingView);
                break;
            case 2:
                GUI.setView(GUI.stopwatchView);
                break;
            case 3:
                GUI.setView(GUI.timerView);
                break;
            case 4:
                GUI.setView(GUI.d_dayView);
                break;
            case 5:
                GUI.setView(GUI.alarmView);
                break;
            case 6:
                GUI.setView(GUI.alarmCustomView);
                break;
        }
    }
}