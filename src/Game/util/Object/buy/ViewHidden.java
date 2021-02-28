package Game.util.Object.buy;

import Game.WindowInitializer;
import Game.util.Player.Player;

public class ViewHidden extends ObjectBuy{

    private final WindowInitializer window;

    public ViewHidden(WindowInitializer window) {
        super(300);
        this.window = window;
    }

    @Override
    public String toString() {
        return "View The Hidden: price = " + price;
    }

    @Override
    public void use(Player player) {
            window.showBomb(false);
        }

}

