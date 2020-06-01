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
        alarmList = new AlarmData[10];
        mode = 3;
        state = false;
        alarmPointer = 0;
    }

    AlarmData curAlarm ;
    AlarmData[] alarmList;

    private int alarmPointer;
    private int mode;

    private boolean state = false;

    private int typeindex = 1; // 시분초 구분

    /*
    * 아래 조건문 전부 pressBtn 상태에 따라 다 바꿔야함.
    * */

    /**
     * 
     */
    public void requestAlarmSettingMode() {
        // TODO implement here
        changeMode();


        while(true)
        {
            if(true) // type : Select 버튼
            {
                changeType(); // 시분초
            }
            else if(true) // Start Btn : +
            {
                changeValue(1);
                if(typeindex == 1 && curAlarm.alarmTime.hour > 23)
                {
                    curAlarm.alarmTime.hour = 23;
                }
                else if (typeindex == 2 && curAlarm.alarmTime.min > 59)
                {
                    curAlarm.alarmTime.min = 59;
                }
                else if (typeindex == 3 && curAlarm.alarmTime.sec > 59)
                {
                    curAlarm.alarmTime.sec = 59;
                }

            }
            else if(true) // reset Btn : -
            {
                changeValue(-1);
                if(typeindex == 1 && curAlarm.alarmTime.hour < 0)
                {
                    curAlarm.alarmTime.hour = 0;
                }
                else if (typeindex == 2 && curAlarm.alarmTime.min < 0)
                {
                    curAlarm.alarmTime.min = 0;
                }
                else if (typeindex == 3 && curAlarm.alarmTime.sec < 0)
                {
                    curAlarm.alarmTime.sec = 0;
                }

            }
            else if(true) // Mode 버튼 : 저장
            {
                break;
            }
        }

        requestSave();

    }


    /**
     * 
     */
    public void requestSave() {
        // TODO implement here

        // AlarmData alarmData = new AlarmData();
        //alarmData.getTime();

        addTimeToAlarmList(curAlarm.alarmTime);

    }

    /**
     * @param alarmTime
     */
    public void addTimeToAlarmList(Time alarmTime) {
        // TODO implement here

        int count = 0;

        for (int i  =0 ; i < 10 ; i++)
        {
            count++;
            if(alarmList[i] == null)
            {

                break;
            }


        }

        if (count >= 10) {
            // 개수 초과
            return;
        }
        else {
            curAlarm.alarmTime = alarmTime;
            alarmList[count] = curAlarm;

        }

        changeMode();

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
        alarmList[alarmIdx] = null;
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
    public void changeMode() {
        if(this.state)
        {
            this.state = false;
        } else{
            this.state = true;
        }
    }

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