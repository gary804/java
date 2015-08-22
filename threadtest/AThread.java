package threadtest;

public class AThread extends Thread implements MyThread{
	String msg;
	Singleton counter = Singleton.getInstance();
	AThread(  ){
		this.msg = "No message";
	}
	AThread( String msg ){
		this.msg = msg;
	}
	public void printMessage(){
		System.out.println(msg);
	}
	public String getMessage(){
		return msg;
	}
	public void setMessage(String msg){
		this.msg = msg;
	}
	@Override
	public void run(){
		System.out.println("The thread name is "+getName()+". The current counter value is "+counter.increase());
		this.setName(msg);
		System.out.println("The thread name is \""+this.getName()+"\". Its message is:");
		printMessage();
	}
}