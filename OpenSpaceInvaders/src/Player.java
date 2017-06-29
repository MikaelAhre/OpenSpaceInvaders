import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;


/**
 * Created by mikael on 6/27/17.
 */
public class Player {
    String name;
    int x;
    int y;
    int score;
    public Object lock;

    Player(String name, int x, int y, int score, Object lock) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.lock = lock;
        this.score = score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void playerMovement(Key key, int x) {
        x = getX();
        if (key != null) {
            switch (key.getKind()) {
                case ArrowLeft:
                    x += -1;
                    setX(x);
                    break;
                case ArrowRight:
                    x += 1;
                    setX(x);
                    break;
            }
        }
    }

    public void drawPlayer(Terminal terminal, int x, int y) {
        x = getX();
        y = getY();
        terminal.moveCursor(x, y);
        terminal.putCharacter('\u29CB');
        terminal.moveCursor(0, 0);
    }

    public void deleteCurrent(Terminal terminal, int x, int y) {
        x = getX();
        y = getY();
        terminal.moveCursor(x, y);
        terminal.putCharacter(' ');
        terminal.moveCursor(0, 0);
    }
}
