
/**
 * @author Yoonseop Shin
 */
public class Alarm extends Function {

    final static int FID = 5;
    final static int ALARM_POINTER_MAX = 9;
    /**
     * Default constructor
     */
    public Alarm(System system) {
        fid = 5;
        curAlarm = new AlarmData();
        alarmList = new AlarmData[10];
        mode = 0; // 기본 모드
        segmentPointer = new int[2];

        typeindex = 0;


    }

    private AlarmData curAlarm;

    private AlarmData[] alarmList;




    private int alarmPointer;
    public int[] segmentPointer;
    private int mode;
    private int typeindex; // 시분초 구분
    private int alarmSettingValue[] = {-1,-1,-1};


    public int[] getAlarmSettingValue() {
       return this.alarmSettingValue;
    }


    /**
     * 
     */
    public void requestAlarmSettingMode() {
        // TODO implement here
        changeMode(1);
    }

    /**
     * 
     */
    public void requestSave() {
        // TODO implement here
        Time time = new Time(2);
        time.setTime(alarmSettingValue[0],alarmSettingValue[1], alarmSettingValue[2]);
        this.curAlarm.setAlarmTime(time);

        //this.curAlarm.setAlarmTime(time);
        //this.curAlarm.setAlarmTime();
        addTimeToAlarmList(time);
        changeMode(0);

    }

    /**
     * @param alarmTime
     */
    public void addTimeToAlarmList(Time alarmTime) {
        // TODO implement here
        int size = getSize();
        if(size >= 10)
        {
            // 꽉 찼으니 저장 안함.
        } else {
            Time newTime = new Time(0);
            newTime.setTime(0, 0, 0);
            curAlarm.setAlarmTime(newTime);

            if (alarmList[size] == null)
                alarmList[size] = new AlarmData();
            alarmList[size].setAlarmTime(alarmTime);
        }

    }

    /**
     * 
     */
    public void requestDeleteAlarm() {
        // TODO implement here
        deleteAlarm(this.alarmPointer);
        changeMode(0);
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * @param alarmIdx
     */

    public void deleteAlarm(int alarmIdx) {
        // TODO implement here
        //delete하면 그 사이를 이어붙임.
        int i;
        for(i = alarmIdx; i < 9 ; i++)
        {
            if (alarmList[i + 1] == null)
                break;
            this.alarmList[i] = this.alarmList[i+1];
        }
        alarmList[i] = null;
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
    public void movePointer(int diff) {
        // TODO implement here
        int size = this.getSize();

        if (size == 0)
        {
            return;
        }

        this.alarmPointer += diff;


        if(this.alarmPointer > size - 1)
        {
            this.alarmPointer = size - 1;
        } else if (this.alarmPointer < 0)
        {
            this.alarmPointer = 0;
        }
    }

    /**
     * 
     */
    public void requestAlarmSelectMode() {
        // TODO implement here
        segmentPointer[0] = 0;
        segmentPointer[1] = Math.min(2, getSize() - 1);
        changeMode(2);
    }

    /**
     * 
     */
    public void timeout() {}

    public void setAlarmPointer(int alarmPointer) {
        this.alarmPointer = alarmPointer;
    }

    /**
     * 
     */
    public void cancel() {

        mode = 0;
        changeMode();
    }

    /**
     * 
     */


    public void changeMode(int mode) { // 기본 화면, 알람 리스트 확인, 알람 설정 이렇게 3개가 있음.
        this.mode = mode;
        if(this.mode == 1) // 알람 설정
        {
            // 알람 설정값을 00 : 00 : 00 으로 초기화
            alarmSettingValue[0] = 0;
            alarmSettingValue[1] = 0;
            alarmSettingValue[2] = 0;


        } else if (this.mode == 2){ // 알람 리스트 확인.
            // 포인터를 0으로 초기화
            alarmPointer = 0;


        } else { // 기본 화면
            // alarmSettingValue -1로 비활성화
            for(int i=0; i< 3; ++i)
                alarmSettingValue[i] = -1;

            typeindex = 0;
            alarmPointer = 0;
        }

    }

    public void changeValue2(int diff) {
        alarmPointer += diff;

        if (alarmPointer > getSize() - 1)
            alarmPointer = getSize() - 1;
        if (alarmPointer < 0)
            alarmPointer = 0;

        if (alarmPointer > segmentPointer[1]) {
            segmentPointer[0]++;
            segmentPointer[1]++;
        }
        else if (alarmPointer < segmentPointer[0]) {
            segmentPointer[0]--;
            segmentPointer[1]--;
        }
    }

    public int[] getSegmentPointer() {
        return this.segmentPointer;
    }

    public void setSegmentPointer(int[] segmentPointer) {
        this.segmentPointer = segmentPointer;
    }


    /**
     * @param diff
     */
    public void changeValue(int diff) {
        alarmSettingValue[typeindex] += diff;

        // 각 type 값 검사
        switch(typeindex) {
            case 0 :
                if(alarmSettingValue[typeindex] < curAlarm.getTime().TIME_BOTTOM_LIMIT)
                    alarmSettingValue[typeindex] = curAlarm.getTime().TIME_BOTTOM_LIMIT;
                else if(alarmSettingValue[typeindex] > curAlarm.getTime().HOUR_TOP_LIMIT)
                    alarmSettingValue[typeindex] = curAlarm.getTime().HOUR_TOP_LIMIT;
                break;
            case 1 :
                if(alarmSettingValue[typeindex] < curAlarm.getTime().TIME_BOTTOM_LIMIT)
                    alarmSettingValue[typeindex] = curAlarm.getTime().TIME_BOTTOM_LIMIT;
                else if(alarmSettingValue[typeindex] > curAlarm.getTime().MINUTE_TOP_LIMIT)
                    alarmSettingValue[typeindex] = curAlarm.getTime().MINUTE_TOP_LIMIT;
                break;
            case 2 :
                if(alarmSettingValue[typeindex] < curAlarm.getTime().TIME_BOTTOM_LIMIT)
                    alarmSettingValue[typeindex] = curAlarm.getTime().TIME_BOTTOM_LIMIT;
                else if(alarmSettingValue[typeindex] > curAlarm.getTime().SECOND_TOP_LIMIT)
                    alarmSettingValue[typeindex] = curAlarm.getTime().SECOND_TOP_LIMIT;
                break;

        }
    }

    /**
     * 
     */
    public void changeType() {
        typeindex = (typeindex + 1) % 3; // 시(0), 분(1), 초(2) 타입 변경.
    }

    public int getMode() {
        return this.mode;
    }

    public int getType() {
        return typeindex;
    }

    public int getSize() {
        int cnt = 0;
        for (AlarmData alarmData : alarmList) {
            if (alarmData != null)
            {
                cnt++;

            }
        }
        return cnt;
    }

    public int getAlarmPointer() {
        return this.alarmPointer;
    }

    public AlarmData[] getAlarmList(){
        return this.alarmList;

    }

}