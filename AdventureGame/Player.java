package AdventureGame;

import java.util.Scanner;

public class Player {
    private int damage, healthy, money, rHealthy;
    private String name, cName; // name: player's name, cName: character name
    private Inventory inv;

    Scanner scan = new Scanner(System.in);

    public Player(String name){
        this.name=name;
        this.inv = new Inventory();
    }

    public void selectCha(){
        switch (chaMenu()){
            case 1:
                initPlayer("Samurai",5 ,21 ,15);
                break;
            case 2:
                initPlayer("Archer",7 ,18 ,20);
                break;
            case 3:
                initPlayer("Knight",8 ,24 ,5);
                break;
        }
        System.out.println("You have chosen the " + getcName() + " class.");
    }

    public int chaMenu(){
        System.out.println("Please, choose your character: ");
        System.out.println("1- Samurai \t Damage: 5 \t Healthy: 21 \t Money: 15");
        System.out.println("2- Archer \t Damage: 7 \t Healthy: 18 \t Money: 20");
        System.out.println("3- Knight \t Damage: 8 \t Healthy: 24 \t Money: 5");
        System.out.print("Your choice: ");
        int chaID = scan.nextInt();

        while(chaID < 1 || chaID > 3){
            System.out.println("Please, enter a valid number. (1-2-3)");
            chaID = scan.nextInt();
        }

        return chaID;
    }

    public void initPlayer(String cname, int dmg, int hlthy, int mny){
        setcName(cname);
        setDamage(dmg);
        setHealthy(hlthy);
        setMoney(mny);
        setrHealthy(hlthy);
    }


    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getHealthy() {
        return healthy;
    }
    public void setHealthy(int healthy) {
        this.healthy = healthy;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getcName() {
        return cName;
    }
    public void setcName(String cName) {
        this.cName = cName;
    }
    public Inventory getInv() {
        return inv;
    }
    public void setInv(Inventory inv) {
        this.inv = inv;
    }
    public int getrHealthy() {
        return rHealthy;
    }
    public void setrHealthy(int rHealthy) {
        this.rHealthy = rHealthy;
    }

    public int totalDamage(){
        return this.getDamage() + this.getInv().getDamage();
    }
}
