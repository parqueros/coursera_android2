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
	// ALM - DONE
	ReentrantLock mLock;

    /**
     * Define a ConditionObject to wait while the number of
     * permits is 0.
     */
    // TODO - you fill in here
	// ALM - DONE
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
    	// ALM - DONE
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
    	// ALM - DONE
    	final Lock lock = mLock;
    	
    	try
    	{
    		lock.lockInterruptibly();
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
    	// ALM - DONE
    	final Lock lock = mLock;
    	
    	try
    	{
	    	lock.lock();
	    	
			while(mAvailablePermits == 0)
				mPermitsAvailable.awaitUninterruptibly();
    		--mAvailablePermits;
    	}
    	finally
    	{
    		lock.unlock();
    	}
    }

    /**
     * Return one permit to the semaphore.
     */
    void release() {
        // TODO - you fill in here
    	// ALM - DONE
    	final Lock lock = mLock;
    	
    	try
    	{
	    	lock.lock();
	    	mAvailablePermits++;
	    	
	    	// ALM - Choosing signal() over signalAll() here since
	    	// only one of possibly many waiting threads who are
	    	// looking to acquire can access the semaphore at a time.
	    	// Zero, some, or all of the rest of the threads may then
	    	// have to wait for the lock  in order to wake up only to
	    	// have to re-check the condition and go back to sleep if
	    	// there aren't enough permits for all of them. That's
	    	// unnecessary thrashing.
	    	mPermitsAvailable.signal();
    	}
    	finally
    	{
    		lock.unlock();
    	}
    }
    
    /**
     * Return the number of permits available.
     */
    public int availablePermits(){
    	// TODO - you fill in here
    	// ALM - DONE
    	final Lock lock = mLock;
    	
    	try
    	{
	    	lock.lock();
	    	return mAvailablePermits;
    	}
    	finally
    	{
    		lock.unlock();
    	}
    }
}

