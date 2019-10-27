package com.mashibing.aqs;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Sync extends AbstractQueuedSynchronizer {

    @Override
    protected boolean tryAcquire(int arg){
        if(compareAndSetState(0,1)){
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg){
        if (isHeldExclusively())
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    // 这个实现有错，可能把别的线程的设的1释放了，因为它是AtomicInteger？被volatile修饰
    @Override
    protected boolean isHeldExclusively(){
        return getState() == 1;
    }

}
