class Battle implements Runnable {
    private Player player;
    private Monster monster;

    Battle(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }

    @Override
    public void run() {
        while (player.isAlive() && monster.isAlive()) {
            int playerDamage = player.calculateDamage();
            if (playerDamage > 0) {
                monster.takeDamage(playerDamage);
                System.out.println(player.name + " атакует " + monster.name + " и наносит " + playerDamage + " урона.");
            }

            if (!monster.isAlive()) {
                System.out.println(monster.name + " повержен!");
                player.gainExperience(50);
                player.addGold(20);
                break;
            }

            int monsterDamage = monster.calculateDamage();
            if (monsterDamage > 0) {
                player.takeDamage(monsterDamage);
                System.out.println(monster.name + " атакует " + player.name + " и наносит " + monsterDamage + " урона.");
            }

            if (!player.isAlive()) {
                System.out.println(player.name + " был убит!");
                break;
            }
        }
    }
}