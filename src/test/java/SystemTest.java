import junit.framework.TestCase;
import org.junit.Test;

public class SystemTest  {

    System system = new System();

    @Test
    public void ChangeScreenTest() {

        system.selectFunction();

        java.lang.System.out.println(system.getSelectedFid());
        system.nextFunction();
        java.lang.System.out.println(system.getSelectedFid());
        system.nextFunction();
        java.lang.System.out.println(system.getSelectedFid());
        system.nextFunction();
        java.lang.System.out.println(system.getSelectedFid());
        system.nextFunction();
        java.lang.System.out.println(system.getSelectedFid());
        system.nextFunction();
        java.lang.System.out.println(system.getSelectedFid());
        system.nextFunction();
        java.lang.System.out.println(system.getSelectedFid());

    }

    @Test
    public  void TimeoutTest() {

        TimeKeeping timeKeeping = new TimeKeeping(system);
        Stopwatch stopwatch = new Stopwatch(system);
        Timer timer = new Timer(system);
        D_day d_day = new D_day(system);
        Alarm alarm = new Alarm(system);
        AlarmCustom alarmCustom = new AlarmCustom(system);


        java.lang.System.out.println(system.getSelectedFid());
        timeKeeping.requestTimeSettingMode();
        system.startCheckTimeOut();
        // (임시로 10분을 10초로 바꾸고) 10초 정지 후 어떻게 되는 지 확인
        system.getMode();


        java.lang.System.out.println(system.getSelectedFid());
        stopwatch.requestRecordCheckMode();
        system.startCheckTimeOut();

        java.lang.System.out.println(system.getSelectedFid());
        timer.requestTimerSettingMode();
        system.startCheckTimeOut();

        java.lang.System.out.println(system.getSelectedFid());
        d_day.requestDdaySettingMode();
        system.startCheckTimeOut();


        java.lang.System.out.println(system.getSelectedFid());
        alarm.requestAlarmSettingMode();
        system.startCheckTimeOut();

        java.lang.System.out.println(system.getSelectedFid());
        alarmCustom.requestIntervalSettingMode();
        system.startCheckTimeOut();

    }

    @Test
    public void CancelTest() {
        TimeKeeping timeKeeping = new TimeKeeping(system);
        Stopwatch stopwatch = new Stopwatch(system);
        Timer timer = new Timer(system);
        D_day d_day = new D_day(system);
        Alarm alarm = new Alarm(system);
        AlarmCustom alarmCustom = new AlarmCustom(system);

        //타임 키핑
        timeKeeping.requestTimeSettingMode();
        system.cancel(timeKeeping);
        java.lang.System.out.println(timeKeeping.getMode());

        // 스톱워치
        stopwatch.requestRecordCheckMode();
        system.cancel(stopwatch);
        java.lang.System.out.println(stopwatch.getMode());

        //타이머
        timer.requestTimerSettingMode();
        system.cancel(timer);
        java.lang.System.out.println(timer.getMode());

        //디데이
        d_day.requestDdaySettingMode();
        system.cancel(d_day);
        java.lang.System.out.println(d_day.getMode());

        // 알람
        alarm.requestAlarmSettingMode();
        system.cancel(alarm);
        java.lang.System.out.println(alarm.getMode());

        // 알람 커스텀
        alarmCustom.requestIntervalSettingMode();
        system.cancel(alarmCustom);
        java.lang.System.out.println(alarmCustom.getMode());

        alarmCustom.requestAlarmVolumeMode();
        system.cancel(alarmCustom);
        java.lang.System.out.println(alarmCustom.getMode());

        alarmCustom.requestAlarmSelectMode();
        system.cancel(alarmCustom);
        java.lang.System.out.println(alarmCustom.getMode());


    }

}