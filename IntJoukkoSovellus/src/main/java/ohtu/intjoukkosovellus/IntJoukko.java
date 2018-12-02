package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Oletuskasvatus väärin");//heitin vaan jotain :D
        }

        this.kasvatuskoko = kasvatuskoko;
        this.ljono = new int[kapasiteetti];
        alkioidenLkm = 0;
    }

    public boolean lisaa(int luku) {
        if (!kuuluuJoukkoon(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;

            if (alkioidenLkm % ljono.length == 0) {
                lisaaTilaaJonoon();
            }

            return true;
        }

        return false;
    }

    private void lisaaTilaaJonoon() {
        int[] uusiTaulukko = new int[ljono.length + kasvatuskoko];
        kopioiUuteenTaulukkoonVanhanArvot(uusiTaulukko);
        ljono = uusiTaulukko;
    }

    public boolean kuuluuJoukkoon(int luku) {
        for (int arvo : ljono) {
            if (arvo == luku) {
                return true;
            }
        }

        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                ljono[i] = 0;
                siirraLukujaVasemmalleAlkaen(i);
                alkioidenLkm--;
                return true;
            }
        }

        return false;
    }

    private void siirraLukujaVasemmalleAlkaen(int kohta) {
        for (int i = kohta; i < alkioidenLkm - 1; i++) {
            int apu = ljono[i];
            ljono[i] = ljono[i + 1];
            ljono[i + 1] = apu;
        }
    }

    private void kopioiUuteenTaulukkoonVanhanArvot(int[] kohde) {
        for (int i = 0; i < ljono.length; i++) {
            kohde[i] = ljono[i];
        }
    }

    public int koko() {
        return alkioidenLkm;
    }
    
    // Joo-oh
    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + ljono[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += ljono[i];
                tuotos += ", ";
            }
            tuotos += ljono[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        
        System.arraycopy(ljono, 0, taulu, 0, taulu.length);
        
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko palautettava = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        
        for (int i = 0; i < aTaulu.length; i++) {
            palautettava.lisaa(aTaulu[i]);
        }
        
        for (int i = 0; i < bTaulu.length; i++) {
            palautettava.lisaa(bTaulu[i]);
        }
        
        return palautettava;
    }

    // Tässä on yksi rivi liikaa, mutta se tulee for:ien sulkemisesta
    // En siis pidä tätä liian monimutkaisena .
    // (varsinkin kun jos tätä lähtee hajottamaan siitä tulee omasta mielestä vaan sekavampaa)
    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko palautettava = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    palautettava.lisaa(bTaulu[j]);
                }
            }
        }
        
        return palautettava;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko palautettava = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        
        for (int i = 0; i < aTaulu.length; i++) {
            palautettava.lisaa(aTaulu[i]);
        }
        
        for (int i = 0; i < bTaulu.length; i++) {
            palautettava.poista(bTaulu[i]);
        }

        return palautettava;
    }

}
