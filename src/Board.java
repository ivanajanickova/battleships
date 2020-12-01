import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Board {

    private int width;
    private int length;
    private final HashMap<String, Integer> BOATS = new HashMap<>() {{
        put("Carrier", 5);
        put("Battleship", 4);
        put("Submarine", 3);
        put("Destroyer", 2);
    }};
    //STORE BOATS in a hash map of string with
    private HashMap<Character, CoordinateArray> boatPlacement = new HashMap<>();
    private Character[][] board;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int height) {
        this.length = height;
    }

    public HashMap<Character, CoordinateArray> getBoatPlacement() {
        return boatPlacement;
    }

    public void setBoatPlacement(HashMap<Character, CoordinateArray> boatPlacement) {
        this.boatPlacement = boatPlacement;
    }

    public HashMap<String, Integer> getBOATS() {
        return BOATS;
    }

    public Character[][] getBoard() {
        return board;
    }

    public void setBoard(Character[][] board) {
        this.board = board;
    }

    public Board(int width, int length){
        if(width<6 || length<6){
            JOptionPane.showMessageDialog(null, "Please increase the board dimensions. \n " +
                    "Minimal size is 6x6.");
            //method that allows user to re-Enter the input
        }
        else{
            this.setWidth(width);
            this.setLength(length);
            this.generateBoatPlacement();
            this.createBoard();
        }
    }

    public Board(File file){
        this.readFile(file);
        if(width == 0 || length == 0){
            JOptionPane.showMessageDialog(null, "The file is not valid.");
        }
        else if(width<6 || length<6){
            JOptionPane.showMessageDialog(null, "Please increase the board dimensions. \n " +
                    "Minimal size is 6x6.");
            //method that allows user to re-Enter the input
        }
        else {
            this.createBoard();
        }
    }


    public void createBoard(){
        //creates board represented as 2D Array of Characters: '*' if sea, 'C' if Carrier ship, 'D' if Destroyer, etc.
        Character[][] board= new Character[length][width];
        for(int i=0; i<length; i++){
            for(int j=0; j<width; j++){
                board[i][j] = '*';
            }

        }
        for(Character i : boatPlacement.keySet()){
            for(Coordinate c : boatPlacement.get(i).getCoordinateArray()){
                board[c.getRow()][c.getCol()] = i;
            }
        }
        this.setBoard(board);
    }

    public void generateBoatPlacement(){
        //Populate the HashMap of Boat Character + List of Coordinates (in case no user's input file is provided)
        Random rand = new Random();
        for(String i : BOATS.keySet()) {
            int rowCoordinate = length;
            int colCoordinate = width;
            CoordinateArray coordinates = new CoordinateArray(BOATS.get(i));
            //boolean b - decides if boat is placed vertically/horizontally
            boolean b = rand.nextBoolean();
            //Vertical boat placement
            if(b){
                while((rowCoordinate + BOATS.get(i)) >= length ||
                !isAvailable(coordinates)){
                    rowCoordinate = rand.nextInt(length);
                    colCoordinate = rand.nextInt(width);
                    coordinates.generateCoordinateArray(new Coordinate(rowCoordinate, colCoordinate),
                            BOATS.get(i), "Vertical");
                }
            }
            //Horizontal boat placement
            else{
                while((colCoordinate + BOATS.get(i)) >= width ||
                        !isAvailable(coordinates)){
                    rowCoordinate = rand.nextInt(length);
                    colCoordinate = rand.nextInt(width);
                    coordinates.generateCoordinateArray(new Coordinate(rowCoordinate, colCoordinate),
                            BOATS.get(i), "Horizontal");
                }
            }
            boatPlacement.put(i.charAt(0), coordinates);
        }
    }

    public void readFile(File file){
        //Reads file, sets dimension of board
        //Calls parseLine method which populates the boatPlacement HashMap
        int lineNum = 0;
        String line;
        try(Scanner scan = new Scanner(new FileInputStream(file))) {
            try {
                while(scan.hasNext()){
                    if(lineNum == 0) {
                        this.width = scan.nextInt();
                        this.length = this.width;
                        lineNum++;
                    }
                    else if(lineNum <= 5){
                        line = scan.nextLine();
                        this.parseLine(line);
                        lineNum++;
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "The file is not valid.");
                        //call method that will redirect back to option window
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void parseLine(String line){
        //Populates boatPlacement HashMap
        String[] splittedLine = line.split(";");
        int numOfCoordinates= 0; //check if the number of Coordinates matches the length of a boat
        if (BOATS.containsKey(splittedLine[0])) {
            CoordinateArray coordinateArray = new CoordinateArray(BOATS.get(splittedLine[0]));
            for (int i = 1; i < splittedLine.length; i++) {
                String[] coordinatesStr = splittedLine[i].split("\\*");
                int rowCoordinate = Integer.parseInt(coordinatesStr[0]);
                int colCoordinate = Integer.parseInt(coordinatesStr[1]);
                Coordinate c = new Coordinate(rowCoordinate-1, colCoordinate-1);
                coordinateArray.put(c, i-1);
                numOfCoordinates++;
            }
            if (coordinateArray.isValid() && this.isAvailable(coordinateArray) &&
            coordinateArray.getCoordinateArray().length == numOfCoordinates){
                boatPlacement.put(splittedLine[0].charAt(0),coordinateArray);
            }
            else {
                JOptionPane.showMessageDialog(null, "The file is not valid.");
                //call method that will redirect back to option window
            }
        }
        else {
            System.out.println("todo");
            //call method that will redirect back to option window
        }
    }

    public boolean isAvailable(CoordinateArray coordinates){
        if (coordinates.isEmpty()){
            return false;
        }
        else if(boatPlacement.isEmpty()){
            return true;
        }
        else {
            for(Coordinate i : coordinates.getCoordinateArray()){
                for(CoordinateArray j : boatPlacement.values()){
                    for(Coordinate c : j.getCoordinateArray()){
                        if(i.isEqual(c) || i.getRow() > this.length || i.getCol() > this.width){
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

}
