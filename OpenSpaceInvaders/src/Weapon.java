
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by mikael on 6/27/17.
 */
public class Weapon {
    int x;
    int y;
    Object lock;
    Player player;

    Weapon(int x, int y, Player player, Object lock) {
        this.x = x;
        this.y = y;
        this.lock = lock;
        this.player = player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void weaponMovement(int x, Key key) {
        this.x = x;
    }
    public void draw(Terminal terminal, int x, int y) {
        for (int i = 19; i > 10; i--) {
            y--;
            terminal.moveCursor(x, y);
            if (y > 0) {
                terminal.putCharacter('I');
            }
        }
        y= 8;
        try {
            Thread.sleep(50);
            for (int n = 10; n < 20; n++) {
                y++;
                terminal.moveCursor(x, y);
                if (y <= 19) {
                    terminal.putCharacter(' ');
                }
            }
        } catch (InterruptedException e){

        }
    }
 /*  public void deleteCurrent(Terminal terminal, int x, int y) {
       for(int n = 10; n <= 19; n++){
           y++;
           terminal.moveCursor(x, y);
           if (y <= 19){
               terminal.putCharacter(' ');
           }
       }
    }*/
}
