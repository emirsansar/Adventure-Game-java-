package AdventureGame;

import AdventureGame.Locations.Battle.*;
import AdventureGame.Locations.Normal.*;
import AdventureGame.Locations.Location;

import java.util.Scanner;

public class Game {
    Player player;
    Location location;
    Scanner scan = new Scanner(System.in);

    public void login(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to the Adventure Game!");
        System.out.println("You must all the loots from Cave, Forest and Firewood to win the game! ");
        System.out.print("Please, enter your name: ");
        String playerName = scan.nextLine();
        player = new Player(playerName);
        player.selectCha();
        start();
    }

    public void start(){
        while(true){
            System.out.println();
            System.out.println("========================");
            System.out.println();
            System.out.println("Please, choose the location you want to go: ");
            System.out.println("1. Safe House ---> A safe place for you.");
            System.out.println("2. Cave ---> Defeat the Zombies and get the loot!");
            System.out.println("3. Forest ---> Defeat the Vampires and get the loot!");
            System.out.println("4. River ---> Defeat the bears and get the loot!");
            System.out.println("5. Tool Shop ---> You can buy weapon or armor.");
            System.out.println("6. Exit Game. ");

            System.out.print("Your choice: ");
            int selLoc = scan.nextInt();

            while (selLoc<0 || selLoc>6){
                System.out.print("Please, enter a valid location: ");
                selLoc = scan.nextInt();
            }

            switch (selLoc){
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new Cave(player);
                    break;
                case 3:
                    location = new Forest(player);
                    break;
                case 4:
                    location = new River(player);
                    break;
                case 5:
                    location = new ToolStore(player);
                    break;
            }

            if (selLoc == 6){
                System.out.println("Exiting game...");
                break;
            }

            if( !location.getLocation() ){   // The getLocation method shows whether the player went to that location and died.
                System.out.println("Game over!");    // If the player dies in the locaiton, the method returns false and the game is over.
                break;
            }

            if ( checkWin() ){
                System.out.println("Congratulations. You won the game by collecting all the loots.");
                break;
            }
        }

    }

    public boolean checkWin() {
        System.out.println();
        boolean hasWater = player.getInv().isWater();
        boolean hasFood = player.getInv().isFood();
        boolean hasFirewood = player.getInv().isFirewood();

        System.out.println("Has Water: " + hasWater);
        System.out.println("Has Food: " + hasFood);
        System.out.println("Has Firewood: " + hasFirewood);

        return (hasWater && hasFood && hasFirewood);
    }


}
