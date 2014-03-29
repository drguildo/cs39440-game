package uk.ac.aber.cs39440.experiments;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundBug extends BasicGame {
    private Music music;
    private Sound sound;

    public SoundBug() {
        super("SoundTest");
    }

    public void init(GameContainer container) throws SlickException {
        music = new Music("testdata/theme.ogg", true);
        sound = new Sound("testdata/burp.aif");
        music.play();
        sound.play();
    }

    public void render(GameContainer container, Graphics g) {
    }

    public void update(GameContainer container, int delta) {
    }

    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer(new SoundBug());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
