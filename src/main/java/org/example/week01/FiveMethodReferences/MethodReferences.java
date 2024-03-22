package org.example.week01.FiveMethodReferences;

import java.util.Arrays;

public class MethodReferences {

    interface MyTransformingType {
        int transform(int a);
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};

        MyTransformingType doubleValue = (x) -> x * 2;

        int[] doubled = map(arr, doubleValue::transform);
        System.out.println(Arrays.toString(doubled));



    }

    public static int[] map(int[] arr, MyTransformingType op) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = op.transform(arr[i]);
        }
        return result;
    }


}
