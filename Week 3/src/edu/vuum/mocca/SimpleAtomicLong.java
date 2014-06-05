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
    private ReentrantReadWriteLock mRWLock = new ReentrantReadWriteLock(false);

    /**
     * Creates a new SimpleAtomicLong with the given initial value.
     */
    public SimpleAtomicLong(long initialValue)
    {
        // TODO -- you fill in here
        // ALM - DONE
        final Lock writeLock = mRWLock.writeLock();
        writeLock.lock();
        mValue = initialValue;
        writeLock.unlock();
    }

    /**
     * @brief Gets the current value.
     * 
     * @returns The current value
     */
    public long get()
    {
        long value;

        // TODO -- you fill in here
        // ALM - DONE
        final Lock readLock = mRWLock.readLock();
        readLock.lock();
        value = mValue;
        readLock.unlock();

        return value;
    }

    /**
     * @brief Atomically decrements by one the current value
     *
     * @returns the updated value
     */
    public long decrementAndGet()
    {
        long value = 0;

        // TODO -- you fill in here
        // ALM - DONE
        final Lock writeLock = mRWLock.writeLock();
        writeLock.lock();
        value = --mValue;
        writeLock.unlock();

        return value;
    }

    /**
     * @brief Atomically increments by one the current value
     *
     * @returns the previous value
     */
    public long getAndIncrement()
    {
        long value = 0;

        // TODO -- you fill in here
        // ALM - DONE
        final Lock writeLock = mRWLock.writeLock();
        writeLock.lock();
        value = mValue++;
        writeLock.unlock();

        return value;
    }

    /**
     * @brief Atomically decrements by one the current value
     *
     * @returns the previous value
     */
    public long getAndDecrement()
    {
        long value = 0;

        // TODO -- you fill in here
        // ALM - DONE
        final Lock writeLock = mRWLock.writeLock();
        writeLock.lock();
        value = mValue--;
        writeLock.unlock();

        return value;
    }

    /**
     * @brief Atomically increments by one the current value
     *
     * @returns the updated value
     */
    public long incrementAndGet()
    {
        long value = 0;

        // TODO -- you fill in here
        // ALM - DONE
        final Lock writeLock = mRWLock.writeLock();
        writeLock.lock();
        value = ++mValue;
        writeLock.unlock();

        return value;
    }
}

