package com.things.connect;

import java.util.concurrent.Executor;

public abstract class ThreadTask implements Runnable {
    public ThreadTask(Executor executor) {
        executor.execute(this);
    }

    public void run() {
        this.performInAsync();
    }

    public abstract void performInAsync();
    

}
