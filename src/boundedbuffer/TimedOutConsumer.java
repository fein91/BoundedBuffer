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
public class TimedOutConsumer implements Runnable {

    private final TimeOutSingleElemBuffer buffer;
    private final int timeOut;

    public TimedOutConsumer(TimeOutSingleElemBuffer buffer, int timeOut) {
        this.buffer = buffer;
        this.timeOut = timeOut;
    }

    @Override
    public void run() {
        while (true) {
            int elem;
            try {
                elem = buffer.get(timeOut);
                System.out.println(Thread.currentThread().getName()+": "+elem + " consumed");
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
                return;
            } catch (TimeOutException ex) {
                System.out.println("TimeOutException in consumer: " + Thread.currentThread().getName());
                return;
            }
        }
    }
}
