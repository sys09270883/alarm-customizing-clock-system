import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * @author Yoonseop Shin
 */
public class D_day extends Function {

    private final int TYPE_SIZE = 3;

    public int[] getDateSettingValue() {
        return dateSettingValue;
    }

    private int dateSettingValue[] = {-1, -1, -1};

    /**
     * Default constructor
     */
    public D_day(System system) {
        fid = 4;
        d_day = -1; //일단 d-day가 없을 시의 수를 -1로 두었습니다.
        d_dayDate = new Date();
        mode = 0;
        type = 0;
        this.system = system;
    }



    public int getD_day() {
        return d_day;
    }

    private int d_day;

    public Date getD_dayDate() {
        return d_dayDate;
    }

    private Date d_dayDate;

    private int mode;

    private int type;

    // system의 blink에 접근하기 위하여
    private System system;

    public void requestDdaySettingMode() {
        changeMode(); // d-day setting mode로 변경
        String curDateStr = d_dayDate.getCurrentDate();
        String splited[] = curDateStr.split(" ");
        d_dayDate.setDate(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]), Integer.parseInt(splited[2]));
    }

    /**
     *
     */
    public void requestSave() {
        if (dateSettingValue[2] > d_dayDate.numOfDays[dateSettingValue[1]]) {
            changeMode();
            return;
        }

        Date saveDdayDate = new Date();
        saveDdayDate.setDate(dateSettingValue[0], dateSettingValue[1], dateSettingValue[2]);
        setDate(saveDdayDate);
        changeMode(); // d-day setting이 끝났으면 원래 모드로 변경
        type = 0;
    }

    /**
     * @param date
     * java.util.Calendar를 활용해 계산했습니다.
     */
    public void setDate(Date date) {
        String curDate = system.timeKeeping.getCurDate().getCurrentDate();
        StringTokenizer st = new StringTokenizer(curDate, " ");
        Calendar curDateCal = Calendar.getInstance();
        curDateCal.set(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));

        Calendar d_dayDateCal = Calendar.getInstance();
        d_dayDateCal.set(date.getYear(), date.getMonth()-1, date.getDay());

        if(curDateCal.after(d_dayDateCal)) {
            //현재 시간이 설정 시간보다 미래에 있으므로, d-day로 설정할 수 없음.
        }

        if(curDateCal.before(d_dayDateCal)) {
            //현재 시간이 설정 시간과 같거나 현재 시간이 설정 시간보다 과거라면, d-day로 설정할 수 있음.
            long curDateTime = curDateCal.getTimeInMillis() / (60*60*24*1000);
            long d_dayDateTime = d_dayDateCal.getTimeInMillis() / (60*60*24*1000);

            this.d_dayDate = date;
            this.d_day = (int)(d_dayDateTime - curDateTime);
        }
    }

    /**
     *
     */
    public void requestStopDdayBlink() {
        system.blink.stopBlink();
    }

    /**
     * 
     */
    public void requestDeleteDday() {
        d_dayDate.deleteDday();
        d_day = -1; //일단 d-day가 없을 시의 수를 -1로 두었습니다.
    }

    /**
     * 
     */
    public void timeout() {}

    /**
     * 
     */
    public void cancel() {}

    /**
     *
     */
    public void changeMode() {
        mode ^= 1;
        if (mode == 0) {
            Arrays.fill(dateSettingValue, -1);
        }
        else {
            String str = d_dayDate.getCurrentDate();
            StringTokenizer st = new StringTokenizer(str, " ");
            dateSettingValue[0] = Integer.parseInt(st.nextToken());
            dateSettingValue[1] = Integer.parseInt(st.nextToken());
            dateSettingValue[2] = Integer.parseInt(st.nextToken());
        }
    }

    public int getType() {
        return this.type;
    }
    /**
     * @param diff
     */
    public void changeValue(int diff) {
        dateSettingValue[type] += diff;
        switch(type) {
            case 0:
                if(dateSettingValue[type] < d_dayDate.YEAR_BOTTON_LIMIT)
                    dateSettingValue[type] = d_dayDate.YEAR_BOTTON_LIMIT;
                else if(dateSettingValue[type] > d_dayDate.YEAR_TOP_LIMIT)
                    dateSettingValue[type] = d_dayDate.YEAR_TOP_LIMIT;
                break;
            case 1:
                if(dateSettingValue[type] < d_dayDate.MONTH_BOTTON_LIMIT)
                    dateSettingValue[type] = d_dayDate.MONTH_BOTTON_LIMIT;
                else if(dateSettingValue[type] > d_dayDate.MONTH_TOP_LIMIT)
                    dateSettingValue[type] = d_dayDate.MONTH_TOP_LIMIT;
                break;
            case 2:
                if(dateSettingValue[type] < d_dayDate.numOfDays[0])
                    dateSettingValue[type] = d_dayDate.numOfDays[0];
                else if(dateSettingValue[type] > d_dayDate.numOfDays[dateSettingValue[1]])
                    dateSettingValue[type] = d_dayDate.numOfDays[dateSettingValue[1]];
                break;
        }
    }

    /**
     * 
     */
    public void changeType() { type = (type + 1) % TYPE_SIZE; }

    public int getMode() {
        return this.mode;
    }
}