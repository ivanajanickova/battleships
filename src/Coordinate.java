public class Coordinate {

    private int row;
    private int col;
    private int[] coordinate = new int[2];

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int row, int col) {
        this.coordinate[0] = row;
        this.coordinate[1] = col;
    }

    public Coordinate(int row, int col) {
        this.setRow(row);
        this.setCol(col);
        this.setCoordinate(row, col);
    }

    public boolean isEqual(Coordinate c) {
        return (this.getRow() == c.getRow() && this.getCol() == c.getCol());
    }
}
