package com.things.connect;

import java.util.concurrent.Executor;

abstract class MessageListener  implements Runnable {
	 public void ThreadTask(Executor executor) {
	        executor.execute(this);
	    }

	    public void run() {
	        this.performInAsync();
	    }

	    public abstract void performInAsync();


}
