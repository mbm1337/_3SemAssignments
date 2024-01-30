package org.example.week01.TwoFunctionalProgramming;

public class FunctionalProgramming {

    interface MyTransformingType {
        int transform(int a);
    }

    interface MyValidatingType {
        boolean validate(int a);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};

        MyTransformingType negate = (int a) -> -a;

        MyValidatingType isEven = (int a) -> a % 2 == 0;
        MyValidatingType isOdd = (int a) -> a % 2 != 0;

        for (int i : filter(arr, isOdd)) {
            System.out.println(i);
        }


    }

    public static int[] map(int[] arr, MyTransformingType op) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = op.transform(arr[i]);
        }
        return result;
    }

    public static int[] filter(int[] arr, MyValidatingType op) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (op.validate(arr[i])) {
                result[i] = arr[i];
            }
        }
        return result;
    }


}
