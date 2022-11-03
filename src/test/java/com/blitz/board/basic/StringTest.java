package com.blitz.board.basic;

public class StringTest {
    private static final int MAX_LOOP_COUNT = 50_000;

    public static void main(String[] args) {

        // StringBuilder
        StringBuilder builder = new StringBuilder();
        MadClock builderClock = new MadClock();
        builderClock.startClock();
        for (int loop = 1; loop <= MAX_LOOP_COUNT; loop++) {
            builder.append("mad").append(loop).append("play");
        }
        builderClock.stopClock();
        builderClock.printResult("StringBuilder");

        // StringBuffer
        StringBuffer buffer = new StringBuffer();
        MadClock bufferClock = new MadClock();
        bufferClock.startClock();
        for (int loop = 1; loop <= MAX_LOOP_COUNT; loop++) {
            buffer.append("mad").append(loop).append("play");
        }
        bufferClock.stopClock();
        bufferClock.printResult("StringBuffer");

        // String
        String str = "";
        MadClock stringClock = new MadClock();
        stringClock.startClock();
        for (int loop = 1; loop <= MAX_LOOP_COUNT; loop++) {
            str += "mad" + loop + "play";
        }
        stringClock.stopClock();
        stringClock.printResult("String");
    }
}

/**
 * 시간 측정, 결과 출력 클래스
 *
 * @author madplay
 */
class MadClock {
    private long startTime;
    private long endTime;

    public void startClock() {
        startTime = System.nanoTime();
    }

    public void stopClock() {
        endTime = System.nanoTime();
    }

    public void printResult(String clockName) {
        System.out.printf("%s" + ": %.3f seconds %n",
                clockName, (endTime - startTime) / (double) 1_000_000_000);
    }
}