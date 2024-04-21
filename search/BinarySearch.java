package search;

public class BinarySearch {

    public static int BinarySearchRecursion(int[] array, int x, int l, int r) {
        //Base: let array[array.length] be -infinity and array[0 - 1] = +infinity
        //P: array sorted non-ascending && l <= r && array[r] <= x && array[l - 1] > x
        // length = n
        //Q: array[l] <= x && array[l - 1] > x (l - what we are trying to find)

        if (r - l == 0) {
            //P1: P && cond => Q => l - answer
            //length' = 1 => program time = O(log n)
            return l;
        }
        //else: P2: P && !cond => l < r

        //P: l < r
        int m = (r + l) / 2;
        //Q: l <= m < r

        if (array[m] <= x) {
            //P1: P && cond =>
            //array sorted non-ascending && l <= m && array[m] <= x && array[l - 1] > x
            //length' = length / 2 because length = (r + l)
            return BinarySearchRecursion(array, x, l, m);
        }
        //else: P2: P && !cond =>
        //array sorted non-ascending && m + 1 <= r && array[r] <= x && array[m] > x
        //length' = length / 2 because length = (r + l)
        return BinarySearchRecursion(array, x, m + 1, r);
    }

    public static int BinarySearchIterative(int[] array, int x) {
        //P: array sorted non-ascending and length = n
        //Q: array[l] <= x && array[l - 1] > x (l - what we are trying to find)

        //P: 0 <= array.length && array[0] >= array[array.length]
        int l = 0, r = array.length;
        //Q: l <= r && array[l] >= array[r]

        //invariant (P): array[r] <= x && array[l - 1] > x && l < r
        //Base: let array[array.length] be -infinity and array[0 - 1] = +infinity
        while (r != l){

            //P: l < r
            int m = (r + l) / 2;
            //Q: l <= m < r

            //P': array[r] <= x && array[l - 1] > x && l < r
            if (array[m] <= x) {
                //P1: cond && P'
                r = m;
                //Q1: cond => array[r'] <= x
                //array[l' - 1] > x because we haven't changed 'l'
                //length' = length / 2 because length = (r + l)
            } else {
                // P2: !cond && P'
                l = m + 1;
                //Q2: !cond => array[m] > x => a[l' - 1] > x
                //array[r'] <= x because we haven't changed 'r'
                //length' = length / 2 because length = (r + l)
            }
            //Q1 -> and Q2 -> Q': array[r'] <= x && array[l'] > x && l' < r' =>
            //P: array[r] <= x && array[l] > x && l < r && length' = n / 2
        }
        //(array[r] <= x && array[l - 1] > x  r == l) => array[l] <= x && array[l - 1] > x
        // r == l => length = 1
        //Q: array[l] <= x && array[l - 1] > x && program time = O(log n)
        return l;
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
        int arrayLength = args.length - 1;
        int[] a = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            a[i] = Integer.parseInt(args[i + 1]);
        }
        //Q: array consists of non-descending elements

        //P: a - non-descending, the last member >= any of x
        int result1 = BinarySearch.BinarySearchIterative(a, x);
        //Q: returned index shows closest in value to x and array a hasn't changed

        //P: a - non-descending, the last member >= any of x
        int result2 = BinarySearch.BinarySearchRecursion(a, x, 0, arrayLength);
        //Q: returned index shows closest in value to x

        //P: two results show closest in value to x
        if (result1 != result2)
            throw new RuntimeException("Two values are not equal");
        //Q: result is correct
        System.out.println(result1);

    }
}
