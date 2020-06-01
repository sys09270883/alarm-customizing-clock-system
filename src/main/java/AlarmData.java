
/**
 * @author Yoonseop Shin
 */
public class AlarmData {

    /**
     * Default constructor
     */
    public AlarmData() {
        alarmTime = new Time(2); // 증가도 감소도 아니라 의미 없게 2로 지정.
        alarmCustom = new AlarmCustom();
    }

    /**
     * 
     */
    private Time alarmTime;

    private AlarmCustom alarmCustom;

    /**
     * 
     *///


    public Time getTime(){
        return this.alarmTime;
    }

    public AlarmCustom getAlarmCustom() {
        return this.alarmCustom;
    }



}