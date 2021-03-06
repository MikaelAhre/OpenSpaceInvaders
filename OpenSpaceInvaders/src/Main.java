import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;
import java.util.Random;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        Terminal terminal = TerminalFacade.createTerminal(System.in,
                System.out, Charset.forName("UTF8"));
        terminal.setCursorVisible(false);
        Object lock = new Object();
        //Enemy enemyTest = new Enemy(20, 0, lock);
        Player player1 = new Player("Bubba", 20, 20, 0, lock);
        //Weapon weapon = new Weapon(player1.getX(), 19, player1, lock);
        terminal.enterPrivateMode();
        enemyGenerator(terminal, lock, player1);
        //enemyDraw(enemyTest, terminal);
        playerDraw(player1, terminal);
        // weaponMov(weapon, terminal, player1);
       // printString(50, 12, "You have survived " + countingScore() + " seconds", terminal);
        countingScore(terminal);

    }

    private static void countingScore(Terminal terminal){
        int scoreTimer = 0;
        while (true) {
            try {
                Thread.sleep(1000);
                scoreTimer++;
            } catch (InterruptedException e) {

            }
            printString(50, 25, "You have survived " + scoreTimer + " seconds", terminal);
        }
    }

    private static void printString(int x, int y, String gameover, Terminal terminal) {
        for (int i = 0; i < gameover.length(); i++) {
            terminal.moveCursor(x++, y);
            terminal.putCharacter(gameover.charAt(i));

        }
    }

    public static synchronized void enemyDraw(Enemy enemy, Terminal terminal, Player player1) {
        Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
        new Thread(new Runnable() {
            public void run() {
                String gameover = "YOU FAIL. THE LAST OF HUMANITY HAVE BEEN KILLED";
                int gameScore = player1.getScore();
                while (!Thread.currentThread().isInterrupted()) {

                    try {
                        Thread.sleep(500);
                        if (enemy.getY() < 20) {
                            synchronized (enemy.lock) {
                                enemy.draw(terminal);
                                if (player1.getX() == enemy.getX() && player1.getY() == enemy.getY()) {
                                    terminal.clearScreen();
                                    terminal.applyForegroundColor(255, 0, 0);
                                    printString(25, 12, gameover, terminal);
                                    Thread.sleep(1200);
                                    System.exit(0);
                                }
                            }
                        } else {
                            break;
                        }
                    } catch (ThreadDeath | Exception e) {

                    }
                }
            }
        }).start();
    }

  /*  public static synchronized void weaponMov(Weapon weapon, Terminal terminal, Player player1) {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (weapon.lock) {

                        Key key = terminal.readInput();
                        if (key != null) {
                            try {
                                switch (key.getKind()) {
                                    case ArrowUp:
                                        Thread.sleep(100);
                                        weapon.weaponMovement(player1.getX(), key);
                                        Thread.sleep(50);
                                        weapon.draw(terminal, player1.getX(), weapon.getY());
                                        //weapon.deleteCurrent(terminal, player1.getX(), weapon.getY());
                                }
                            } catch (InterruptedException e) {

                            }
                        }
                    }
                }
            }
        }).start();
    }*/

    public static synchronized void playerDraw(Player player1, Terminal terminal) {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (player1.lock) {
                        Key key = terminal.readInput();
                        if (key != null) {
                            player1.deleteCurrent(terminal, player1.getX(), player1.getY());
                            player1.playerMovement(key, player1.getX());
                            player1.drawPlayer(terminal, player1.getX(), player1.getY());

                        }
                    }
                }

            }
        }).start();
    }

    public static synchronized void enemyGenerator(Terminal terminal, Object lock, Player player1) {
        new Thread(new Runnable() {
            public void run() {

                Random rand = new Random();
                while (true) {
                    try {
                        int randomNum = rand.nextInt(25) + 1;
                        Thread.sleep(500);
                        Enemy enemy = new Enemy(randomNum, 0, lock);
                        Thread.sleep(100);
                        enemyDraw(enemy, terminal, player1);
                    } catch (Exception e) {
                    }
                }
            }
        }).start();
    }
}


