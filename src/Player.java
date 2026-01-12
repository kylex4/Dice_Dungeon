public class Player {
    private String name;
    private int health;
    private int defense;
    private int stagger;
    private int level;
    private int pips;


    public Player(String name){
        this.name = name;
        this.health = 30;
        this.defense = 1;
        this.stagger = 15;
        this.level = 1;
        this.pips = 0;
        this.reroll = 2;
    }



    public int roll(Dice dice_type){

    }
}
