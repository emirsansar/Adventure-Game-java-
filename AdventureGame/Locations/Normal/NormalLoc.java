package AdventureGame.Locations.Normal;

import AdventureGame.Locations.Location;
import AdventureGame.Player;

public abstract class NormalLoc extends Location {

    NormalLoc(Player player, String name) {
        super(player);
        this.name=name;
    }

    public boolean getLocation(){
        return true;
    }

}
