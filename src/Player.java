import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    private String name;
    private int score = 0;
    private final HashMap<Character, Integer> SCORING_MAP = new HashMap<>() {{
        put('C', 2);
        put('B', 4);
        put('S', 6);
        put('D', 8);
    }};

    public int getScore() {
        return score;
    }

    public void changeScoring(){
        this.score = this.score + 2;
    }
    public void addScore(ArrayList<Character> boatsDestroyed, Character boat){
        if(boatsDestroyed != null && boatsDestroyed.contains(boat)){
            this.score = this.score + SCORING_MAP.get(boat)*2;
        }
        else{
            this.score = this.score + SCORING_MAP.get(boat);
            }
    }

    public String getName() {
        return name;
    }

    public Player(String name){
        this.name = name;
    }


}

