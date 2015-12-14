package util.timespan;

import com.sun.org.apache.xml.internal.security.Init;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by k13082kk on 2015/12/04.
 */
public class CalcTime {
    private Map<Object,Instant> instants;

    public CalcTime(){
        this.instants = new HashMap<>();
    }

    public void setTime(Object key){
        this.instants.put(key,Instant.now());
    }

    public Instant getTime(Object key){
        return this.instants.get(key);
    }


    public Duration getDuaration(Object startKey,Object endKey){
        Instant start = this.instants.get(startKey);
        Instant end = this.instants.get(endKey);

        return Duration.between(start,end);
    }

    public long getDuarationSec(Object startKey,Object endKey){
        Instant start = this.instants.get(startKey);
        Instant end = this.instants.get(endKey);

        return Duration.between(start,end).getSeconds();
    }

    public long getDuarationNano(Object startKey,Object endKey){
        Instant start = this.instants.get(startKey);
        Instant end = this.instants.get(endKey);

        return Duration.between(start,end).getNano();
    }

}
