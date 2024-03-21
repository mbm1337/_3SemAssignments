package main.java.org.example.week01.OneLambda;

public class Lambda {

    interface ArithmeticOperation {
        int perform(int a, int b);
    }

    public static void main(String[] args) {
        int[] aArr = {1,2,3,4,5};
        int[] bArr = {6,7,8,9,10};

        ArithmeticOperation addition = (int a, int b) -> a + b;
        ArithmeticOperation subtraction = (a, b) -> a - b;
        ArithmeticOperation multiplication = (int a, int b) -> a * b;
        ArithmeticOperation division = (int a, int b) -> a / b;
        ArithmeticOperation modulo = (int a, int b) -> a % b;
        ArithmeticOperation power = (int a, int b) -> (int) Math.pow(a, b);


        System.out.println(operate(13,32, addition ));
        System.out.println(operate(93,52, subtraction));
        System.out.println(operate(35,75, multiplication));
        System.out.println(operate(427,58, division));
        System.out.println(operate(47,567, modulo));
        System.out.println(operate(7,38, power));

        System.out.println(operateArr(aArr,bArr,addition));
        System.out.println(operateArr(aArr,bArr,subtraction));
        System.out.println(operateArr(aArr,bArr,multiplication));
        System.out.println(operateArr(aArr,bArr,division));
        System.out.println(operateArr(aArr,bArr,modulo));
        System.out.println(operateArr(aArr,bArr,power));






    }

    public static int operate(int a, int b, ArithmeticOperation op){
        return op.perform(a,b);
    }

    public static int operateArr(int[] aArr, int[] bArr, ArithmeticOperation op){
        int result = 0;
        for (int i = 0; i < aArr.length; i++) {
            result += op.perform(aArr[i],bArr[i]);
        }
        return result;
    }

}