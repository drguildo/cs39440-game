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

import uk.ac.aber.cs39440.game.Options;

public class OptionsState extends BasicGameState {
    public static final int ID = 2;
    private StateBasedGame game;

    private String[][] entries = {
            {"Number of Rounds", "number_of_rounds"},
            {"Default Shield Strength", "shield_strength"},
            {"Exit", "exit"}
    };
    private int lineHeight;
    private int longestLineWidth = 0;
    private int indentAmount = 0;
    private int selected = 0;
    private TrueTypeFont font;

    private Options opt;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        this.game = game;

        opt = Options.getOptions();

        font = new TrueTypeFont(new Font("Arial", Font.BOLD, 48), true);

        lineHeight = font.getLineHeight();

        for (String[] e : entries) {
            if (font.getWidth(e[0]) > longestLineWidth) {
                longestLineWidth = font.getWidth(e[0]); // Account for the option value.
            }
        }

        indentAmount = (container.getWidth() - longestLineWidth) / 2;
    }

    @Override
    public void keyPressed(int key, char c) {
        int cur;
        switch (key) {
        case Input.KEY_ESCAPE:
            game.enterState(MenuState.ID);
            break;
        case Input.KEY_UP:
            selected--;
            break;
        case Input.KEY_DOWN:
            selected++;
            break;
        case Input.KEY_LEFT:
            cur = opt.getAsInt(entries[selected][1]);
            opt.set(entries[selected][1], String.valueOf(cur-1));
            break;
        case Input.KEY_RIGHT:
            cur = opt.getAsInt(entries[selected][1]);
            opt.set(entries[selected][1], String.valueOf(cur+1));
            break;
        case Input.KEY_ENTER:
            if (selected == 2) {
                game.enterState(MenuState.ID);
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
    public void leave(GameContainer container, StateBasedGame game)
            throws SlickException {
        // TODO: Don't bother saving settings if they're same as defaults?
        opt.save();
        selected = 0;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        // The first menu entry's offset from the top of the window.
        float i = (container.getHeight() - (lineHeight * (entries.length*2))) / 2;
        for (String e[] : entries) {
            if (e[0].compareTo(entries[selected][0]) == 0) {
                font.drawString(indentAmount, i, e[0], Color.white);
            } else {
                font.drawString(indentAmount, i, e[0], Color.gray);
            }
            i += font.getLineHeight();
            if (e[1].compareTo("exit") != 0) {
                String value = opt.get(e[1]);
                float offset = (indentAmount + longestLineWidth) - font.getWidth(value);
                font.drawString(offset, i, value, Color.white);
            }
            i += font.getLineHeight();
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
    }

}
