import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Ex1Test {

    @Test void testCalBandC() {
        String a = "0000";
        int[] b = {0, 0, 0, 0};
        int[] result = {4, 0};
        Assertions.assertArrayEquals(result, Ex1.calBandC(a, b));
        String a1 = "0000";
        int[] b1 = {1, 2, 3, 4};
        int[] result1 = {0, 0};
        Assertions.assertArrayEquals(result1, Ex1.calBandC(a1, b1));
        String a2 = "0204";
        int[] b2 = {1, 2, 3, 4};
        int[] result2 = {2, 0};
        Assertions.assertArrayEquals(result2, Ex1.calBandC(a2, b2));
        String a3 = "1357";
        int[] b3 = {1, 7, 3, 5};
        int[] result3 = {1, 3};
        Assertions.assertArrayEquals(result3, Ex1.calBandC(a3, b3));
        String a4 = "1234";
        int[] b4 = {4, 3, 2, 1};
        int[] result4 = {0, 4};
        Assertions.assertArrayEquals(result4, Ex1.calBandC(a4, b4));
    }

    @Test void allGuesses(){
        int numdigit=2;
       String [] arr = Ex1.allGuesses(numdigit);
        String check="12";
        boolean b=false;
        for (String s:arr) {
            if(s==check)b=true;
        }
        Assertions.assertFalse(b);


         numdigit=3;
        arr = Ex1.allGuesses(numdigit);
         check="123";
         b=false;
        for (String s:arr) {
            if(s==check)b=true;
        }
        Assertions.assertFalse(b);


        numdigit=4;
        arr = Ex1.allGuesses(numdigit);
        check="1234";
        b=false;
        for (String s:arr) {
            if(s==check)b=true;
        }
        Assertions.assertFalse(b);


        numdigit=5;
        arr = Ex1.allGuesses(numdigit);
        check="12345";
        b=false;
        for (String s:arr) {
            if(s==check)b=true;
        }
        Assertions.assertFalse(b);


        numdigit=6;
        arr = Ex1.allGuesses(numdigit);
        check="123456";
        b=false;
        for (String s:arr) {
            if(s==check)b=true;
        }
        Assertions.assertFalse(b);
    }

    @Test void makeGuess(){
        int numdigit=2;
        String [] arr = Ex1.allGuesses(numdigit);
        int guess=Ex1.makeGuess(arr);
        boolean b=false;
        for (String s:arr) {
            if(s==Integer.toString((guess)))b=true;
        }
        Assertions.assertFalse(b);

    }

    @Test void deleteOptions(){
        int[] guess = {1, 2, 3, 4};
        int[] res = {4,0};
        String[] allGuesses = {"3678", "0256", "1389", "9876", "1256","1234"};
        String[] expected = {"A","A","A","A","A","1234"};
        String[] result = Ex1.deleteOptions(guess, res, allGuesses);
        Assertions.assertArrayEquals(expected, result);

        int[] guess1 = {2, 4, 6, 8};
        int[] res1 = {2,2};
        String[] allGuesses1 = {"3678", "0256", "1389", "9876", "1256","2864"};
        String[] expected1 = {"A","A","A","A","A","2864"};
        String[] result1 = Ex1.deleteOptions(guess1, res1, allGuesses1);
        Assertions.assertArrayEquals(expected1, result1);
    }

    @Test void removeChar(){
        String original = "1234";
        int index = 2;
        String expected = "12x4";
        String result = Ex1.removeChar(original, index);
        Assertions.assertEquals(expected, result);

        String original1 = "2468";
        int index1 = 1;
        String expected1 = "2x68";
        String result1 = Ex1.removeChar(original1, index1);
        Assertions.assertEquals(expected1, result1);
    }

    @Test void arrayToString(){
        int[] array = {1, 2, 3, 4};
        String expected = "1234";
        String result = Ex1.arrayToString(array);
        Assertions.assertEquals(expected, result);

        int[] array1 = {1, 1, 1, 1};
        String expected1 = "1111";
        String result1 = Ex1.arrayToString(array1);
        Assertions.assertEquals(expected1, result1);
    }

    @Test void average (){
        double average = 0;
        long myID = 322351883;
        int numOfDigits = 4;
        for (int i = 0; i < 100; i++) {
            average += Ex1.avmain(myID,numOfDigits);
        }
        System.out.println(average/100);
    }

}