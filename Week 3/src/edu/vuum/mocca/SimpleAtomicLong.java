package edu.vuum.mocca;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;

/**
 * @class SimpleAtomicLong
 *
 * @brief This class implements a subset of the
 *        java.util.concurrent.atomic.SimpleAtomicLong class using a
 *        ReentrantReadWriteLock to illustrate how they work.
 */
class SimpleAtomicLong
{
    /**
     * The value that's manipulated atomically via the methods.
     */
    private long mValue;
    
    /**
     * The ReentrantReadWriteLock used to serialize access to mValue.
     */

    // TODO -- you fill in here by replacing the null with an
    // initialization of ReentrantReadWriteLock.
    // ALM - DONE
    // NOTE: opted for non-fair implementation in an effort to have mercy
    // on the graders since it reduces run time drastically on most platforms!
    private final ReentrantReadWriteLock mRWLock = new ReentrantReadWriteLock(false);

    /**
     * Creates a new SimpleAtomicLong with the given initial value.
     */
    public SimpleAtomicLong(long initialValue)
    {
        // TODO -- you fill in here
        // ALM - DONE
        mValue = initialValue;
    }

    /**
     * @brief Gets the current value.
     * 
     * @returns The current value
     */
    public long get()
    {
        // TODO -- you fill in here
        // ALM - DONE
        final Lock readLock = mRWLock.readLock();
        readLock.lock();
        
        try
        {
	        return mValue;
        }
        finally
        {
        	readLock.unlock();
        }
    }

    /**
     * @brief Atomically decrements by one the current value
     *
     * @returns the updated value
     */
    public long decrementAndGet()
    {
        // TODO -- you fill in here
        // ALM - DONE
        final Lock writeLock = mRWLock.writeLock();
        writeLock.lock();

        try
        {
	        return --mValue;
        }
        finally
        {
        	writeLock.unlock();
        }
    }

    /**
     * @brief Atomically increments by one the current value
     *
     * @returns the previous value
     */
    public long getAndIncrement()
    {
        // TODO -- you fill in here
        // ALM - DONE
        final Lock writeLock = mRWLock.writeLock();
        writeLock.lock();
        
        try
        {
        	return mValue++;
        }
        finally
        {
        	writeLock.unlock();
        }
}

    /**
     * @brief Atomically decrements by one the current value
     *
     * @returns the previous value
     */
    public long getAndDecrement()
    {
        // TODO -- you fill in here
        // ALM - DONE
        final Lock writeLock = mRWLock.writeLock();
        writeLock.lock();
        
        try
        {
        	return mValue--;
        }
        finally
        {
        	writeLock.unlock();
        }
    }

    /**
     * @brief Atomically increments by one the current value
     *
     * @returns the updated value
     */
    public long incrementAndGet()
    {
        // TODO -- you fill in here
        // ALM - DONE
        final Lock writeLock = mRWLock.writeLock();
        writeLock.lock();
        
        try
        {
        	return ++mValue;
        }
        finally
        {
        	writeLock.unlock();
        }
    }
}

