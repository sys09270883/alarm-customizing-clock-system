import java.util.ArrayList;

/**
 * @author Yoonseop Shin
 */
public class Alarm extends Function {

    /**
     * Default constructor
     */
    public Alarm() {  // 초기
        curAlarm  = new AlarmData();
        alarmList = new ArrayList<AlarmData>();
        mode = 3;
        state = 0;
        alarmPointer = 0;
    }

    AlarmData curAlarm ;
    ArrayList<AlarmData> alarmList;

    private int alarmPointer;
    private int mode;
    private int state = 0;

    private int typeindex = 1; // 시분초 구분


    /**
     * 
     */
    public void requestAlarmSettingMode() {
        // TODO implement here
        changeMode();
        requestAlarmSelectMode();

    }


    /**
     * 
     */
    public void requestSave() {
        // TODO implement here
        //addTimeToAlarmList(curAlarm.);

    }

    /**
     * @param alarmTime
     */
    public void addTimeToAlarmList(Time alarmTime) {
        // TODO implement here

        if (alarmList.size() >= 10) {
            // 개수 초과되었다고 오류 메시지ㅣ 출력
            return;
        }
        else {
            curAlarm.alarmTime = alarmTime;
            alarmList.add(curAlarm);

        }

    }

    /**
     * 
     */
    public void requestDeleteAlarm() {
        // TODO implement here
        deleteAlarm(alarmPointer);
    }

    /**
     * @param alarmIdx
     */
    public void deleteAlarm(int alarmIdx) {
        // TODO implement here
        alarmList.set(alarmIdx, null);
    }

    /**
     * 
     */
    public void requestStopAlarmBuzzer() {
        // TODO implement here
        Buzzer buzzer = new Buzzer();
        buzzer.stopBuzzer();
    }

    /**
     * @param diff
     */
    public void movePointer(int diff, int pointer) {
        // TODO implement here
        pointer += diff;
    }

    /**
     * 
     */
    public void requestAlarmSelectMode() {
        // TODO implement here
        changeMode();

        while(true)
        {
            //start(+)
            if (true) {
                movePointer(1, alarmPointer);
            }
            //reset(-)
            else if(true){
                movePointer(-1, alarmPointer);
            }

            if(true) // 끝나면
                break;
        }

    }

    /**
     * 
     */
    public void timeout() {}


    /**
     * 
     */
    public void cancel() {}

    /**
     * 
     */
    @Override
    public void changeMode() {state = 1;}

    @Override
    public void changeValue(int diff) {
        if (typeindex == 1)
        {
            curAlarm.alarmTime.hour += diff;
        }
        else if (typeindex == 2)
        {
            curAlarm.alarmTime.min += diff;
        }
        else if(typeindex == 3)
        {
            curAlarm.alarmTime.sec += diff;
        }


    }



    /**
     * 
     */
    public void changeType() {

        typeindex++;

        if(typeindex == 4)
        {
            typeindex = 1;
        }

    }

}