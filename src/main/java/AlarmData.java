
/**
 * @author Yoonseop Shin
 */
public class AlarmData {

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * Default constructor
     */
    private int interval;

    public int getVolume() {
        return volume;
    }

    private int volume;

    public AlarmData() {
        alarmTime = new Time(2); // 증가도 감소도 아니라 의미 없게 2로 지정.

        interval = 2;
        volume = 2;

        alarmTime.setTime(-1,-1,-1);

    }

    public Time getAlarmTime() {
        return alarmTime;
    }

    /**
     * 
     */
    private Time alarmTime;


    private AlarmCustom alarmCustom;



    public Time getTime(){
        return this.alarmTime;
    }

    public AlarmCustom getAlarmCustom() {
        return this.alarmCustom;
    }

    public void setAlarmTime(Time settingTime) {
        this.alarmTime = settingTime;
    }

    public void setAlarmCustom(AlarmCustom settingCustom) {
        this.alarmCustom = settingCustom;
    }



}