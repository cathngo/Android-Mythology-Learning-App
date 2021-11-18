package au.edu.unsw.infs3634.unswgamifiedlearningapp;

public class PageTracker {
    /**This class includes static global variables to keep track of the user's progress on different learning modules
     * is set to false if the uses has not finished that page of the module, true once they have finished it.
     * All variables reset to false upon log out to refresh for the next user**/

    static boolean greekOne = false;
    static boolean greekTwo = false;
    static boolean greekThree= false;
    static boolean greekFour = false;
    static boolean greekQuiz = false;

    static boolean egyptianOne = false;
    static boolean egyptianTwo = false;
    static boolean egyptianThree = false;
    static boolean egyptianFour = false;
    static boolean egyptianFive = false;
    static boolean egyptianQuiz= false;

    static boolean romanOne = false;
    static boolean romanTwo = false;
    static boolean romanThree = false;
    static boolean romanFour = false;
    static boolean romanQuiz = false;

    public static void resetPageTracker() {
        greekOne = false;
        greekTwo = false;
        greekThree= false;
        greekFour = false;
        greekQuiz = false;

        egyptianOne = false;
        egyptianTwo = false;
        egyptianThree = false;
        egyptianFour = false;
        egyptianFive = false;
        egyptianQuiz= false;

         romanOne = false;
         romanTwo = false;
         romanThree = false;
         romanFour = false;
         romanQuiz = true;

    }

}
