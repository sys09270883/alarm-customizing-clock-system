import javafx.util.Pair;
import java.text.SimpleDateFormat;
import java.lang.System;
import java.lang.Runnable;

class Time implements Runnable {

    public final int HOUR_TOP_LIMIT = 23;
    public final int MINUTE_TOP_LIMIT = 59;
    public final int SECOND_TOP_LIMIT = 59;
    public final int TIME_BOTTOM_LIMIT = 0;

    // 0 -> 시간 감소 1 -> 시간 증가
    private int timeFlag;
    private int hour;
    private int min;
    private int sec;
    private boolean isPaused; // pause, start 표현 위한 boolean 변수 추가
    private final Object lock = new Object();
    private ConditionSatisfiedListener dateChangedListener;
    private ConditionSatisfiedListener secondChangedListener;
    private Thread th;

    public void setTime(int hour, int min, int sec) {
        this.hour = hour;
        this.min = min;
        this.sec = sec;
    }

    public Time getTime() {
        Time result = new Time(0);
        result.setTime(this.hour, this.min, this.sec);
        return result;
    }

    // TODO timeFlag가 시간을 증가시킬지, 감소시킬지를 의미하는 flag. Time 생성자에 필요할 듯
    public Time(int timeFlag) {
        SimpleDateFormat format = new SimpleDateFormat("HH mm ss");
        String curTime = format.format(System.currentTimeMillis());

        String splited[] = curTime.split(" ");

        hour = Integer.parseInt(splited[0]);
        min = Integer.parseInt(splited[1]);
        sec = Integer.parseInt(splited[2]);

        isPaused = false;
        this.timeFlag = timeFlag;

        dateChangedListener = null;
        secondChangedListener = null;

    }

    public void pauseTime() {
        isPaused = true;
    }

    // 추가
    public void startTime() {
        isPaused = false;
        th = new Thread(this);
        th.start();
    }

    public String getCurrentTime() {
        String currentTime;
        synchronized(lock) {
            currentTime = Integer.toString(hour) + " " + Integer.toString(min) + " " + Integer.toString(sec);
        }
        return currentTime;
    }

    public void clearTime() {
        synchronized(lock) {
            hour = 0;
            min = 0;
            sec = 0;
        }
    }

    public void run() {
        long std = System.currentTimeMillis();
        while (!isPaused) {
            try {
                Thread.sleep(10);
                long cur = System.currentTimeMillis();
                if(cur - std >= 1000) {
                    if(timeFlag == 1) {
                        synchronized (lock) {
                            ++sec;
                            if (sec > SECOND_TOP_LIMIT) {
                                sec = TIME_BOTTOM_LIMIT;
                                ++min;
                            }
                            if (min > MINUTE_TOP_LIMIT) {
                                min = TIME_BOTTOM_LIMIT;
                                ++hour;
                            }
                            if (hour > HOUR_TOP_LIMIT) {
                                if (dateChangedListener != null) {
                                    hour = TIME_BOTTOM_LIMIT;
                                    min = TIME_BOTTOM_LIMIT;
                                    sec = TIME_BOTTOM_LIMIT;
                                    dateChangedListener.conditionSatisfied();
                                } else
                                    throw new NullListenerException();
                            }
                            secondChangedListener.conditionSatisfied();
                        }
                    }
                    else {
                        synchronized (lock) {
                            --sec;
                            if (sec < TIME_BOTTOM_LIMIT) {
                                sec = SECOND_TOP_LIMIT;
                                --min;
                            }
                            if (min < TIME_BOTTOM_LIMIT) {
                                min = MINUTE_TOP_LIMIT;
                                --hour;
                            }
                            if (hour < TIME_BOTTOM_LIMIT) {
                                if (dateChangedListener != null) {
                                    hour = TIME_BOTTOM_LIMIT;
                                    min = TIME_BOTTOM_LIMIT;
                                    sec = TIME_BOTTOM_LIMIT;
                                    dateChangedListener.conditionSatisfied();
                                } else
                                    throw new NullListenerException();
                            }
                            secondChangedListener.conditionSatisfied();
                        }
                    }
                    std = cur;
                }

            } catch (InterruptedException | NullListenerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    interface ConditionSatisfiedListener {
        void conditionSatisfied() throws InterruptedException;
    }

    public void setDateListener(ConditionSatisfiedListener dateChangedListener) {
        this.dateChangedListener = dateChangedListener;
    }

    public void setSecondListener(ConditionSatisfiedListener secondChangedListener) {
        this.secondChangedListener = secondChangedListener;
    }

    class NullListenerException extends Exception {
        public NullListenerException() {
            System.err.println("Listener is null");
        }
    }
}