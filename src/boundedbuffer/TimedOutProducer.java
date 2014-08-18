/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundedbuffer;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author otatarik
 */
public class TimedOutProducer implements Runnable {

    private int startValue;
    private final int period;
    private final TimeOutSingleElemBuffer buffer;
    private final int timeOut;

    public TimedOutProducer(int startValue, int period, TimeOutSingleElemBuffer buffer, int timeOut) {
        this.startValue = startValue;
        this.period = period;
        this.buffer = buffer;
        this.timeOut = timeOut;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(Thread.currentThread().getName()+": "+startValue + " produced");
                buffer.put(startValue++, timeOut);
                Thread.sleep(period);
            } catch (InterruptedException ex) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
                return;
            } catch (TimeOutException ex) {
                System.out.println("TimeOutException in producer: "+Thread.currentThread().getName());
                return;
            }
        }
    }

}
