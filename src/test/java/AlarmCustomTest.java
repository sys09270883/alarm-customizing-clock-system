
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class AlarmCustomTest {
    System system = new System();


    @Test
    public void ControlAlarmListTest() {
        AlarmCustom alarmCustom = new AlarmCustom(system);

        alarmCustom.requestAlarmSelectMode();
        java.lang.System.out.println(alarmCustom.alarm.getAlarmPointer());


        Time time = new Time(2);
        time.setTime(1,2,3);
        alarmCustom.alarm.addTimeToAlarmList(time);
        time.setTime(1,2,3);
        alarmCustom.alarm.addTimeToAlarmList(time);
        time.setTime(1,2,3);
        alarmCustom.alarm.addTimeToAlarmList(time);

        alarmCustom.requestAlarmSelectMode();

        alarmCustom.alarm.movePointer(1);
        java.lang.System.out.println(alarmCustom.alarm.getAlarmPointer());

        alarmCustom.alarm.movePointer(1);
        java.lang.System.out.println(alarmCustom.alarm.getAlarmPointer());

        alarmCustom.alarm.movePointer(1);
        java.lang.System.out.println(alarmCustom.alarm.getAlarmPointer());

        alarmCustom.alarm.movePointer(-1);
        java.lang.System.out.println(alarmCustom.alarm.getAlarmPointer());

        alarmCustom.alarm.movePointer(-1);
        java.lang.System.out.println(alarmCustom.alarm.getAlarmPointer());

        alarmCustom.alarm.movePointer(-1);
        java.lang.System.out.println(alarmCustom.alarm.getAlarmPointer());

    }

    @Test
    public void SetAlarmIntervalTest() {
        AlarmCustom alarmCustom = new AlarmCustom(system);
        alarmCustom.requestIntervalSettingMode();


        alarmCustom.changeValue(1);
        java.lang.System.out.println(alarmCustom.getCustomSettingValue()[1]);

        alarmCustom.changeValue(1);
        java.lang.System.out.println(alarmCustom.getCustomSettingValue()[1]);

        alarmCustom.changeValue(1);
        java.lang.System.out.println(alarmCustom.getCustomSettingValue()[1]);

        alarmCustom.changeValue(-1);
        java.lang.System.out.println(alarmCustom.getCustomSettingValue()[1]);

        alarmCustom.changeValue(-1);
        java.lang.System.out.println(alarmCustom.getCustomSettingValue()[1]);

        alarmCustom.changeValue(-1);
        java.lang.System.out.println(alarmCustom.getCustomSettingValue()[1]);

        alarmCustom.changeValue(-1);
        java.lang.System.out.println(alarmCustom.getCustomSettingValue()[1]);

        //assert();
    }

    @Test
    public void SetAlarmVolumeTest() {
        AlarmCustom alarmCustom = new AlarmCustom(system);
        alarmCustom.requestAlarmVolumeMode();

        alarmCustom.changeValue(1);
        java.lang.System.out.println(alarmCustom.getCustomSettingValue()[2]);

        alarmCustom.changeValue(1);
        java.lang.System.out.println(alarmCustom.getCustomSettingValue()[2]);

        alarmCustom.changeValue(1);
        java.lang.System.out.println(alarmCustom.getCustomSettingValue()[2]);


        alarmCustom.changeValue(-1);
        java.lang.System.out.println(alarmCustom.getCustomSettingValue()[2]);

        alarmCustom.changeValue(-1);
        java.lang.System.out.println(alarmCustom.getCustomSettingValue()[2]);


        alarmCustom.changeValue(-1);
        java.lang.System.out.println(alarmCustom.getCustomSettingValue()[2]);
    }




}