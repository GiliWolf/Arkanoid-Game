//Gili Wolf 315144907

/**.
 * this class represent a counter by an int.
 */
public class Counter {
    private int counter;

    /**
     * constructor, initialise the counter to zero.
     */
    public Counter() {
        this.counter = 0;
    }
    /**
     * add number to current count.
     * @param number num to add
     */
    void increase(int number) {
        counter += number;
    }
    /**
     * subtract number from current count.
     * @param number num to subtract
     */
    void decrease(int number) {
        counter -= number;
    }

    /**
     * get current count.
     * @return counter num value
     */
    public int getValue() {
        return counter;
    }
}