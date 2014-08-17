/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundedbuffer;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fein
 */
public class Producer implements Runnable {

    private int startValue;
    private final int period;
    private final SingleElemBuffer buffer;

    public Producer(int startValue, int period, SingleElemBuffer buffer) {
        this.startValue = startValue;
        this.period = period;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(startValue + " produced");
                buffer.put(startValue++);
                Thread.sleep(period);
            } catch (InterruptedException ex) {
                System.out.println(Thread.currentThread().getName()+" interrupted");
                return;
            }
        }
    }
}
