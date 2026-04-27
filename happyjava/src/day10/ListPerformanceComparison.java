package day10;

import java.util.*;

public class ListPerformanceComparison {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        // 1. 끝에 추가 (둘 다 빠름)
        long startTime = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            arrayList.add(i);
        }
        long arrayListAddTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            linkedList.add(i);
        }
        long linkedListAddTime = System.nanoTime() - startTime;

        System.out.println("끝에 추가:");
        System.out.println("ArrayList: " + arrayListAddTime / 1000000 + "ms");
        System.out.println("LinkedList: " + linkedListAddTime / 1000000 + "ms");

        // 2. 중간에 삽입 (LinkedList가 유리)
        startTime = System.nanoTime();
        for(int i = 0; i < 1000; i++) {
            arrayList.add(50000, i);
        }
        long arrayListInsertTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for(int i = 0; i < 1000; i++) {
            linkedList.add(50000, i);
        }
        long linkedListInsertTime = System.nanoTime() - startTime;

        System.out.println("\n중간 삽입:");
        System.out.println("ArrayList: " + arrayListInsertTime / 1000000 + "ms");
        System.out.println("LinkedList: " + linkedListInsertTime / 1000000 + "ms");

        // 3. 인덱스 접근 (ArrayList가 유리)
        startTime = System.nanoTime();
        for(int i = 0; i < 10000; i++) {
            arrayList.get(i);
        }
        long arrayListGetTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for(int i = 0; i < 10000; i++) {
            linkedList.get(i);
        }
        long linkedListGetTime = System.nanoTime() - startTime;

        System.out.println("\n인덱스 접근:");
        System.out.println("ArrayList: " + arrayListGetTime / 1000000 + "ms");
        System.out.println("LinkedList: " + linkedListGetTime / 1000000 + "ms");
    }
}