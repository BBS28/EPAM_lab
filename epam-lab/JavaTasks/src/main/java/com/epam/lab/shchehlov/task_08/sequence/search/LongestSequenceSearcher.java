package com.epam.lab.shchehlov.task_08.sequence.search;

import com.epam.lab.shchehlov.task_08.constant.Constant;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The thread that performs the direct work of finding repeated sequences of bytes.
 *
 * @author B.Shchehlov.
 */
public class LongestSequenceSearcher extends Thread {
    private static final Logger log = Logger.getLogger(LongestSequenceSearcher.class);

    private final ReentrantLock mainLock;
    private final ReentrantLock subLock;
    private List<Byte> byteList;

    private int firstIndex;
    private int secondIndex;
    private int sequenceSize;
    private boolean isComplete;

    public LongestSequenceSearcher(ReentrantLock lock) {
        this.mainLock = lock;
        this.subLock = new ReentrantLock();
        this.isComplete = false;
    }

    /**
     * Initializes list of bytes.
     *
     * @param byteList list of bytes.
     */
    public void setByteList(List<Byte> byteList) {
        this.byteList = byteList;
    }

    /**
     * Returns size of repeated sequence.
     *
     * @return size repeated sequences.
     */
    public int getSequenceSize() {
        return sequenceSize;
    }

    /**
     * Returns first entry index of sequence.
     *
     * @return first entry index of sequence.
     */
    public int getFirstIndex() {
        return firstIndex;
    }

    /**
     * Returns second entry index of sequence.
     *
     * @return second entry index of sequence.
     */
    public int getSecondIndex() {
        return secondIndex;
    }

    /**
     * Return true if search is complete.
     *
     * @return true if search is complete.
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Sets fields to default values.
     */
    public void clearResult() {
        firstIndex = 0;
        secondIndex = 0;
        sequenceSize = 0;
        isComplete = false;
    }

    /**
     * Waits for the main thread to be unblocked to start searching.
     */
    @Override
    public void run() {
        while (!this.isInterrupted()) {

            if (!mainLock.isLocked()) {
                subLock.lock();
                getLongestSequence();
            }
        }
    }

    /**
     * Searches for the most repeating sequences.
     */
    public void getLongestSequence() {

        for (int checkedSize = 2; checkedSize < byteList.size(); checkedSize++) {

            for (int i = 0; i < byteList.size() - sequenceSize; i++) {

                for (int j = i + checkedSize + 1; j < byteList.size() - checkedSize + 1; j++) {

                    if (byteList.subList(i, i + checkedSize).equals(byteList.subList(j, j + checkedSize))) {
                        checkLength(checkedSize, i, j);
                        break;
                    }
                }
            }
        }
        isComplete = true;
        subLock.unlock();
    }

    /**
     * Checks the value of the found sequence length with the maximum and overwrites the result if its length is greater.
     *
     * @param checkedSize value of the length of the found sequence.
     * @param i           first entry index of sequence.
     * @param j           second entry index of sequence.
     */
    private void checkLength(int checkedSize, int i, int j) {
        if (checkedSize > sequenceSize) {
            setResult(byteList.subList(i, i + checkedSize), i, j);

            try {
                Thread.sleep(Constant.DELAY);
            } catch (InterruptedException e) {
                log.error(Constant.EXCEPTION_INTERRUPTED, e);
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Sets the result of the found sequence.
     *
     * @param subList     list of bytes of found sequence.
     * @param firstIndex  first entry index of sequence.
     * @param secondIndex second entry index of sequence.
     */
    private void setResult(List<Byte> subList, int firstIndex, int secondIndex) {
        sequenceSize = subList.size();
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;
    }
}
