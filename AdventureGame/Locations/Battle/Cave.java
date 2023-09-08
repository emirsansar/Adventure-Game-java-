package AdventureGame.Locations.Battle;

import AdventureGame.Obstacles.Zombie;
import AdventureGame.Player;

public class Cave extends BattleLoc {

    public Cave(Player player){
        super(player, "Cave", new Zombie(), "Food",3);
    }
}
