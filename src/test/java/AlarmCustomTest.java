import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class AlarmCustomTest {
    System system = new System();


    @Test
    public void ControlAlarmListTest() {
        AlarmCustom alarmCustom = new AlarmCustom(system);

        alarmCustom.requestAlarmSelectMode();

    }

    @Test
    public void SetAlarmIntervalTest() {
        AlarmCustom alarmCustom = new AlarmCustom(system);
        alarmCustom.requestIntervalSettingMode();
    }

    @Test
    public void SetAlarmVolumeTest() {
        AlarmCustom alarmCustom = new AlarmCustom(system);
        alarmCustom.requestAlarmVolumeMode();

    }




}