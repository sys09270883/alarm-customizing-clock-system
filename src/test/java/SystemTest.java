import junit.framework.TestCase;
import org.junit.Test;

public class SystemTest extends TestCase {

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

        stopwatch.requestRecordCheckMode();
        system.startCheckTimeOut();

        timer.requestTimerSettingMode();
        system.startCheckTimeOut();

        d_day.requestDdaySettingMode();
        system.startCheckTimeOut();

        alarm.requestAlarmSettingMode();
        system.startCheckTimeOut();

        alarmCustom.requestIntervalSettingMode();
        system.startCheckTimeOut();

    }

    @Test
    public void CancelTest() {

    }

}