package com.dfire.grade.manager.bean;

import java.util.*;

/**
 * User:huangtao
 * Date:2016-03-22
 * description：
 */
public class MyNumber implements Comparable<MyNumber> {
    private Map<Integer, Integer> value = new HashMap<>();

    public void setValue(int value) {
        Integer numValue = this.value.get(value);
        if (null == numValue) {
            numValue = 0;
        }
        numValue++;
        this.value.put(value, numValue);
    }

    public int getValue() {
        Set<Map.Entry<Integer, Integer>> set = this.value.entrySet();
        Iterator<Map.Entry<Integer, Integer>> it = set.iterator();
        while (it.hasNext()) {
            return it.next().getKey();
        }
        return 0;
    }

    @Override
    public int compareTo(MyNumber o) {
        if (this.value.get(this.getValue()) - o.value.get(o.getValue()) > 0) {
            return this.getValue() - o.getValue();
        }
        return this.getValue() - o.getValue();
    }

    public static void main(String[] args) {

        List<MyNumber> myNumbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MyNumber myNumber = new MyNumber();
            myNumber.setValue(i);
            myNumbers.add(myNumber);
        }
        for (int i = 0; i < 10; i++) {
            MyNumber myNumber = new MyNumber();
            myNumber.setValue(i);
            myNumbers.add(myNumber);
        }
        //对number进行排序
        Collections.sort(myNumbers);
        for (int i = 0; i < myNumbers.size(); i++) {
            System.out.println(myNumbers.get(i).getValue());
        }

    }

}
