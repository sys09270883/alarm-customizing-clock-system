import junit.framework.TestCase;
import org.junit.Test;

public class SystemTest  {

    System system = new System();

    @Test
    public void ChangeScreenTest() {

        system.selectFunction();

        // 화면이 잘 넘어가는지 확인 : 1,2,5,6  설정순
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

        // 타임 키핑
        java.lang.System.out.println(system.getSelectedFid());
        timeKeeping.requestTimeSettingMode();
        system.startCheckTimeOut();
        // (임시로 10분을 10초로 바꾸고) 10초 정지 후 어떻게 되는 지 확인
        java.lang.System.out.println(system.getMode());

        // 스톱워치
        java.lang.System.out.println(system.getSelectedFid());
        stopwatch.requestRecordCheckMode();
        system.startCheckTimeOut();
// (임시로 10분을 10초로 바꾸고) 10초 정지 후 어떻게 되는 지 확인
        java.lang.System.out.println(system.getMode());
        java.lang.System.out.println(system.getSelectedFid());

        // 타이머
        java.lang.System.out.println(system.getSelectedFid());
        timer.requestTimerSettingMode();
        system.startCheckTimeOut();
// (임시로 10분을 10초로 바꾸고) 10초 정지 후 어떻게 되는 지 확인
        java.lang.System.out.println(system.getMode());
        java.lang.System.out.println(system.getSelectedFid());

        // 디데이
        java.lang.System.out.println(system.getSelectedFid());
        d_day.requestDdaySettingMode();
        system.startCheckTimeOut();
// (임시로 10분을 10초로 바꾸고) 10초 정지 후 어떻게 되는 지 확인
        java.lang.System.out.println(system.getMode());

        // 알람

        java.lang.System.out.println(system.getSelectedFid());
        alarm.requestAlarmSettingMode();
        system.startCheckTimeOut();
// (임시로 10분을 10초로 바꾸고) 10초 정지 후 어떻게 되는 지 확인
        java.lang.System.out.println(system.getMode());
        java.lang.System.out.println(system.getSelectedFid());

        // 알람 커스텀
        java.lang.System.out.println(system.getSelectedFid());
        alarmCustom.requestIntervalSettingMode();
        system.startCheckTimeOut();
// (임시로 10분을 10초로 바꾸고) 10초 정지 후 어떻게 되는 지 확인
        system.getMode();
        java.lang.System.out.println(system.getSelectedFid());
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
        java.lang.System.out.println(timeKeeping.getMode()); // 기본 단계로 돌아갔는지 확인.

        // 스톱워치
        stopwatch.requestRecordCheckMode();
        system.cancel(stopwatch);
        java.lang.System.out.println(stopwatch.getMode()); // 기본 단계로 돌아갔는지 확인.

        //타이머
        timer.requestTimerSettingMode();
        system.cancel(timer);
        java.lang.System.out.println(timer.getMode()); // 기본 단계로 돌아갔는지 확인.

        //디데이
        d_day.requestDdaySettingMode();
        system.cancel(d_day);
        java.lang.System.out.println(d_day.getMode()); // 기본 단계로 돌아갔는지 확인.

        // 알람
        alarm.requestAlarmSettingMode();
        system.cancel(alarm);
        java.lang.System.out.println(alarm.getMode()); // 기본 단계로 돌아갔는지 확인.

        // 알람 커스텀
        alarmCustom.requestIntervalSettingMode();
        system.cancel(alarmCustom);
        java.lang.System.out.println(alarmCustom.getMode()); // 기본 단계로 돌아갔는지 확인.

        alarmCustom.requestAlarmVolumeMode();
        system.cancel(alarmCustom);
        java.lang.System.out.println(alarmCustom.getMode());// 기본 단계로 돌아갔는지 확인.

        alarmCustom.requestAlarmSelectMode();
        system.cancel(alarmCustom);
        java.lang.System.out.println(alarmCustom.getMode()); // 기본 단계로 돌아갔는지 확인.


    }

}