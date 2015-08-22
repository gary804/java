package threadtest;

public class ARunnable implements Runnable, MyThread {
	String msg;
	Singleton counter = Singleton.getInstance();
	ARunnable(){
		msg = "No message";
	}
	ARunnable(String msg){
		this.msg = msg;
	}
	public void printMessage(){
		System.out.println(msg);
	}
	@Override
	public void run(){
		System.out.println("The thread name is "+Thread.currentThread().getName()+". The current counter value is "+counter.increase());
		System.out.println("The Runnable name is: \""+Thread.currentThread().getName()+"\" and its message is:");
		printMessage();
	}
}