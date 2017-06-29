
import com.googlecode.lanterna.terminal.Terminal;
/**
 * Created by mikael on 6/27/17.
 */
public class Enemy {
    int x;
    int y;
   public  Object lock;

    Enemy( int x, int y, Object lock) {
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


    public void draw(Terminal terminal) {
        terminal.moveCursor(x, y);
        terminal.putCharacter(' ');
        y++;
        terminal.moveCursor(x, y);
        if(y <20){
        terminal.putCharacter('\u3036');
        }
        terminal.moveCursor(0, 0);


    }

}
