package AdventureGame.Locations.Battle;

import AdventureGame.Obstacles.Bear;
import AdventureGame.Player;

public class River extends BattleLoc {

    public River(Player player){
        super(player, "River", new Bear(), "Water" ,3);
    }
}
