package threadtest;
//Lazy initialization
/*
public class Singleton {	//works
	private static volatile Singleton instance;
	private static volatile long count;
	
	private Singleton(){}

	public static Singleton getInstance(){
		if (instance == null){
			synchronized (Singleton.class){
				if (instance == null){
					instance = new Singleton();
				}
			}
		}
		return instance;
	}

	public static synchronized long increase(){
		return ++count;
	}

}
*/

// eager initialization, which always creates an instance:
public class Singleton {
	private static volatile Singleton instance = new Singleton();
	//private static volatile long count;	//works
	private volatile long count;	//works
	
	private Singleton(){
		count = 100;	//works for static or instance variable
	}

	public static Singleton getInstance(){
		return instance;
	}

/*
	//works
	public static synchronized long increase(){
		return ++count;
	}
*/
/*
	//works for static variable
	public static long increase(){
		synchronized (Singleton.class) {
			return ++count;
		}
	}
*/
	//works for static and instance variable, but make sure all reference need to be recompiled.
	public long increase(){
		synchronized (this) {
			return ++count;
		}
	}

}
