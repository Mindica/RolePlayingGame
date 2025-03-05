import java.util.Random;

abstract class Character {
    String name;
    int health;
    int strength;
    int agility;
    int experience;
    int gold;
    Random random;

    Character(String name, int health, int strength, int agility) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.experience = 0;
        this.gold = 0;
        this.random = new Random();
    }

    boolean isAlive() {
        return health > 0;
    }

    void takeDamage(int damage) {
        health -= damage;
    }

    int calculateDamage() {
        int chanceToHit = random.nextInt(100);
        if (agility * 3 < chanceToHit) {
            System.out.println(name + " промахнулся!");
            return 0;
        }
        return strength;
    }

    void gainExperience(int amount) {
        experience += amount;
    }

    void addGold(int amount) {
        gold += amount;
    }
}