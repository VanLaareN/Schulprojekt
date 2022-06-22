public class Permutations{

    //Recursive helper function - gets recursion started.
  public static int permute(String s) {
    if (s == null) return 0;
    return permute("", s);
  }
 
  private static int permute(String c, String r) {
 
    //If no letters remain in r, we have a solution.  Print
    //it and return 1 to count it.
    if (r.length() == 0) {
      System.out.println(c);
      return 1;
    }
 
    //Recurse once for each remaining letter
    //in r, adding each to the current string.
    //Pass all letters but the added one into
    //the recursive call as remaining letters.
    //Also, sum up the number of solutions.
    int sum = 0;
    for (int i = 0; i < r.length(); ++i) {
      sum += permute(c + r.charAt(i), 
        r.substring(0, i) + r.substring(i + 1));
    }
    return sum;
  }
 
  public static void main(String[] args) {
    System.out.println("Permutation Count: " + permute("abcd"));
  }
}
