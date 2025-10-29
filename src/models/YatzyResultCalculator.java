package models;

/**
 * Used to calculate the score of throws with 5 dice
 */
public class YatzyResultCalculator {

    private Die[] dice; // Array af terninger

    public YatzyResultCalculator(Die[] dice) {
        this.dice = dice; // Constructor
    }

    private int[] countEyes() { // Hjælpemetode, der tæller hvor mange gange hvert øje (1-6) forekommer
        int[] counts = new int[7]; // 7 pladser (0-6), ignorerer indeks 0, så counts[1] tæller 1'ere osv.
        for (Die die : dice) { // for-each loop, gennemgår alle 5 terninger i arrayet dice.
            counts[die.getEyes()]++; // Henter værdien (die.getEyes()), og lægger 1 til tælleren, for det tal.
        }
        return counts;
    }

    public int upperSectionScore(int eyes) { // Metode beregner scoreren for én af de øverste sektioner.
        int sum = 0;
        for (Die die : dice) { // For hver terning (die) i arrayet (dice)
            if (die.getEyes() == eyes)
                sum += eyes; // Hvis værdien på terningen matcher den valgte pointværdi, læg værdien til summen.
        }
        return sum;
    }

    public int onePairScore() {
        int[] counts = countEyes(); // Kalder hjælpemetoden countEyes().
        for (int i = 6; i >= 1; i--) { // Hvert indeks i arrayet, fortæller hvor mange terninger har den værdi. Gar baglæns ned fra 6.
            if (counts[i] >= 2) return i * 2;
        }
        return 0;
    }

    public int twoPairScore() {
        int[] counts = countEyes();
        int countPairs = 0;
        int sum = 0;

        for (int i = 6; i >= 1; i--) {
            if (counts[i] >= 2) {
                countPairs++;
                sum += i * 2;
            }
            if (countPairs == 2)
                return sum;
        }
        return 0;
    }

    public int threeOfAKindScore() {
        int[] counts = countEyes(); // Bruger igen arrayet der tæller antal forekomster
        for (int i = 6; i >= 1; i--) { // Tæller ned fra 6.
            if (counts[i] >= 3) { // Hvis der er 3 (eller flere) forekomster af samme antal øjne:
                return i * 3; // Returnerer 3* antal øjne. (Man kan ikke få flere point end svarende til 3 af de samme, selvom man har flere)
            }
        }
        return 0;
    }

    public int fourOfAKindScore() { // Samme logik som metoden ovenover.
        int[] counts = countEyes();
        for (int i = 6; i >= 1; i--) {
            if (counts[i] >= 4) {
                return i * 4;
            }
        }
        return 0;
    }

    public int smallStraightScore() { // Giver kun point, hvis der er præcis én etter, én to'er osv.
        int[] counts = countEyes();
        for (int i = 1; i < 6; i++) { // Går igennem tælleren, fra 1-5.
            if (counts[i] != 1) {
                return 0; // Returnerer 0, hvis antallet af øjne (fra 1-5) ikke fremkommer præcis 1 gang.
            }
        }
        return 15; // ellers returnerer 15 point.
    }

    public int largeStraightScore() {
        int[] counts = countEyes();
        for (int i = 2; i <= 6; i++) { // Går igennem tælleren fra 2-6.
            if (counts[i] != 1) {
                return 0; // Returnerer 0 hvis antallet af øjne (fra 2-6) ikke fremkommer præcis 1 gang.
            }
        }
        return 20; // ellers returnerer 20 point.
    }

    public int fullHouseScore() {
        int[] counts = countEyes();
        int threeSame = 0; // Variabel der holder det antal øjne, som der er tre ens af.
        int twoSame = 0; // Variabel der holder det antal øjne, som der er to ens af.

        for (int i = 1; i <= 6; i++) {
            if (counts[i] == 3) // Hvis der er præcis 3 forekomster af et antal øjne, sættes variablen til det antal øjne.
                threeSame = i;
            if (counts[i] == 2) // Hvis der er præcis 2 forekomster af et antal øjne, sættes variablen til det antal øjne.
                twoSame = i;
            }

        if (twoSame > 0 && threeSame > 0) { // Hvis der er en faktisk værdi i begge de to variable, returneres point antallet.
            return twoSame * 2 + threeSame * 3;
        }
        return 0;
    }

    public int chanceScore() {
        int sum = 0;
        for (Die die : dice) {
            sum += die.getEyes();
        }
        return sum;
    }

    public int yatzyScore() {
        int[] counts = countEyes();
        for (int i = 1; i < 6; i++) {
            if (counts[i] == 5) {
                return 50;
            }
        }
        return 0;
    }
}
