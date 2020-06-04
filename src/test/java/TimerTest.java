import org.junit.Test;

import static org.junit.Assert.*;

public class TimerTest {
    System system = new System();

    @Test
    public void setTimerTest() {
        Timer timer = new Timer(system);

        timer.requestTimerSettingMode();

        for(int i = 0; i<2; i++) {
            timer.changeValue(i+1);
            timer.changeType();
        }
        timer.changeValue(3);
        timer.requestSave();

        Time time = timer.getTimer();

        String timeStr = time.getTime();
        String splitedTime[] = timeStr.split(" ");

        assert(splitedTime[0].equals("1"));
        assert(splitedTime[1].equals("2"));
        assert(splitedTime[2].equals("3"));
    }
}