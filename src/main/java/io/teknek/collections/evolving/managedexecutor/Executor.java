package io.teknek.collections.evolving.managedexecutor;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class Executor implements ExecutorMBean {
    private final AtomicReference<ThreadPoolExecutor> executor = new AtomicReference<>();
    private volatile int corePoolSize = 4;
    private volatile int maxPoolSize = 10;
    private volatile long keepAliveInMs = 10_000;
    private volatile int blockingQueueSize = 1_000;

    public Executor( ){
        executor.set(new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveInMs, TimeUnit.MILLISECONDS, createQueue()));
    }

    private BlockingQueue<Runnable> createQueue(){
        return new ArrayBlockingQueue<Runnable>(this.blockingQueueSize);
    }

    public boolean restart() throws InterruptedException {
        synchronized (this) {
            ThreadPoolExecutor tp = executor.get();
            executor.set(new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveInMs, TimeUnit.MILLISECONDS, createQueue()));
            tp.shutdown();
            return tp.awaitTermination(5, TimeUnit.MILLISECONDS);
        }
    }

    public ThreadPoolExecutor activeExecutor(){
        return executor.get();
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public long getKeepAliveInMs() {
        return keepAliveInMs;
    }

    public void setKeepAliveInMs(long keepAliveInMs) {
        this.keepAliveInMs = keepAliveInMs;
    }

    public int getBlockingQueueSize() {
        return blockingQueueSize;
    }

    public void setBlockingQueueSize(int blockingQueueSize) {
        this.blockingQueueSize = blockingQueueSize;
    }

    public static void main(String [] args) throws MalformedObjectNameException, NotCompliantMBeanException,
            InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        Executor ex = new Executor();
        ObjectName objectName = new ObjectName("io.teknek:type=ManagedExecutor");
        mbs.registerMBean(ex, objectName);
        System.out.println("You have two minutes to look at jconsole");
        for (int i=0;i< 20; i++){
            ex.activeExecutor().submit( () -> {
                System.out.println("Im making an LLM");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("finished it");
            });
        }


        Thread.sleep(120_000);
    }
}

