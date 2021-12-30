public class Main {

    public static int roundNumber = 1;

    //Boss
    public static int bossHealth = 800;
    public static int bossDamage = 50;
    public static String bossDefence = "";

    //Heroes
    public static String[] heroesType = {"Physical", "Magical", "Kinetic", "Medic"};

    public static int[] heroesHealth = {270, 280, 240, 220};

    public static int[] heroesDamage = {20, 25, 30, 0};

    public static void main(String[] args) {
        printStatistic();
        while (isGameFinished()) {
            round();
        }
    }


    public static void round() {
        roundNumber++;
        chooseBossDefence();
        bossHit();
        heroesHit();
        printStatistic();
        medicHeal();
    }


    public static void printStatistic() {
        System.out.println("Random:" + roundNumber + "Statistic");
        System.out.println("---------------------");
        System.out.println("Boss health:" +
                bossHealth + "; Boss damage: " + bossDamage);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesType[i] + "Health: "
                    + heroesHealth[i]);
            System.out.println(heroesType[i] + "Damage: " + heroesDamage[i]);
        }
        System.out.println("--------------------");
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesType.length);
        bossDefence = heroesType[randomIndex];
        System.out.println("Boss have defence " + bossDefence);
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(10);
                    if (bossHealth - heroesDamage[i] * coeff < 0) {

                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println(heroesType[i] + " Critical damage "
                            + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }

        }
    }


    public static boolean isGameFinished() {

        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return false;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return !allHeroesDead;

    }

    private static void medicHeal() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (1 == 3) {
                continue;
            }
            if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                heroesHealth[i] += 40;
                System.out.println("Medic help:" + heroesType[i]);
                break;
            }
        }
    }
}