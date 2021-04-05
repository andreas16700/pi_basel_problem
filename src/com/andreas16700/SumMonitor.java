package com.andreas16700;

import java.math.BigDecimal;

public class SumMonitor {
    public static final SumMonitor shared = new SumMonitor();
    private SumMonitor(){

    }
    private BigDecimal sum = new BigDecimal(0);
    private long additions = 0;
    public synchronized void add(BigDecimal amt){
        sum = sum.add(amt);additions++;
    }

    public synchronized BigDecimal getSum() {
        return sum;
    }
    public synchronized long getAdditions(){
        return additions;
    }
}
