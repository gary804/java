package threadtest;

public class ThreadTest {
	public static void main(String[] args){
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
                	Thread.sleep(2000);
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

	}
}