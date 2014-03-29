package uk.ac.aber.cs39440.game.states;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState {
    public static final int ID = 0;
    private StateBasedGame game;

    private String[] entries = { "Start Game", "Options", "Quit" };
    private int menuHeight;
    private int selected = 0;

    private TrueTypeFont font;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        this.game = game;

        font = new TrueTypeFont(new Font("Arial", Font.BOLD, 48), true);
        menuHeight = font.getLineHeight() * entries.length;
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
        case Input.KEY_UP:
            selected--;
            break;
        case Input.KEY_DOWN:
            selected++;
            break;
        case Input.KEY_ESCAPE:
            System.exit(0);
            break;
        case Input.KEY_ENTER:
            if (selected == 0) {
                game.enterState(PlayState.ID);
            }
            if (selected == 1) {
                game.enterState(OptionsState.ID);
            }
            if (selected == 2) {
                System.exit(0);
            }
            break;
        }
        if (selected < 0) {
            selected = entries.length - 1;
        } else if (selected >= entries.length) {
            selected = 0;
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        int stringMiddle;

        for (int i = 0; i < entries.length; i++) {
            stringMiddle = font.getWidth(entries[i]) / 2;
            if (i == selected) {
                font.drawString((container.getWidth() / 2) - stringMiddle,
                                (container.getHeight() / 2) - (menuHeight / 2)
                                        + i * font.getLineHeight(), entries[i],
                                Color.white);
            } else {
                font.drawString((container.getWidth() / 2) - stringMiddle,
                                (container.getHeight() / 2) - (menuHeight / 2)
                                        + i * font.getLineHeight(), entries[i],
                                Color.gray);
            }
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
    }

}
