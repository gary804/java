package threadtest;

class MonitorObject {
	boolean wasSignalled = false;

	public void doWait(){
    	synchronized(this){
      	while(!wasSignalled){
        	try{
          	this.wait();
         	} catch(InterruptedException e){}
      	}
      	//clear signal and continue running.
      	wasSignalled = false;
    	}
  	}
  	public void doNotify(){
    	synchronized(this){
      	wasSignalled = true;
      	this.notify();
    	}
  	}
}