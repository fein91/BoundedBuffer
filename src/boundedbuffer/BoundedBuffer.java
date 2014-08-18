/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundedbuffer;

/**
 *
 * @author Fein
 */
public class BoundedBuffer {

    /**
     * @param args the command line arguments
     */
    private static void testSingleBuffer() {
        SingleElemBuffer buffer = new SingleElemBuffer();
        Thread producerThread1 = new Thread(new Producer(0, 200, buffer), "Producer-1");
        Thread producerThread2 = new Thread(new Producer(1000, 200, buffer), "Producer-1");
        Thread producerThread3 = new Thread(new Producer(100, 200, buffer), "Producer-1");

        Thread consumerThread1 = new Thread(new Consumer(buffer), "Consumer-1");
        producerThread1.start();
        producerThread2.start();
        producerThread3.start();
        consumerThread1.start();

    }

    private static void testSingleTimeOutBuffer() {
        TimeOutSingleElemBuffer buffer = new TimeOutSingleElemBuffer();
        int periodProduce = 200;
        int timeOutProducer = 100;

        int timeOutConsumer = 200;

        Thread producer1 = new Thread(new TimedOutProducer(0, periodProduce, buffer, timeOutProducer), "Producer-0");
        Thread producer2 = new Thread(new TimedOutProducer(100, periodProduce, buffer, timeOutProducer), "Producer-1");
        Thread producer3 = new Thread(new TimedOutProducer(1000, periodProduce, buffer, timeOutProducer), "Producer-2");
        Thread producer4 = new Thread(new TimedOutProducer(10000, periodProduce, buffer, timeOutProducer), "Producer-3");
        Thread producer5 = new Thread(new TimedOutProducer(100000, periodProduce, buffer, timeOutProducer), "Producer-4");
        Thread producer6 = new Thread(new TimedOutProducer(1000000, periodProduce, buffer, timeOutProducer), "Producer-5");

        Thread consumer1 = new Thread(new TimedOutConsumer(buffer, timeOutConsumer), "Consumer-0");
        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();
        producer6.start();

        consumer1.start();
    }

    public static void main(String[] args) {

    }
}
