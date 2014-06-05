package edu.vuum.mocca;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

/**
 * @class SimpleSemaphore
 *
 * @brief This class provides a simple counting semaphore
 *        implementation using Java a ReentrantLock and a
 *        ConditionObject.  It must implement both "Fair" and
 *        "NonFair" semaphore semantics, just liked Java Semaphores. 
 */
public class SimpleSemaphore {
    /**
     * Define a ReentrantLock to protect the critical section.
     */
    // TODO - you fill in here
	// ALM - CHECK
	ReentrantLock mLock;

    /**
     * Define a ConditionObject to wait while the number of
     * permits is 0.
     */
    // TODO - you fill in here
	// ALM - CHECK
	Condition mPermitsAvailable; 

    /**
     * Define a count of the number of available permits.
     */
    // TODO - you fill in here.  Make sure that this data member will
    // ensure its values aren't cached by multiple Threads..
	volatile int mAvailablePermits;

    /**
     * Constructor initialize the data members.  
     */
    public SimpleSemaphore (int permits,
                            boolean fair)
    { 
        // TODO - you fill in here
    	// ALM - CHECK
    	mAvailablePermits = permits;
    	mLock = new ReentrantLock(fair);
    	mPermitsAvailable = mLock.newCondition();
    }

    /**
     * Acquire one permit from the semaphore in a manner that can
     * be interrupted.
     */
    public void acquire() throws InterruptedException {
        // TODO - you fill in here
    	// ALM - CHECK
    	final ReentrantLock lock = mLock;
    	lock.lockInterruptibly();
    	
    	try
    	{
    		while(mAvailablePermits == 0)
    			mPermitsAvailable.await();
    		--mAvailablePermits;
    	}
    	finally
    	{
    		lock.unlock();
    	}
    }

    /**
     * Acquire one permit from the semaphore in a manner that
     * cannot be interrupted.
     */
    public void acquireUninterruptibly() {
        // TODO - you fill in here
    	// ALM - CHECK
    	final ReentrantLock lock = mLock;
    	lock.lock();
    	
		while(mAvailablePermits == 0)
			mPermitsAvailable.awaitUninterruptibly();
		--mAvailablePermits;
		
		lock.unlock();
    }

    /**
     * Return one permit to the semaphore.
     */
    void release() {
        // TODO - you fill in here
    	// ALM - CHECK
    	final ReentrantLock lock = mLock;
    	lock.lock();
    	mAvailablePermits++;
    	mPermitsAvailable.signal();
    	lock.unlock();
    }
    
    /**
     * Return the number of permits available.
     */
    public int availablePermits(){
    	// TODO - you fill in here
    	// ALM - CHECK
    	int iRet;
    	
    	final ReentrantLock lock = mLock;
    	lock.lock();
    	iRet = mAvailablePermits;
    	lock.unlock();
    	
    	return iRet; // You will change this value. 
    }
}

