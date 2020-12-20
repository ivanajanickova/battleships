public class Player {

    private String name;
    private int score = 0;
    private boolean isFirst = false;
    private boolean isChangedScoring = false;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void changeScoring(boolean b){
        this.isChangedScoring = b;
    }
    public void addScore(int score){
        if(!isChangedScoring || !isFirst) {
            this.score = this.score + score;
        }
        else {
            this.score = this.score + score/2;
        }
    }


    public void setFirst(boolean b){
        isFirst = true;
    }

    public String getName() {
        return name;
    }

    public Player(String name){
        this.name = name;
    }


}

