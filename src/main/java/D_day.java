import java.util.Calendar;

import static java.lang.Integer.parseInt;

/**
 * @author Yoonseop Shin
 */
public class D_day extends Function {

    /**
     * Default constructor
     */
    public D_day(System system) {
        fid = 4;
        d_day = -1; //일단 d-day가 없을 시의 수를 -1로 두었습니다.
        d_dayDate = null;
        mode = 0;
    }

    public int getD_day() {
        return d_day;
    }

    /**
     * 
     */
    private int d_day;

    /**
     * 
     */
    private Date d_dayDate;

    /**
     * 
     */

    /**
     * 
     */
    private int mode;


    /**
     * 
     */
    public void requestDdaySettingMode() {
        // TODO implement here
    }

    /**
     * request Save는 setDate를 단순히 호출하는 방식이라서
     * 삭제해도 좋을 것 같습니다.
     */
    public void requestSave() {
        // setDate(date, d_day);
        // TODO implement here
    }

    /**
     * @param date 
     * @param d_day
     * param에 d_day는 필요없을 것 같습니다.
     * java.util.Calendar를 활용해 계산했습니다.
     */
    public void setDate(Date date, int d_day) {
        String curDate = date.getCurrentDate(); // TimeKeeping의 date

        Calendar curDateCal = Calendar.getInstance();
        String splited[] = curDate.split(" ");
        curDateCal.set(parseInt(splited[0]), parseInt(splited[1])-1, parseInt(splited[2]));
        curDateCal = Calendar.getInstance(); //현재시간 가져오기

        Calendar d_dayDateCal = Calendar.getInstance();
        d_dayDateCal.set(date.getYear(), date.getMonth()-1, date.getDay());
        d_dayDateCal = Calendar.getInstance(); //설정시간 가져오기

        if(curDateCal.after(d_dayDateCal)) {
            //현재 시간이 설정 시간보다 미래에 있으므로, d-day로 설정할 수 없음.
        }

        if(curDateCal.equals(d_dayDateCal) || curDateCal.before(d_dayDateCal)) {
            //현재 시간이 설정 시간과 같거나 현재 시간이 설정 시간보다 과거라면, d-day로 설정할 수 있음.
            long curDateTime = curDateCal.getTimeInMillis() / (60*60*24*1000);
            long d_dayDateTime = d_dayDateCal.getTimeInMillis() / (60*60*24*1000);

            this.d_dayDate = date;
            this.d_day = (int)(d_dayDateTime - curDateTime);
        }
    }

    /**
     * 울리고 있는 Blink를 받아올 방법이 필요합니다.
     */
    public void requestStopDdayBlink() {
        Blink b = new Blink(); //울리고 있는 Blink에 접근해야합니다.
        b.stopBlink();
    }

    /**
     * 
     */
    public void requestDeleteDday() {
        d_dayDate.deleteDday();
        d_day = -1; //일단 d-day가 없을 시의 수를 -1로 두었습니다.
    }

    public void changeMode() {}

    /**
     * @param diff
     */
    public void changeValue(int diff) {}

    /**
     * 
     */
    public void changeType() {}

    public int getMode() {
        return this.mode;
    }
}