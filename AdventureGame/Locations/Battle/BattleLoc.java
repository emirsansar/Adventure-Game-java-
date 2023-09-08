package AdventureGame.Locations.Battle;

import AdventureGame.Locations.Location;
import AdventureGame.Obstacles.Obstacle;
import AdventureGame.Player;

import java.util.Random;
import java.util.Scanner;

public abstract class BattleLoc extends Location {
    Obstacle obstacle;
    protected String lootName;
    protected int maxObsCount;
    Scanner scan = new Scanner(System.in);

    public BattleLoc(Player player, String name, Obstacle obstacle, String lootName, int maxObsCount) {
        super(player);
        this.name=name;
        this.obstacle=obstacle;
        this.lootName = lootName;
        this.maxObsCount=maxObsCount;
    }

    public int generateObsCount(){
        Random rand = new Random();
        return rand.nextInt(maxObsCount)+1;
    }

    public boolean getLocation(){
        int obsCount = generateObsCount();

        System.out.println("You are in " + this.getName() + "!");
        System.out.println("There are " + obsCount + " " + obstacle.getName() + "s !");

        String selCase;
        do {
            System.out.print("Press 'B' to battle, 'E' to escape. ");
            selCase = scan.nextLine();
            selCase = selCase.toUpperCase();

            if (!selCase.equals("B") && !selCase.equals("E")) {
                System.out.println("Invalid option! Please, press 'B' to battle, 'E' to escape.");
            }
        } while (!selCase.equals("B") && !selCase.equals("E"));

        if( selCase.equals("B") ){
            if (combat(obsCount)){
                handleLoot();
                return true;
            }
            else{
                System.out.println("You died!");
                return false;
            }
        }

        return true;
    }

    public boolean combat (int obsCount){
        int obsDefaultHealth = obstacle.getHealth();

        for(int i=0; i<obsCount; i++){
            obstacle.setHealth(obsDefaultHealth);
            playerStats();
            obstacleStats();

            while ( player.getHealthy() > 0 && obstacle.getHealth() > 0){
                System.out.println();
                System.out.print("Press 'H' to hit, 'E' to escape.");
                String selCase = scan.nextLine();
                selCase = selCase.toUpperCase();

                if ( !selCase.equals("H") && !selCase.equals("E") ){
                    System.out.println("Invalid option! Please, press 'H' to hit, 'E' to escape.");
                }

                if ( selCase.equals("H") ){
                    playerAttack();
                    System.out.println();
                    if (obstacle.getHealth() > 0) {  // If the player kills the obstacle, it prevents the player from taking damage.
                        enemyAttack();
                    }
                }
                else {
                    break;
                }
            }

            if ( obsCount != 1 ) {
                if (obstacle.getHealth() <=0 && i == obsCount-2){
                    System.out.println("You defeated " + obstacle.getName() + ". A new obstacle appears! ");
                }
                else if (obstacle.getHealth() <=0 && i == obsCount-1){
                    System.out.println("Last " + obstacle.getName() + " is defeated.");
                }
            }
        }

        if ( player.getHealthy() > 0 ) {
            System.out.println();
            System.out.println("You defeated all the " + obstacle.getName() + "s in " + this.getName() + "!");
            handleLoot();  // The region's loot reward is added to the player's inventory.

            int awardBattle = obstacle.getAward()*obsCount;  // Money is added to player's wallet.
            player.setMoney(player.getMoney() + awardBattle);
            System.out.println("You have won " + awardBattle +" money. Your money: " + player.getMoney());
        }
        else {
            return false;
        }

        return true;
    }

    public void playerStats(){
        System.out.println("Character's health: " + player.getHealthy() + ", total damage: " + player.totalDamage() + " money: " + player.getMoney());
    }

    public void obstacleStats(){
        System.out.println(obstacle.getName() + "'s health: " + obstacle.getHealth() + ", total damage: " + obstacle.getDamage() + " award: " + obstacle.getAward());
    }

    public void statsAfterHit(){
        if( obstacle.getHealth() < 0){
            obstacle.setHealth(0);
        }
        System.out.println("Your health: " + player.getHealthy() + " -VS-  " + obstacle.getName() + "'s health: " + obstacle.getHealth() );
    }

    public void playerAttack(){
        System.out.println("You hit!");
        obstacle.setHealth( obstacle.getHealth() - player.totalDamage() );
        statsAfterHit();
    }

    public void enemyAttack(){
        System.out.println(obstacle.getName() + " hit you!");
        int damageTaken = obstacle.getDamage() - player.getInv().getArmor();

        if (damageTaken < 0) {  // If the obstacle's hit is less than the armor protection, it cannot inflict any damage to the player.
            damageTaken = 0;
        }
        player.setHealthy(player.getHealthy() - damageTaken);
        statsAfterHit();
    }

    public void handleLoot(){
        if (this.lootName.equals("Food") && !player.getInv().isFood() ) {
            System.out.println("'" + this.lootName + "' is looted.");
            player.getInv().setFood(true);
        } else if (this.lootName.equals("Water") && !player.getInv().isWater() ) {
            System.out.println("'" + this.lootName + "' is looted.");
            player.getInv().setWater(true);
        } else if (this.lootName.equals("Firewood") && !player.getInv().isFirewood() ) {
            System.out.println("'" + this.lootName + "' is looted.");
            player.getInv().setFirewood(true);
        }
    }
}
