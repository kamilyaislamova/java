package search;

public class BinarySearchClosestI {

    public static int LowerBoundRecursion(int[] array, int x, int l, int r) {
        //Base: let array[array.length] be +infinity and array[0 - 1] = -infinity
        //P: array sorted non-descending && l <= r && array[r] >= x && array[l - 1] < x
        // length = n
        //Q: array[l] >= x && array[l - 1] < x (l - what we are trying to find)

        if (r - l == 0) {
            //P1: P && cond => Q => l - answer
            //length' = 1 => program time = O(log n)
            return l;
        }
        //else: P2: P && !cond => l < r

        //P: l < r
        int m = (r + l) / 2;
        //Q: l <= m < r

        if (array[m] >= x) {
            //P1: P && cond =>
            //array sorted non-descending && l <= m && array[m] >= x && array[l - 1] < x
            //length' = length / 2 because length = (r + l)
            return LowerBoundRecursion(array, x, l, m);
        }
        //else: P2: P && !cond =>
        //array sorted non-descending && m + 1 <= r && array[r] >= x && array[m] < x
        //length' = length / 2 because length = (r + l)
        return LowerBoundRecursion(array, x, m + 1, r);
    }

    public static int BinarySearchRecursion(int[] array, int x) {

        //P: array sorted non-descending and length = n
        int l1 = LowerBoundRecursion(array, x, 0, array.length);
        //Q: array[l1] >= x && array[l1 - 1] < x
        //program time = O(log n)

        //P: array sorted non-descending and length = n
        int l2 = LowerBoundRecursion(array, array[Math.max(l1 - 1, 0)], 0, array.length);
        //Q: array[l2] >= x && array[l2 - 1] < x
        //program time = O(log n)

        //P': array[array.length - 1] is fictive so we check that we don't use it as actual array & P
        //It can be array.length - 1 if any members >= x, that is why the last is the closest
        if (l1 == array.length - 1) {
            l1 -= 1;
        }
        //Q': array[l1] - the first in a row >= x (or any members >= x so l1 is the closest because of non-descending)

        //P: array[l1] - the first in a row >= x, array[l2] - the last in a row < x
        return CheckClosest(array, x, l1, l2);
        //Q: returned index shows closest in value to x
        //program time = O(2 log n) = O(log n)
    }

    public static  int LowerBound(int[] array, int x) {
        //P: array sorted non-descending and length = n
        //Q: array[l] >= x && array[l - 1] < x (l - what we are trying to find)

        //P: 0 <= array.length && array[0] <= array[array.length]
        int l = 0, r = array.length;
        //Q: l <= r && array[l] <= array[r]

        //invariant (P): array[r] >= x && array[l - 1] < x && l < r
        //Base: let array[array.length] be +infinity and array[0 - 1] = -infinity
        while (r != l){

            //P: l < r
            int m = (r + l) / 2;
            //Q: l <= m < r

            //P': array[r] >= x && array[l - 1] < x && l < r && P
            if (array[m] >= x) {
                //P1: cond && P' && P
                r = m;
                //Q1: cond => array[r'] >= x
                //array[l' - 1] < x because we haven't changed 'l'
                //length' = length / 2 because length = (r + l)
            } else {
                // P2: !cond && P' && P
                l = m + 1;
                //Q2: !cond => array[m] < x => a[l' - 1] < x
                //array[r'] >= x because we haven't changed 'r'
                //length' = length / 2 because length = (r + l)
            }
            //Q1 -> and Q2 -> Q': array[r'] >= x && array[l'] < x && l' < r' =>
            //P: array[r] >= x && array[l] < x && l < r && length' = n / 2
        }
        //(array[r] >= x && array[l - 1] < x  r == l) => array[l] >= x && array[l - 1] < x
        // r == l => length = 1
        //Q: array[l] >= x && array[l - 1] < x && program time = O(log n)
        return l;
    }

    public static int BinarySearchIterative(int[] array, int x) {

        //P: array sorted non-descending and length = n
        int l1 = LowerBound(array, x);
        //Q: array[l1] >= x && array[l1 - 1] < x
        //program time = O(log n)

        //P: array sorted non-descending and length = n
        int l2 = LowerBound(array, array[Math.max(l1 - 1, 0)]);
        //Q: array[l2] >= xFictive && array[l2 - 1] < xFictive
        //program time = O(log n)

        //P': array[array.length - 1] is fictive so we check that we don't use it as actual array & P
        //It can be array.length - 1 if any members >= x, that is why the last is the closest
        if (l1 == array.length - 1) {
            l1 -= 1;
        }
        //Q': array[l1] - the first in a row >= x (or any members >= x so l1 is the closest because of non-descending)

        //P: array[l1] - the first in a row >= x (or any members >= x and l1 - is the closest), array[l2] - the last in a row < x
        return CheckClosest(array, x, l1, l2);
        //Q: returned index shows closest in value to x
        //program time = O(2 log n) = O(log n)
    }

    public static int CheckClosest(int[] array, int x, int l1, int l2) {

        //P: array[l1] - the first in a row >= x, array[l2] - the last in a row < x
        if (Math.abs(array[l1] - x) >= Math.abs(x - array[l2])) {
            //P1: P && cond
            //Q1: l2 - closest in value to x
            return l2;
        }
        //else: P2: P && !cond
        return l1;
        //Q2: l1 - closest in value to x

        // Q1, Q2 -> Q: returned index shows closest in value to x
    }

    public static void main(String[] args) {

        //P: args consists of sought-for x and members of array
        if (args.length == 0) {
            //cond && P -> no such sought-for element
            throw new RuntimeException("There is no first element");
        }
        //else: !cond && P ->
        //Q: the first element is sought-for

        //P: the first element of args must be x
        int x = Integer.parseInt(args[0]);
        //Q: x = args[0] - we are trying to find

        //P: args.length > 0
        int arrayLength = args.length;
        int[] a = new int[arrayLength];
        a[arrayLength - 1] = Integer.MAX_VALUE;
        //Q: there is always minimum one member os array and it is >= any x && array consists of non-descending elements

        //P: args[i] && i > 0 - string version of massive, a[arrayLength - 1] = possible maximum
        for (int i = 0; i < arrayLength - 1; i++) {
            a[i] = Integer.parseInt(args[i + 1]);
        }
        //Q: a[i] && i = [0, arrayLength - 1) are members of array, a[arrayLength - 1] = possible maximum
        //a - non-descending

        //P: a - non-descending, the last member >= any of x
        int result1 = BinarySearchClosestI.BinarySearchIterative(a, x);
        //Q: returned index shows closest in value to x and array a hasn't changed

        //P: a - non-descending, the last member >= any of x
        int result2 = BinarySearchClosestI.BinarySearchRecursion(a, x);
        //Q: returned index shows closest in value to x

        //P: two results show closest in value to x
        if (result1 != result2) {
            //P && cond -> result is incorrect
            throw new RuntimeException("Two values are not equal");
        }
        //Q: result is correct
        System.out.println(result1);
    }
}
