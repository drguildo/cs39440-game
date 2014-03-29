package uk.ac.aber.cs39440.experiments;

import java.awt.Font;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class Menus extends BasicGame {
    private String[] options = { "Start Game", "Options", "Quit" };
    private int menuHeight = 0;
    private int selected = 0;

    private TrueTypeFont font;

    public Menus() {
        super("Menus");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        font = new TrueTypeFont(new Font("Arial", Font.BOLD, 48), true);
        menuHeight = font.getLineHeight() * options.length;
    }

    @Override
    public void update(GameContainer container, int delta)
            throws SlickException {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(GameContainer container, Graphics g)
            throws SlickException {
        int stringMiddle;

        for (int i = 0; i < options.length; i++) {
            stringMiddle = font.getWidth(options[i]) / 2;
            if (i == selected) {
                font.drawString((container.getWidth() / 2) - stringMiddle,
                                (container.getHeight() / 2) - (menuHeight / 2)
                                        + i * font.getLineHeight(), options[i],
                                Color.white);
            } else {
                font.drawString((container.getWidth() / 2) - stringMiddle,
                                (container.getHeight() / 2) - (menuHeight / 2)
                                        + i * font.getLineHeight(), options[i],
                                Color.gray);
            }
        }
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
            if (selected == 2) {
                System.exit(0);
            }
            break;
        }
        if (selected < 0)
            selected = options.length - 1;
        if (selected >= options.length)
            selected = 0;
        System.out.println(selected);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new Menus());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

}
