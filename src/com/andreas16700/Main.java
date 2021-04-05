package com.andreas16700;

import java.math.BigDecimal;
import java.math.MathContext;

public class Main {
    private static final BigDecimal six = new BigDecimal(6, Calculator.precise);
    private static final MathContext lessPrecise = new MathContext(100);
    private static BigDecimal getPi(BigDecimal sum){

        BigDecimal value = sum.multiply(six);
        return value.sqrt(lessPrecise);
    }
    public static void main(String[] args) throws InterruptedException {
        int threads = 10;
        Calculator[] calculators = new Calculator[threads];
        for (int i = 0; i < threads; i++) {
            calculators[i] = new Calculator(i);
        }
        for (int i = 0; i < threads; i++) {
            calculators[i].start();
        }
        int seconds = 2;
        long additions=0;
        while (true) {
            Thread.sleep(seconds * 1000L);
            while (additions==SumMonitor.shared.getAdditions())
                Thread.sleep(seconds * 1000);
            additions=SumMonitor.shared.getAdditions();
            BigDecimal sum = SumMonitor.shared.getSum();
            System.out.println("Additions: "+additions+"*"+threads+"*"+Calculator.CALCULATIONS_BFORE_UPDATING);
            System.out.println("Approximate pi: "+ getPi(sum));
        }
    }
}
