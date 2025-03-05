class Player extends Character {
    int level;

    Player(String name) {
        super(name, 100, 20, 15);
        this.level = 1;
    }

    void levelUp() {
        System.out.println(name + " повысил уровень!");
        level++;
        health += 20;
        strength += 5;
        agility += 5;
        experience = 0;
    }

    void purchasePotion() {
        if (gold >= 10) {
            gold -= 10;
            health += 20;
            System.out.println(name + " купил зелье лечения. Текущее здоровье: " + health);
        } else {
            System.out.println("Недостаточно золота!");
        }
    }
}
