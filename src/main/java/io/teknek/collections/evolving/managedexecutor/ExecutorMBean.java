package io.teknek.collections.evolving.managedexecutor;

public interface ExecutorMBean {
    boolean restart() throws InterruptedException;
    int getCorePoolSize();
    void setCorePoolSize(int corePoolSize);
    int getMaxPoolSize();
    void setMaxPoolSize(int maxPoolSize);
    long getKeepAliveInMs();
    void setKeepAliveInMs(long keepAliveInMs);
    int getBlockingQueueSize();
    void setBlockingQueueSize(int blockingQueueSize);
}
