package uk.ac.aber.cs39440.experiments;

import java.awt.Font;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;

public class JitteryText extends BasicGame {
    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new JitteryText());
            container.setShowFPS(false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private float offsetx, offsety;
    private TrueTypeFont font;
    private Input input;

    public JitteryText() {
        super("Test Case");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        offsetx = 0;
        offsety = 0;
        font = new TrueTypeFont(new Font("Arial", Font.BOLD, 24), true);
        input = container.getInput();
    }

    @Override
    public void render(GameContainer container, Graphics g)
            throws SlickException {
        g.translate(-offsetx, -offsety);
        g.draw(new Circle(100.0f, 100.0f, 12.0f));
        font.drawString(offsetx, offsety, "Test");
    }

    @Override
    public void update(GameContainer container, int delta)
            throws SlickException {
        if (input.isKeyDown(Input.KEY_LEFT)) {
            offsetx--;
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            offsetx++;
        }
        if (input.isKeyDown(Input.KEY_UP)) {
            offsety--;
        }
        if (input.isKeyDown(Input.KEY_DOWN)) {
            offsety++;
        }
    }

}
