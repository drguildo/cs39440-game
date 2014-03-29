package uk.ac.aber.cs39440.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import uk.ac.aber.cs39440.game.states.MenuState;
import uk.ac.aber.cs39440.game.states.OptionsState;
import uk.ac.aber.cs39440.game.states.PlayState;

/**
 * @author Simon Morgan
 *
 */
public class Game extends StateBasedGame {
    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new Game());
            //container.setTargetFrameRate(100);
            //container.setVSync(true);
            container.setVerbose(false);
            container.setShowFPS(false);
            container.setMinimumLogicUpdateInterval(20);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public Game() {
        super("Game");
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(new MenuState());
        addState(new PlayState());
        addState(new OptionsState());
    }
}
