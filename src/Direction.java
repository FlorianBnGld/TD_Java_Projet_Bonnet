public enum Direction {
    // Définition des directions avec un numéro de ligne de frame associé
    NORTH(2),  // Direction nord, correspond à la ligne de frame 2
    SOUTH(0),  // Direction sud, correspond à la ligne de frame 0
    EAST(3),   // Direction est, correspond à la ligne de frame 3
    WEST(1);   // Direction ouest, correspond à la ligne de frame 1

    private int frameLineNumber;

    // Constructeur de l'énumération Direction
    Direction(int frameLineNumber) {
        this.frameLineNumber = frameLineNumber;
    }

    /**
     * Method to obtain the frame line number
     * @return The frame line number
     */
    public int getFrameLineNumber() {
        return frameLineNumber;
    }
}