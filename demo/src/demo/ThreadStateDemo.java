package demo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadStateDemo {

    /**
     * New ! Runnable ! Waiting ! Blocked ! Timed_Waiting ! Terminated
     *
     * @param args
     */
    public static void main(String[] args) throws Throwable {

        ServerSocket ss = new ServerSocket(7001);

        Thread t1 = new Thread() {
            public void run() {
                try {
                    // Select * from 3000 (3 secs)
                    System.out.println("Server started and waiting for incoming request");
                    Socket s = ss.accept();
                    System.out.println("request received");

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            ;
        };

        t1.start();

        new Thread() {
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    System.out.println("T1 : " + t1.getState().name());

                }
            }

            ;

        }.start();

    }

}
