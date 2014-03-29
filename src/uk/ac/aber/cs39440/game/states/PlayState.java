package uk.ac.aber.cs39440.game.states;

import java.awt.Font;
import java.util.Vector;

import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.CollisionListener;
import net.phys2d.raw.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import uk.ac.aber.cs39440.game.Map;
import uk.ac.aber.cs39440.game.Options;
import uk.ac.aber.cs39440.game.SimpleStars;
import uk.ac.aber.cs39440.game.entities.Bullet;
import uk.ac.aber.cs39440.game.entities.DynamicEntity;
import uk.ac.aber.cs39440.game.entities.Entity;
import uk.ac.aber.cs39440.game.entities.Ship;
import uk.ac.aber.cs39440.game.entities.StaticEntity;

public class PlayState extends BasicGameState implements CollisionListener {
    public static final int ID = 1;
    private StateBasedGame game;

    // The amount of damage a bullet does.
    private static final int BULLET_STRENGTH = 2;
    // The amount of time to display the new round message.
    private static final long MSG_DISPLAY_TIME = 3000;

    private Map map;
    private World world;
    private Ship ship1, ship2;

    private Input input;

    private float scale;

    // private AngelCodeFont font;
    private TrueTypeFont font;

    private int round, rounds, s1wins, s2wins;

    private boolean gameOver;

    // The absolute time the game should display the new round message until.
    private long msgDisplayUntil;

    private float height, width;

    private Options opt;

    private SimpleStars stars;

    private boolean paused = false;

    @Override
    public void collisionOccured(CollisionEvent event) {
        Ship ship;
        StaticEntity se;

        Object bodyA = event.getBodyA().getUserData();
        Object bodyB = event.getBodyB().getUserData();

        if (bodyA instanceof Bullet) {
            Bullet b = (Bullet) bodyA;
            if (bodyB instanceof Ship) {
                ship = (Ship) bodyB;
                ship.decreaseShields(BULLET_STRENGTH);
            }
            map.remove(b);
        }

        if (bodyB instanceof Bullet) {
            Bullet b = (Bullet) bodyB;
            if (bodyA instanceof Ship) {
                ship = (Ship) bodyA;
                ship.decreaseShields(BULLET_STRENGTH);
            }
            map.remove(b);
        }

        if (bodyA instanceof StaticEntity) {
            se = (StaticEntity) bodyA;
        } else if (bodyB instanceof StaticEntity) {
            se = (StaticEntity) bodyB;
        } else {
            return;
        }

        if (bodyB instanceof Ship) {
            ship = (Ship) bodyB;
        } else if (bodyA instanceof Ship) {
            ship = (Ship) bodyA;
        } else {
            return;
        }

        Shape s = se.getShape();
        Circle c = new Circle(ship.getTip().getX(), ship.getTip().getY(), 2.0f);
        if (c.intersects(s)) {
            ship.setShields(0);
        }
    }

