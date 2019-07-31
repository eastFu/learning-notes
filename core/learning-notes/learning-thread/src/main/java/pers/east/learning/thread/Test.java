package pers.east.learning.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    private static void getData(ArrayList<String> arrayList , StringBuilder data, ArrayList<String> arrayListData, int length) {
        for (int i = 0; i < arrayList.size(); i++) {
            data.append(arrayList.get(i));
            ArrayList<String> newArrayList = new ArrayList<>(arrayList);
            newArrayList.remove(i);
            getData(newArrayList, data, arrayListData, length);
        }
        if (arrayList.size() == 0) {
            arrayListData.add(data.toString());
        }
        if (data.length() != 0) {
            data.deleteCharAt(data.length() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> arrayList = Arrays.asList("192.168.1.1","192.168.1.2","192.168.1.3");
    }
}
