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
public class Consumer implements Runnable {

    private final SingleElemBuffer buffer;

    public Consumer(SingleElemBuffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        while (true) {
            int elem;
            try {
                elem = buffer.get();
                System.out.println(elem+" consumed");
            } catch (InterruptedException ex) {
                System.out.println(Thread.currentThread().getName()+" interrupted");
                return;
            }
            
        }
    }
}
