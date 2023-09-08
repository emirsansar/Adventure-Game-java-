package AdventureGame.Locations.Normal;

import AdventureGame.Player;

public class SafeHouse extends NormalLoc {

    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    public boolean getLocation(){
        player.setHealthy(player.getrHealthy());
        System.out.println("You have been healed. You are in safe now.");

        return true;
    }
}
