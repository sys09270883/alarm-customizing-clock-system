import javafx.util.Pair;
import java.text.SimpleDateFormat;
import java.lang.System;

class Time implements Runnable {

    public final int HOUR_TOP_LIMIT = 23;
    public final int MINUTE_TOP_LIMIT = 59;
    public final int SECOND_TOP_LIMIT = 59;
    public final int TIME_BOTTOM_LIMIT = 0;

    // 0 -> 멈춤 1 -> 흐름
    protected int timeFlag;
    protected int hour;
    protected int min;
    protected int sec;
    private final Object lock = new Object();
    private ConditionSatisfiedListener dateChangedListener;

    public Time() {
        SimpleDateFormat format = new SimpleDateFormat("HH mm ss");
        String curTime = format.format(System.currentTimeMillis());

        String splited[] = curTime.split(" ");

        hour = Integer.parseInt(splited[0]);
        min = Integer.parseInt(splited[1]);
        sec = Integer.parseInt(splited[2]);
        timeFlag = 0;

        dateChangedListener = null;

        Thread th = new Thread((java.lang.Runnable) this);
        th.start();
    }

    public void pauseTime() {
        timeFlag = 0;
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
        while(true) {
            try {
                Thread.sleep(10);
                long cur = System.currentTimeMillis();
                if(cur - std >= 1000) {
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
                        if(hour > HOUR_TOP_LIMIT) {
                            if(dateChangedListener != null) {
                                hour = TIME_BOTTOM_LIMIT;
                                min = TIME_BOTTOM_LIMIT;
                                sec = TIME_BOTTOM_LIMIT;
                                dateChangedListener.conditionSatisfied();
                            } else
                                throw new NullListenerException();
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
        void conditionSatisfied();
    }

    public void setListener(ConditionSatisfiedListener dateChangedListener) {
        this.dateChangedListener = dateChangedListener;
    }

    class NullListenerException extends Exception {
        public NullListenerException() {
            System.err.println("Listener is null");
        }
    }
}