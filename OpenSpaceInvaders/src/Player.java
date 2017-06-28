import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;




/**
 * Created by mikael on 6/27/17.
 */
public class Player {
    String name;
    int x;
    int y;
   public Object lock;

    Player(String name, int x, int y, Object lock) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.lock = lock;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
        terminal.putCharacter('\u0394');
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
