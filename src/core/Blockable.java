package core;

interface Blockable {

    /**
     * This method will change the status to be Blocked
     *
     * @return a boolean as to whether they are blocked
     */
    boolean block();

    /**
     * This method will change the status from blocked to active
     *
     * @return a boolean as to whether they are blocked
     */
    boolean unBlock();

}