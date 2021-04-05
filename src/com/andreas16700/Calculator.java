package com.andreas16700;

import java.math.BigDecimal;
import java.math.MathContext;

public class Calculator extends Thread{
    private final int increaseBy;
    public static final int CALCULATIONS_BFORE_UPDATING = 1000;
    public static final MathContext precise = new MathContext(100);
    public Calculator(int incr){
        this.increaseBy = incr;
    }

    @Override
    public void run() {
        BigDecimal number = new BigDecimal(increaseBy, precise);
        BigDecimal zero = new BigDecimal(0);
        BigDecimal sum = zero;
        BigDecimal one = new BigDecimal(1, precise);
        BigDecimal ten = new BigDecimal(10);
        int runs=0;
        while (true){
            if (number.equals(BigDecimal.ZERO))
                number=number.add(ten);
            BigDecimal powr = number.multiply(number);

            BigDecimal div = one.divide(powr, precise);
            sum = sum.add(div);
            runs++;number=number.add(ten);
            if (runs==CALCULATIONS_BFORE_UPDATING){
                SumMonitor.shared.add(sum);
                sum=zero;runs=0;
            }
        }
    }
}
