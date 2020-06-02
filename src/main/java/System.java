import java.util.Arrays;

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
    int functionNumIdx = 0;

    public System() {
        GUI = new MainFrame(this);

        functionNum = new int[4];
        Arrays.fill(functionNum, 0);
        functionNum[0] = 1;
        functionNum[1] = 2;
        functionNum[2] = 5;
        functionNum[3] = 6;
        status = 0b00;
        type = 0;
        selectedFid = 1;

        timeKeeping = new TimeKeeping(this);
        stopwatch = new Stopwatch(this);
        timer = new Timer(this);
        d_day = new D_day(this);
        alarm = new Alarm(this);
        alarmCustom = new AlarmCustom();

        buzzer = new Buzzer();
        blink = new Blink();

    }

    public static void main(String[] args) {
        System system = new System();


    }

    public int checkStatus() {
        if (status == 3) {
            status = 1;
            return 2;
        }
        else if (status == 2) {
            status = 0;
            return 1;
        }
        else if (status == 1) {
            status = 0;
            return 0;
        }
        return -1;
    }

    public void startBtnPressed() {
        if (checkStatus() > -1)
            return;
        switch (selectedFid) {
            case 1: // timekeeping에서 현재시간 설정하는 것
                if (timeKeeping.getMode() == 0)
                    return;
                // GUI에 반영해야 함.
                timeKeeping.changeValue(1);
                type = timeKeeping.getType();
                int[] timeSettingValue = timeKeeping.getTimeSettingValue();
                GUI.timekeepingView.setHour(String.format("%2s", timeSettingValue[0]));
                GUI.timekeepingView.setMinute(String.format("%2s", timeSettingValue[1]));
                GUI.timekeepingView.setCurTime2(String.format("%2s", timeSettingValue[2]));
                GUI.timekeepingView.setDate(String.format("%2s", timeSettingValue[3]).substring(2, 4)
                        + String.format("%2s", timeSettingValue[4])
                        + String.format("%2s", timeSettingValue[5]));
                // GUI type에 해당하는 부분이 깜빡이는 효과를 추가해야 함.

                break;
            case 2: // stopwatch

                break;
            case 3: // timer

                break;
            case 4: // d-day

                break;
            case 5: // alarm
                if (alarm.getMode() == 0)
                    return;
                // GUI에 반영해야 함.
                else if (alarm.getMode() == 1) { // 알람 설정
                    alarm.changeValue(1);
                    type = alarm.getType();
                    int[] alarmSettingValue = alarm.getAlarmSettingValue();
                    String str1 = "00", str2 = "00", str3 = "00";
                    if(alarmSettingValue[0] < 10) {
                        str1 = "0" + String.format("%1s", alarmSettingValue[0]);
                    } else {
                        str1 = String.format("%2s", alarmSettingValue[0]);
                    }
                    if (alarmSettingValue[1] < 10)
                    {
                        str2 = "0" + String.format("%1s", alarmSettingValue[1]);
                    }else {
                        str2 = String.format("%2s", alarmSettingValue[1]);
                    }if (alarmSettingValue[2] < 10)
                    {
                        str3 = "0" + String.format("%1s", alarmSettingValue[2]);
                    }else {
                        str3 = String.format("%2s", alarmSettingValue[2]);
                    }

                    GUI.alarmView.setAlarm(str1 + str2  + str3);
                    GUI.alarmView.setAlarmList(alarm.getAlarmList(), alarm.getAlarmPointer(), alarm.getSize());

                } else if (alarm.getMode() == 2) // 포인터 조종
                {
                    alarm.movePointer(1);


                }
                // GUI type에 해당하는 부분이 깜빡이는 효과를 추가해야 함.
                break;
            case 6: // alarm custom
                if (alarmCustom.getMode() == 0) // 알람 포인터
                {
                    alarm.movePointer(1);
                    GUI.alarmView.setAlarmList(alarm.getAlarmList(), alarm.getAlarmPointer(), alarm.getSize());
                    java.lang.System.out.println(alarm.getAlarmPointer());
                }
                // GUI에 반영해야 함.
                else if (alarmCustom.getMode() == 1) { // 알람 간격 설정
                    alarmCustom.changeValue(1);
                    int setInterval = alarmCustom.getInterval();
                    String str1 = "00";
                    if(setInterval < 10) {
                        str1 = String.format("%1s", setInterval);
                    }

                    GUI.alarmCustomView.setAlarmInterval(str1);
                    GUI.alarmCustomView.setAlarmList(alarm.getAlarmList(), alarm.getAlarmPointer(), alarm.getSize());
                } else if (alarmCustom.getMode() == 2) // 알람 볼륨 설정
                {
                    alarmCustom.changeValue(1);
                    int setVolume = alarmCustom.getVolume();
                    String str2= "0";
                    str2 = String.format("%1s", setVolume);

                    //java.lang.System.out.println(str2);
                    GUI.alarmCustomView.setAlarmVolume(str2);
                   // GUI.alarmCustomView.setAlarmList(alarm.getAlarmList(), alarm.getAlarmPointer(), alarm.getSize());

                }
                break;
        }

    }

    public void resetBtnPressed() {
        if (checkStatus() > -1)
            return;
        switch (selectedFid) {
            case 1: // timekeeping에서 현재시간 설정하는 것
                if (timeKeeping.getMode() == 0)
                    return;
                // GUI에 반영해야 함.
                timeKeeping.changeValue(-1);
                int type = timeKeeping.getType();
                int[] timeSettingValue = timeKeeping.getTimeSettingValue();
                GUI.timekeepingView.setHour(String.format("%2s", timeSettingValue[0]));
                GUI.timekeepingView.setMinute(String.format("%2s", timeSettingValue[1]));
                GUI.timekeepingView.setCurTime2(String.format("%2s", timeSettingValue[2]));
                GUI.timekeepingView.setDate(String.format("%2s", timeSettingValue[3]).substring(2, 4)
                        + String.format("%2s", timeSettingValue[4])
                        + String.format("%2s", timeSettingValue[5]));
                // GUI type에 해당하는 부분이 깜빡이는 효과를 추가해야 함.

                break;
            case 2: // stopwatch

                break;
            case 3: // timer

                break;
            case 4: // d-day

                break;
            case 5: // alarm
                if (alarm.getMode() == 0)
                    return;
                    // GUI에 반영해야 함.
                else if (alarm.getMode() == 1) { // 알람 설정
                    alarm.changeValue(-1);
                    type = alarm.getType();
                    int[] alarmSettingValue = alarm.getAlarmSettingValue();
                    String str1 = "00", str2 = "00", str3 = "00";
                    if(alarmSettingValue[0] < 10) {
                        str1 = "0" + String.format("%1s", alarmSettingValue[0]);
                    } else {
                        str1 = String.format("%2s", alarmSettingValue[0]);
                    }
                    if (alarmSettingValue[1] < 10)
                    {
                        str2 = "0" + String.format("%1s", alarmSettingValue[1]);
                    }else {
                        str2 = String.format("%2s", alarmSettingValue[1]);
                    }if (alarmSettingValue[2] < 10)
                    {
                        str3 = "0" + String.format("%1s", alarmSettingValue[2]);
                    }else {
                        str3 = String.format("%2s", alarmSettingValue[2]);
                    }

                    GUI.alarmView.setAlarm(str1 + str2  + str3);
                    GUI.alarmView.setAlarmList(alarm.getAlarmList(), alarm.getAlarmPointer(), alarm.getSize());
                } else if (alarm.getMode() == 2) // 포인터 조종
                {
                    alarm.movePointer(-1);
                    GUI.alarmView.setAlarmList(alarm.getAlarmList(), alarm.getAlarmPointer(), alarm.getSize());
                }
                break;
            case 6: // alarm custom
                if (alarmCustom.getMode() == 0) // 알람 포인터
                {
                    alarm.movePointer(-1);
                    GUI.alarmView.setAlarmList(alarm.getAlarmList(), alarm.getAlarmPointer(), alarm.getSize());
                }
                    // GUI에 반영해야 함.
                else if (alarmCustom.getMode() == 1) { // 알람 간격 설정
                    alarmCustom.changeValue(-1);
                    int setInterval = alarmCustom.getInterval();
                    String str1 = "00";
                    if(setInterval < 10) {
                        str1 = String.format("%1s", setInterval);
                    }

                    GUI.alarmCustomView.setAlarmInterval(str1);
                //    GUI.alarmCustomView.setAlarmList(alarm.getAlarmList(), alarm.getAlarmPointer(), alarm.getSize());
                } else if (alarmCustom.getMode() == 2) // 알람 볼륨 설정
                {
                    alarmCustom.changeValue(-1);
                    int setVolume = alarmCustom.getVolume();
                    String str2= "0";
                    if(setVolume < 10)
                    {
                        str2 = String.format("%1s", setVolume);
                    }
                    GUI.alarmCustomView.setAlarmVolume(str2);

                }
                break;
        }


    }

    public void selectBtnPressed() {
        if (checkStatus() > -1)
            return;
        switch (selectedFid) {
            case 1: // timekeeping에서 현재시간 설정하는 것
                if (timeKeeping.getMode() == 0) {
                    timeKeeping.changeMode();
                }
                else {
                    timeKeeping.changeType();
                }


                break;
            case 2: // stopwatch

                break;
            case 3: // timer

                break;
            case 4: // d-day

                break;
            case 5: // alarm
                if(alarm.getMode() == 0){
                    alarm.requestAlarmSettingMode();
                } else if (alarm.getMode() == 1) {
                    alarm.changeType();
                    //타입 바뀔 때마다 항목 표시

                } else { // 알람 리스트 확인
                    alarm.requestDeleteAlarm();
                    
                }

                break;
            case 6: // alarm custom
                if(alarmCustom.getMode() == 0){ // 항목 선택 시
                    //String str = String.format("%1s", alarm.getAlarmList()[alarm.getAlarmPointer()].alarmCustom.getVolume());
                    //GUI.alarmCustomView.setAlarmVolume(str);
                    alarmCustom.requestIntervalSettingMode();
                    //alarmCustom.requestAlarmSelectMode(alarm);
                } else if (alarmCustom.getMode() == 1) { // 인터벌 설정 끝날때
                    alarmCustom.requestAlarmVolumeMode();

                } else if (alarmCustom.getMode() == 2) {

                    //alarmCustom.requestSave();
                }else {//타입 바뀔 때마다 항목 표시
                    //alarmCustom.changeType();
                }
                break;
        }

    }

    public void modeBtnPressed() {
        if (checkStatus() > -1)
            return;
        switch (selectedFid) {
            case 1: // timekeeping에서 현재시간 설정하는 것
                if (timeKeeping.getMode() == 0) {
                    changeScreen();
                }
                else {
                    timeKeeping.requestSave();
                    // TODO 시간 저장 후 타임쓰레드에 문제있음.
                    timeKeeping.changeMode();
                }

                break;
            case 2: // stopwatch
                if (stopwatch.getMode() == 0) {
                    changeScreen();
                }

                break;
            case 3: // timer
                if (timer.getMode() == 0) {
                    changeScreen();
                }

                break;
            case 4: // d-day
                if (d_day.getMode() == 0) {
                    changeScreen();
                }

                break;
            case 5: // alarm
                if (alarm.getMode() == 0) {
                    changeScreen();
                }else if(alarm.getMode() == 1){
                    alarm.requestSave();

                    GUI.alarmView.setAlarmList(alarm.getAlarmList(), alarm.getAlarmPointer(), alarm.getSize());

                    alarm.changeMode();
                } else {
                    alarm.cancel();
                }

                break;
            case 6: // alarm custom
                if (alarmCustom.getMode() == 0) {
                    changeScreen();
                } else if (alarmCustom.getMode() == 1)
                {
                    //alarmCustom.requestAlarmVolumeMode();
                } else  if(alarmCustom.getMode() == 2)
                {
                    alarmCustom.requestSave();
                }

                break;
        }
    }


    public void changeScreen() {
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

    @Override
    public void timeout() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void changeMode() {

    }

    /**
     * 
     */
    private int[] functionNum;

    /**
     * 
     */
    private int selectedFid;

    /**
     * 비트마스킹: 0b00 0b01 0b10 0b11
     */
    private int status;



    /**
     * 수정할 인덱스: 연, 월, 일, 시, 분, 초 [0, 5]
     */
    private int type;

    /**
     * 
     */
    private Buzzer buzzer;

    /**
     * 
     */
    private Blink blink;





    /**
     * 
     */
    public void changeType() {
        type = (type + 1) % 6;
    }

    /**
     * @param diff: +1 or -1
     */
    public void changeValue(int diff) {
        // TODO implement here
    }

    /**
     * @param selected
     */
    public void setFunction(int[] selected) {
        // TODO implement here
    }

    /**
     * 
     */
    public void selectFunction() {
        // TODO implement here
    }

    /**
     * 
     */
    public void beepBuzzer() {
        // TODO implement here
    }

    /**
     * 
     */
    public void updateStatus() {
        // TODO implement here
    }

    /**
     * 
     */
    public void set() {
        // TODO implement here
    }

    /**
     * 
     */
    public void nextFunction() {
        // TODO implement here
    }
}