package AdventureGame.Locations.Battle;

import AdventureGame.Obstacles.Vampire;
import AdventureGame.Player;

public class Forest extends BattleLoc {

    public Forest (Player player){
        super(player, "Forest", new Vampire(), "Firewood" ,2);
    }
}
