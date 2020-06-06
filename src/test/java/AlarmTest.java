

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class AlarmTest  {

    System system = new System();

    @Test
    public void SetAlarmTest() {
        Alarm alarm = new Alarm(system);

        alarm.requestAlarmSettingMode(); // mode = 1;

        // 순서대로 시, 분, 초 입력

        alarm.changeValue(2); // 시
        alarm.changeType();
        alarm.changeValue(2); // 분
        alarm.changeType();
        alarm.changeValue(2); // 초

        //request Save
        alarm.requestSave();



        // 테스트 비교용
        int alarmSettingValue[] = {-1,-1,-1};
        Time time = new Time(2);

        // 순서대로 시, 분, 초 입력
        for (int i = 0 ; i < 3 ; i++)
        {
            alarmSettingValue[i] = 2;
        }

        time.setTime(alarmSettingValue[0], alarmSettingValue[1], alarmSettingValue[2]);
/*        AlarmData alarmData = new AlarmData();
        alarmData.setAlarmTime(time);*/


        // 잘 저장 되었는 지 확인.
        assert(alarm.getAlarmList()[0].getAlarmTime().getTime().equals(time.getTime()));

    }


    @Test
    public void DeleteAlarmTest() {
        Alarm alarm = new Alarm(system);

        alarm.movePointer(1);
        int i = alarm.getAlarmPointer();
        assertEquals(i, 0);


        Time time = new Time(2);
        time.setTime(1,1,1);
        alarm.addTimeToAlarmList(time);
        time.setTime(2,2,2);
        alarm.addTimeToAlarmList(time);
        time.setTime(3,3,3);
        alarm.addTimeToAlarmList(time);

        alarm.requestDeleteAlarm(); // " 2 2 2" 삭제

        assert(alarm.getAlarmList()[1].getAlarmTime().equals(time)); // "3 3 3"
        time.setTime(1,1,1);
        assert(alarm.getAlarmList()[0].getAlarmTime().equals(time));



    }

    @Test
    public void BeepAlarmTest() {
        Alarm alarm = new Alarm(system);
        TimeKeeping timeKeeping = new TimeKeeping(system);
        Buzzer buzzer = new Buzzer();

        Time time = new Time(2);
        time.setTime(23,59,59);

        alarm.addTimeToAlarmList(time);

        //현재 시간 임의 설정
        timeKeeping.requestTimeSettingMode();

        // 시간, 분, 초, 순으로 설정

        for(int i = 0 ; i < 5 ; i++) {
            timeKeeping.changeValue(60); // 알맞게 변경하세요
            timeKeeping.changeType();
        }

        timeKeeping.changeValue(60);
        timeKeeping.requestSave(); // "23 59 59"

        java.lang.System.out.println(timeKeeping.getCurTime().getTime());
        java.lang.System.out.println(alarm.getAlarmList()[0].getAlarmTime().getTime());

        // 현재 시각과 알람 시간이 같으면
        if(timeKeeping.getCurTime().getTime().equals(alarm.getAlarmList()[0].getAlarmTime().getCurrentTime()))
        {
            system.beepBuzzer(); // 버저 울리기.
            assertEquals(1, system.getStatus() & 1);
            // buzzer에서 interval과 volume을 get할 방법이 없음. -> getter로 신규 함수 추가해야함.
            assertEquals(1000, system.buzzer.getInterval());
            assertEquals(0.07, system.buzzer.getVolume());
        }


    }

    @Test
    public void StopAlarmBuzzerTest() {
        Alarm alarm = new Alarm(system);
        Buzzer buzzer = new Buzzer();
        buzzer.beepBuzzer();
        alarm.requestStopAlarmBuzzer();

        assertEquals(0, system.getStatus() & 0);

    }

    // DisplayAlarmList는 system에서 확인

    @Test
    public void ControlAlarmListTest() {

        Alarm alarm = new Alarm(system);

        // size == 0
        alarm.movePointer(1);
        assert(alarm.getAlarmPointer() == 0); // 바로 리턴되서 변동 없음.

        // size > 0
        Time time = new Time(2);
        time.setTime(1,1,1);
        alarm.addTimeToAlarmList(time);
        time.setTime(1,1,1);
        alarm.addTimeToAlarmList(time);
        time.setTime(1,1,1);
        alarm.addTimeToAlarmList(time);
        time.setTime(1,1,1);
        alarm.addTimeToAlarmList(time);

        alarm.movePointer(1);
        assert(alarm.getAlarmPointer() == 1);
        alarm.movePointer(1);
        assert(alarm.getAlarmPointer() == 2);
        alarm.movePointer(1);
        assert(alarm.getAlarmPointer() == 3);
        alarm.movePointer(1);
        assert(alarm.getAlarmPointer() == 4);
        alarm.movePointer(-1);
        assert (alarm.getAlarmPointer() == 3);
        alarm.movePointer(-1);
        assert (alarm.getAlarmPointer() == 2);
        alarm.movePointer(-1);
        assert (alarm.getAlarmPointer() == 1);
        alarm.movePointer(-1);
        assert (alarm.getAlarmPointer() == 0);



    }

}