    private void drawHUD(float x, float y, float height, float width) {
        float offset;

        if (gameOver) {
            if (System.currentTimeMillis() < msgDisplayUntil) {
                String winMsg;

                if (s1wins > s2wins) {
                    winMsg = "Player 1 Wins";
                } else if (s1wins == s2wins) {
                    winMsg = "Draw";
                } else {
                    winMsg = "Player 2 Wins";
                }
                font
                        .drawString(x + (width / 2)
                                - (font.getWidth(winMsg) / 2), y + (height / 2)
                                - (font.getHeight(winMsg) / 2), winMsg);
            } else {
                game.enterState(MenuState.ID);
            }

            return;
        }

        String roundMsg = "Round " + round + ". Fight!";
        if (System.currentTimeMillis() < msgDisplayUntil) {
            font.drawString(x + (width / 2) - (font.getWidth(roundMsg) / 2), y
                    + (height / 2) - (font.getHeight(roundMsg) / 2), roundMsg);
        }

        // Draw the number of rounds.
        String roundStr = "Round " + round + " of " + rounds;
        offset = x + (width / 2) - (font.getWidth(roundStr) / 2);
        font.drawString(offset, y, roundStr);

        // Draw ship1's shields.
        font.drawString(x, y, Integer.toString(ship1.getShields()));
        // Draw ship1's wins.
        font.drawString(x, y
                + font.getHeight(Integer.toString(ship1.getShields())), Integer
                .toString(s1wins));

        // FIXME: Part of displayed text gets drawn off screen.
        // Draw ship2's shields.
        offset = (x + width)
                - font.getWidth(Integer.toString(ship2.getShields()));
        font.drawString(offset, y, Integer.toString(ship2.getShields()));
        // Draw ship2's wins.
        offset = (x + width) - font.getWidth(Integer.toString(s2wins));
        font.drawString(offset, y
                + font.getHeight(Integer.toString(ship2.getShields())), Integer
                .toString(s2wins));
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game)
            throws SlickException {
        gameOver = false;

        scale = 1.0f;

        map = new Map("data/map1.svg");
        world = map.getWorld();
        world.addListener(this);

        opt = Options.getOptions();

        rounds = opt.getAsInt("number_of_rounds");
        round = 0;

        s1wins = 0;
        s2wins = 0;

        newRound();
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        this.game = game;
        // font = new AngelCodeFont("data/font.fnt", "data/font.tga");
        font = new TrueTypeFont(new Font("Verdana", Font.PLAIN, 16), false);
        input = container.getInput();
        height = container.getHeight();
        width = container.getWidth();

        // float[] depths = {12f, 8f, 5f, 3.5f};
        float[] depths = { 3.5f };
        stars = new SimpleStars(container.getWidth(), container.getHeight(),
                depths);
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_P) {
            paused = !paused;
        }
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game)
            throws SlickException {
    }

    private void newRound() {
        msgDisplayUntil = System.currentTimeMillis() + MSG_DISPLAY_TIME;

        Vector<Entity> toRemove = new Vector<Entity>();

        for (Entity e : map.getEntities()) {
            if (e instanceof DynamicEntity) {
                toRemove.add(e);
            }
        }

        for (Entity e : toRemove) {
            map.remove(e);
        }

        round++;

        ship1 = new Ship(map.getP1start().getX(), map.getP1start().getY());
        ship1.setShields(opt.getAsInt("shield_strength"));
        map.add(ship1);

        ship2 = new Ship(map.getP2start().getX(), map.getP2start().getY());
        ship2.setShields(opt.getAsInt("shield_strength"));
        map.add(ship2);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        float centrex, centrey;
        float transx, transy;
        float x1, y1, x2, y2;
        float distx, disty;

        float diffx = width - scaled(width);
        float diffy = height - scaled(height);

        x1 = ship1.getBody().getPosition().getX();
        y1 = ship1.getBody().getPosition().getY();

        x2 = ship2.getBody().getPosition().getX();
        y2 = ship2.getBody().getPosition().getY();

        distx = Math.max(x1, x2) - Math.min(x1, x2);
        disty = Math.max(y1, y2) - Math.min(y1, y2);

        scale = Math.max(width, height) / Math.max(distx, disty);
        scale /= 3;

        scale = Math.min(scale, 1.0f);

        centrex = (x1 + x2) / 2;
        transx = centrex - (width / 2);
        transx += (diffx / 2);

        // transx = Math.max(transx, 0);
        // transx = Math.min(transx, map.getWidth() - width);

        centrey = (y1 + y2) / 2;
        transy = centrey - (height / 2);
        transy += (diffy / 2);

        drawHUD(0, 0, height, width);

        stars.setX(centrex);
        stars.setY(centrey);
        stars.render(g);

        g.scale(scale, scale);

        g.translate(-transx, -transy);

        if (!gameOver) {
            for (Entity entity : map.getEntities()) {
                entity.draw(g);
            }

            //Draw a circle at the centre point between the ships.
            //g.draw(new Circle(centrex, centrey, 10));

            // Draw a rectangle around the map boundaries.
            g.drawRect(0, 0, map.getWidth(), map.getHeight() + 1);
        }
    }

    private float scaled(float x) {
        return x / scale;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        // System.out.println(delta);

        if (ship1.isDestroyed() || ship2.isDestroyed()) {
            if (ship1.isDestroyed()) {
                s2wins++;
            }
            if (ship2.isDestroyed()) {
                s1wins++;
            }

            newRound();

            return;
        }

        if (s1wins > (rounds / 2) || s2wins > (rounds / 2)) {
            System.out.println("s1wins: " + s1wins);
            System.out.println("s2wins: " + s2wins);
            gameOver = true;
            msgDisplayUntil = System.currentTimeMillis() + MSG_DISPLAY_TIME;
            round = 0;
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            game.enterState(MenuState.ID);
        }

        // Ship 1 Controls

        if (input.isKeyDown(Input.KEY_W)) {
            ship1.thrust();
        }

        if (input.isKeyDown(Input.KEY_A)) {
            ship1.rotateLeft();
        }

        if (input.isKeyDown(Input.KEY_D)) {
            ship1.rotateRight();
        }

        if (input.isKeyDown(Input.KEY_LCONTROL)) {
            ship1.fire(map);
        }

        // Ship 2 Controls

        if (input.isKeyDown(Input.KEY_I)) {
            ship2.thrust();
        }

        if (input.isKeyDown(Input.KEY_J)) {
            ship2.rotateLeft();
        }

        if (input.isKeyDown(Input.KEY_L)) {
            ship2.rotateRight();
        }

        if (input.isKeyDown(Input.KEY_RCONTROL)) {
            ship2.fire(map);
        }

        if (!paused) {
            world.step();
        }
    }
}
