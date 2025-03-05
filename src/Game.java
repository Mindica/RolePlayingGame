import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private Player player;
    private List<Monster> monsters;
    private Merchant merchant;
    private BufferedReader reader;

    public Game() {
        monsters = new ArrayList<>();
        merchant = new Merchant();
        reader = new BufferedReader(new InputStreamReader(System.in));
        monsters.add(new Goblin());
        monsters.add(new Skeleton());
    }

    public void start() throws IOException {
        createPlayer();
        mainMenu();
    }

    private void createPlayer() throws IOException {
        System.out.println("Введите имя вашего персонажа:");
        String playerName = reader.readLine();
        player = new Player(playerName);
        System.out.println("Персонаж " + playerName + " успешно создан!");
    }

    private void mainMenu() throws IOException {
        while (player.isAlive()) {
            System.out.println("Куда вы хотите пойти?");
            System.out.println("1. К торговцу");
            System.out.println("2. В тёмный лес");
            System.out.println("3. На выход");

            String choice = reader.readLine();
            switch (choice) {
                case "1":
                    visitMerchant();
                    break;
                case "2":
                    enterDarkForest();
                    break;
                case "3":
                    System.out.println("Вы вышли из игры.");
                    return;
                default:
                    System.out.println("Некорректный выбор, попробуйте снова.");
            }
        }
    }

    private void visitMerchant() throws IOException {
        System.out.println("Торговец еще не вышел на работу.");
        promptReturnToMenu();
    }

    private void enterDarkForest() throws IOException {
        // Случайным образом выбираем монстра
        Monster monster = monsters.get(new Random().nextInt(monsters.size()));
        System.out.println("Вы встретили " + monster.name + "!");
        Battle battle = new Battle(player, monster);
        Thread battleThread = new Thread(battle);
        battleThread.start();
        try {
            battleThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        promptReturnToMenu();
    }

    private void promptReturnToMenu() throws IOException {
        System.out.println("Что вы хотите сделать дальше?");
        System.out.println("1. Вернуться в город");
        System.out.println("2. Продолжить торговлю/бой (но это можно только в торговле, так как бой закончен)");

        String choice = reader.readLine();
        if (choice.equals("1")) {
            mainMenu();
        } else {
            System.out.println("Некорректный выбор, возвращаем вас в город.");
            mainMenu();
        }
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.start();
    }
}