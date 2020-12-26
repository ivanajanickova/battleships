import java.util.Arrays;

public class CoordinateArray {

    private Coordinate[] coordinateArray;

    public Coordinate[] getCoordinateArray() {
        return coordinateArray;
    }

    public CoordinateArray(int size){
        //populates array with size corresponding to boat size with zeros
        coordinateArray = new Coordinate[size];
        for(int i=0; i<size; i++){
            coordinateArray[i] = new Coordinate(0,0);
        }
    }

    public void generateCoordinateArray(Coordinate c, int boatSize, String direction){
        int index = 0;
        coordinateArray[index] = c;
        if(direction.equals("Horizontal")){
            for(int i=c.getCol()+1; i<(c.getCol() + boatSize); i++){
                index++;
                coordinateArray[index] = new Coordinate(c.getRow(),i);
            }
        }
        else if(direction.equals("Vertical")){
            for(int i=c.getRow()+1; i<(c.getRow() + boatSize); i++){
                index++;
                coordinateArray[index] = new Coordinate(i, c.getCol());
            }
        }
    }

    public void put(Coordinate c, int index){
        coordinateArray[index] = c;
    }

    public boolean isValid(){
        //checks if Coordinates are not duplicated & checks if Coordinates are grouped
        if(this.isEmpty()){
            return false;
        }
        return this.isGroped() && this.hasDuplicates();
    }

    public boolean hasDuplicates(){
        int numOfEqualCoordinates = 0;
        for(Coordinate c1 : coordinateArray){
            for(Coordinate c2 : coordinateArray){
                //Check for Duplicity
                if(Arrays.equals(c1.getCoordinate(), c2.getCoordinate())){
                    numOfEqualCoordinates++;
                }
            }
            if(numOfEqualCoordinates > 1){
                return false;
            }
            else {
                numOfEqualCoordinates = 0;
            }
        }
        return true;
    }

    public boolean isGroped(){
        for(int i=1; i<coordinateArray.length; i++){
            if(coordinateArray[i-1].getCol() == coordinateArray[i].getCol()){ //Vertical Direction
                if(coordinateArray[i-1].getRow() != coordinateArray[i].getRow() + 1 &&
                        coordinateArray[i-1].getRow() != coordinateArray[i].getRow() - 1 ){
                    return false;
                }
            }
            else if(coordinateArray[i-1].getRow() == coordinateArray[i].getRow()){ //Horizontal Direction
                if(coordinateArray[i -1].getCol() != coordinateArray[i].getCol() + 1 &&
                coordinateArray[i-1].getCol() != coordinateArray[i].getCol() - 1){
                    return false;
                }
            }
            else { //Not vertical nor horizontal
                return false;
            }
        }
        return true;
    }

    public  boolean isEmpty(){
        int zeroVals = 0;
        for(Coordinate c : this.getCoordinateArray()){
            if(c.equals(new Coordinate(0,0))){
                zeroVals++;
            }
        }
        return zeroVals > 1;
    }

    public int length(){
        int lenght = 0;
        for(Coordinate c:this.getCoordinateArray()){
            lenght++;
        }
        return lenght;
    }

    public Coordinate getCoordinateAtIndex(int index){
        return coordinateArray[index];
    }

    public void removeAtIndex(int index){
        Coordinate[] copy = new Coordinate[this.length() - 1];

        for (int i = 0, j = 0; i < this.length(); i++) {
            if (i != index) {
                copy[j++] = this.getCoordinateAtIndex(i);
            }
        }
        this.coordinateArray = copy;
        System.out.println();
    }

    public int size(){
        int size = 0;
        for(Coordinate c : this.getCoordinateArray()){
            size ++;
        }
        return size;
    }
}
