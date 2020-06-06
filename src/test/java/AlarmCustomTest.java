
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class AlarmCustomTest {
    System system = new System();


    @Test
    public void ControlAlarmListTest() {
        AlarmCustom alarmCustom = new AlarmCustom(system);

        alarmCustom.requestAlarmSelectMode();
        java.lang.System.out.println(alarmCustom.getAlarm().getAlarmPointer());


        // alarm 리스트 설정
        Time time = new Time(2);
        time.setTime(1,2,3);
        alarmCustom.getAlarm().addTimeToAlarmList(time);
        time.setTime(1,2,3);
        alarmCustom.getAlarm().addTimeToAlarmList(time);
        time.setTime(1,2,3);
        alarmCustom.getAlarm().addTimeToAlarmList(time);

        //선택 모드 진입
        alarmCustom.requestAlarmSelectMode();

        // 알람 리스트 잘 확인 되는지 확인.
        alarmCustom.getAlarm().movePointer(1);
        java.lang.System.out.println(alarmCustom.getAlarm().getAlarmPointer());

        alarmCustom.getAlarm().movePointer(1);
        java.lang.System.out.println(alarmCustom.getAlarm().getAlarmPointer());

        alarmCustom.getAlarm().movePointer(1);
        java.lang.System.out.println(alarmCustom.getAlarm().getAlarmPointer());

        alarmCustom.getAlarm().movePointer(-1);
        java.lang.System.out.println(alarmCustom.getAlarm().getAlarmPointer());

        alarmCustom.getAlarm().movePointer(-1);
        java.lang.System.out.println(alarmCustom.getAlarm().getAlarmPointer());

        alarmCustom.getAlarm().movePointer(-1);
        java.lang.System.out.println(alarmCustom.getAlarm().getAlarmPointer());

    }

    @Test
    public void SetAlarmIntervalTest() {
        AlarmCustom alarmCustom = new AlarmCustom(system);
        Time time = new Time(2);
        time.setTime(1,2,3);
        system.alarm.addTimeToAlarmList(time);
        alarmCustom.requestAlarmSelectMode();
        alarmCustom.getAlarm().movePointer(1);
        alarmCustom.requestIntervalSettingMode();

        alarmCustom.changeType();

        //인터벌이 범위 내에서 잘 바뀌는 지 확인.
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

        Time time = new Time(2);
        time.setTime(1,1,2);

        system.alarm.addTimeToAlarmList(time);
        alarmCustom.getAlarm().movePointer(1);
        alarmCustom.changeValue(1);
        alarmCustom.changeType();
        alarmCustom.changeValue(1);
        alarmCustom.changeType();
        alarmCustom.requestVolumeSettingMode();



        // 범위에 맞게 볼륨값이 잘 바뀌는 지 확인
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

        // 저장이 잘 되는지 확인
        alarmCustom.setCustom();

        java.lang.System.out.println(system.alarm.getAlarmList()[0].getInterval());
        java.lang.System.out.println(system.alarm.getAlarmList()[0].getVolume());

    }




}