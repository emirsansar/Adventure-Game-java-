package AdventureGame.Locations.Normal;

import AdventureGame.Player;

import java.util.Scanner;

public class ToolStore extends NormalLoc {

    Scanner scan = new Scanner(System.in);

    public ToolStore(Player player){
        super(player, "Tool Shop");
    }

    public boolean getLocation(){
        System.out.println("You entered tool shop. You have: "+ player.getMoney() + " money.");
        System.out.println("1. Weapons: ");
        System.out.println("2. Armors: ");
        System.out.println("3. Exit. ");
        System.out.print("Your choice: ");
        int selTool = scan.nextInt();

        if( selTool<1 && selTool>3 ){
            System.out.println("Please, enter valid option.");
            selTool = scan.nextInt();
        }

        int selItemID;

        switch (selTool){
            case 1:
                selItemID = weaponMenu();
                buyWeapon(selItemID);
                break;
            case 2:
                selItemID = armorMenu();
                buyArmor(selItemID);
                break;
            case 3:
                break;
        }
        return true;
    }

    public int weaponMenu(){
        System.out.println("1. Gun ---> \t Damage: 2 / Cost: 25");
        System.out.println("2. Sword ---> \t Damage: 3 / Cost: 35");
        System.out.println("3. Rifle ---> \t Damage: 7 / Cost: 45");
        System.out.println("4. Cancel.");
        System.out.print("Your choice:");
        int selWeaponID = scan.nextInt();

        if (selWeaponID<1 || selWeaponID>4){
            System.out.print("Please, enter valid option.");
            selWeaponID = scan.nextInt();
        }

        return selWeaponID;
    }

    public void buyWeapon(int itemID){
        int damage=0, cost=0;
        String wName = null;

        switch (itemID){
            case 1:
                damage = 2;
                wName = "Gun";
                cost = 25;
                break;
            case 2:
                damage = 3;
                wName = "Sword";
                cost = 35;
                break;
            case 3:
                damage = 7;
                wName = "Rife";
                cost = 45;
                break;
        }

        if(player.getMoney() >= cost){
            player.getInv().setDamage(damage);
            player.getInv().setwName(wName);
            player.setMoney(player.getMoney()-cost);
            System.out.println("You bought the " + wName +". Your new damage: " + player.totalDamage() );
            System.out.println("The money you have: " + player.getMoney());
        }
        else{
            System.out.println("You don't have enough money to buy " + wName);
        }
    }


    public int armorMenu(){
        System.out.println("1. Leather Armor ---> \t Protection: 1 / Cost: 15");
        System.out.println("2. Chain Armor ---> \t Protection: 3 / Cost: 25");
        System.out.println("3. Iron Armor ---> \t Protection: 6 / Cost: 40");
        System.out.println("4. Cancel.");
        System.out.print("Your choice:");
        int selArmorID = scan.nextInt();

        if (selArmorID<1 || selArmorID>4){
            System.out.print("Please, enter valid option.");
            selArmorID = scan.nextInt();
        }

        return selArmorID;
    }

    public void buyArmor(int itemID){
        int protection=0, cost=0;
        String aName = null;

        switch (itemID){
            case 1:
                protection = 1;
                aName = "Leather Armor";
                cost = 15;
                break;
            case 2:
                protection = 3;
                aName = "Chain Armor";
                cost = 25;
                break;
            case 3:
                protection = 5;
                aName = "Iron Armor";
                cost = 40;
                break;
        }

        if(player.getMoney() >= cost){
            player.getInv().setArmor(protection);
            player.getInv().setaName(aName);
            player.setMoney(player.getMoney()-cost);
            System.out.println("You bought the " + aName +". Your new protection: +" + player.getInv().getArmor() );
            System.out.println("The money you have: " + player.getMoney());
        }
        else{
            System.out.println("You don't have enough money to buy " + aName);
        }
    }

}
