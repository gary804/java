package threadtest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class ThreadTest {
	static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s"
                + "  has bowed to me!%n", 
                this.name, bower.getName());
            bower.bowBack(this);
        }
        public synchronized void bowBack(Friend bower) {
        	try{
        		Thread.sleep(1000);
        	} catch (InterruptedException e) {}
            System.out.format("%s: %s"
                + " has bowed back to me!%n",
                this.name, bower.getName());
        }
    }

    static class SafeFriend {
        private final String name;
        private final Lock lock = new ReentrantLock();

        public SafeFriend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public boolean impendingBow(SafeFriend bower) {
            Boolean myLock = false;
            Boolean yourLock = false;
            try {
                myLock = lock.tryLock();
                yourLock = bower.lock.tryLock();
            } finally {
                if (! (myLock && yourLock)) {
                    if (myLock) {
                        lock.unlock();
                    }
                    if (yourLock) {
                        bower.lock.unlock();
                    }
                }
            }
            return myLock && yourLock;
        }
            
        public void bow(SafeFriend bower) {
            if (impendingBow(bower)) {
                try {
                    System.out.format("%s: %s has"
                        + " bowed to me!%n", 
                        this.name, bower.getName());
                    bower.bowBack(this);
                } finally {
                    lock.unlock();
                    bower.lock.unlock();
                }
            } else {
                System.out.format("%s: %s started"
                    + " to bow to me, but saw that"
                    + " I was already bowing to"
                    + " him.%n",
                    this.name, bower.getName());
            }
        }

        public void bowBack(SafeFriend bower) {
            System.out.format("%s: %s has" +
                " bowed back to me!%n",
                this.name, bower.getName());
        }
    }

    static class BowLoop implements Runnable {
        private SafeFriend bower;
        private SafeFriend bowee;

        public BowLoop(SafeFriend bower, SafeFriend bowee) {
            this.bower = bower;
            this.bowee = bowee;
        }
    
        public void run() {
            Random random = new Random();
            //for (;;) {
            for (int i=0;i<100;i++) {
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {}
                bowee.bow(bower);
            }
        }
    }




	
	public static void main(String[] args){
		final MonitorObject myMonitorA = new MonitorObject();
		final MonitorObject myMonitorB = new MonitorObject();

		System.out.println(Thread.currentThread().getName());

		AThread athread = new AThread("A testing thread");
		athread.printMessage();
		athread.setMessage("Updated Message");

		ARunnable arunnable = new ARunnable("A testing runnable");
		arunnable.printMessage();	//works
		Thread arunnablethread = new Thread(arunnable, "Runnable Thread");

		//athread.start();
		//arunnablethread.start();

		//create an anonymous subclass of Thread like this:
		Thread thread = new Thread(){
			Singleton counter = Singleton.getInstance();
			@Override
    		public void run(){
    			System.out.println("The thread name is "+getName()+". The current counter value is "+counter.increase());
    			System.out.println("An anonymous thread Running");
    		}
  		};
  		//thread.start();

  		// create an anonymous implementation of Runnable, like this:
  		Runnable myRunnable = new Runnable(){
  			String msg ="No message";
  			Singleton counter = Singleton.getInstance();
  			int temp;
  			private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();	//it only can be seen by the thread who set the value.

  			public void setMessage(String msg){
				this.msg = msg;
			}
			public String getMessage(){
				return msg;
			}
			@Override
			public void run(){
				//threadLocal.set( (int) (Math.random() * 100D) );
				temp = (int) (Math.random() * 100D);
				threadLocal.set( (int) (temp) );
				try {
                	Thread.sleep(20);
            	} catch (InterruptedException e) {
            	}
            	System.out.println(threadLocal.get()+", " +temp);	//you can see the diference between two variable.

				System.out.println("The thread name is "+Thread.currentThread().getName()+". The current counter value is "+counter.increase());
				System.out.println("An anonymous runnable running");
				setMessage("Works or not?");
				System.out.println(getMessage());
			}
		};
		//myRunnable.setMessage("Updated message");	//not work

		Thread thread1 = new Thread(myRunnable);
		Thread thread1_1 = new Thread(myRunnable);
		//thread1.start();

		Thread thread2 = new Thread(){
			String msg ="No message";
			Singleton counter = Singleton.getInstance();
			public void setMessage(String msg){
				this.msg = msg;
			}
			public String getMessage(){
				return msg;
			}
			@Override
    		public void run(){
    			System.out.println("The thread name is "+getName()+". The current counter value is "+counter.increase());
      			System.out.println(msg);
      			setMessage("Message has been updated");
      			System.out.println(getMessage());
    		}
  		};
  		//thread2.setMessage("Hello, this is a testing.");	//not work
  		//System.out.println(thread2.getMessage());	//not work
  		//thread2.start();


		thread2.start();
		thread.start();
		thread1.start();
		thread1_1.start();
		athread.start();
		arunnablethread.start();

  		Runnable first = new Runnable(){
  			MonitorObject myMonitor1 = myMonitorA;
  			MonitorObject myMonitor2 = myMonitorB;
  			String msg ="No message";
  			public void setMessage(String msg){
				this.msg = msg;
			}
			public String getMessage(){
				return msg;
			}
			@Override
			public void run(){
				for (int i=0; i<10; i++){
					setMessage("First Thread: message number is "+i);
					System.out.println(getMessage());
					myMonitor2.doNotify();
					myMonitor1.doWait();
				}
			}
		};
  		Runnable second = new Runnable(){
  			MonitorObject myMonitor1 = myMonitorA;
  			MonitorObject myMonitor2 = myMonitorB;
  			String msg ="No message";
  			public void setMessage(String msg){
				this.msg = msg;
			}
			public String getMessage(){
				return msg;
			}
			@Override
			public void run(){
				for (int i=0; i<10; i++){
					myMonitor2.doWait();
					setMessage("Second Thread: message number is "+i);
					System.out.println(getMessage());
					myMonitor1.doNotify();
				}
			}
		};
		Thread tfirst = new Thread(first);
		Thread tsecond = new Thread(second);
		//tsecond.start();
		tfirst.start();
		tsecond.start();

/*
		//deadlock demo
		final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");
		new Thread(new Runnable() {
    	    public void run() { 
    	    	while(true){
    	    		alphonse.bow(gaston); 
    	    	}
    	    }
       	}).start();
       	new Thread(new Runnable() {
            public void run() { 
            	while(true){
            		gaston.bow(alphonse); 
            	}
            }
       	}).start();
*/


       	//it will not deadlock
		final SafeFriend alphonse1 = new SafeFriend("Alphonse1");
        final SafeFriend gaston1 = new SafeFriend("Gaston1");
        new Thread(new BowLoop(alphonse1, gaston1)).start();
        new Thread(new BowLoop(gaston1, alphonse1)).start();

	}
}