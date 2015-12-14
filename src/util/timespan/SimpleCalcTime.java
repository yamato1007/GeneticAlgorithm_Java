package util.timespan;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by k13082kk on 2015/12/04.
 */
public class SimpleCalcTime {
    private CalcTime calcTime;
    private int key;

    public SimpleCalcTime(){
        calcTime = new CalcTime();
        key = -1;

        setTime();
    }

    public Duration checkPoint(){
        setTime();
        Duration duration = calcTime.getDuaration(key -1,key);
        return duration;
    }

    public Duration getDurationNowFromInit (){
        Instant now = Instant.now();
        return Duration.between(calcTime.getTime(0),now);
    }

    private void setTime(){
        key++;
        calcTime.setTime(key);
    }
}
