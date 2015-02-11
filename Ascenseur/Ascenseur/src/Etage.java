import java.util.ArrayList;

public class Etage extends Constantes {
    private int numero; // de l'Etage

    private Immeuble immeuble;

    private LoiDePoisson poissonFrequenceArrivee;

    private ArrayList<Passager> listePassagersEtage = new ArrayList<Passager>();

    public Etage(int n, int fa, Immeuble im) {
        numero = n;
        immeuble = im;
        int germe = n << 2;
        if (germe <= 0) {
            germe = -germe + 1;
        }
        poissonFrequenceArrivee = new LoiDePoisson(germe, fa);
    }

    public void afficheLaSituation() {
        if (numero() >= 0) {
            System.out.print(" ");
        }
        System.out.print(numero());
        if (this == immeuble.cabine.etage) {
            System.out.print(" C ");
            if (immeuble.cabine.porteOuverte) {
                System.out.print("[  ]: ");
            } else {
                System.out.print(" [] : ");
            }
        } else {
            System.out.print("   ");
            System.out.print(" [] : ");
        }
        int i = 0;
        boolean stop = listePassagersEtage.size() == 0;
        while (!stop) {
            if (i >= listePassagersEtage.size()) {
                stop = true;
            } else if (i > 6) {
                stop = true;
                System.out.print("...(");
                System.out.print(listePassagersEtage.size());
                System.out.print(')');
            } else {
                System.out.print(listePassagersEtage.get(i));
                i++;
                if (i < listePassagersEtage.size()) {
                    System.out.print(", ");
                }
            }
        }
        System.out.print('\n');
    }

    public int numero() {
        // En Eiffel il est possible de mettre celle qui suit:
        // assert immeuble.recalcule_numero(this) == this.numero;
        return this.numero;
    }

    public void ajouter(Passager passager) {
        listePassagersEtage.add(passager);
    }

    public long arriveeSuivant() {
        return poissonFrequenceArrivee.suivant();
    }

    public boolean doitStopper(Cabine cabine) {
        if (isModeParfait()) {
            for (Passager p : listePassagersEtage) {
                if (cabine.accepteParfait(p)) {
                    return true;
                }
            }
        } else {
            return !listePassagersEtage.isEmpty();
        }
        return false;
    }

    public long entreePassagers(Cabine cabine) {
        long temps = 0;

        if (isModeParfait()) {
            int i = 0;
            while (i < listePassagersEtage.size()) {
                Passager p = listePassagersEtage.get(i);
                if (cabine.accepteParfait(p)) {
                    if (cabine.ajouterPassager(p)) {
                        temps += Constantes.tempsPourEntrerOuSortirDeLaCabine;
                        listePassagersEtage.remove(i);
                    } else {
                        // plus de place !
                        break;
                    }
                } else {
                    i++;
                }
            }
        } else {
            while (!listePassagersEtage.isEmpty()) {
                Passager p = listePassagersEtage.get(0);
                if (cabine.ajouterPassager(p)) {
                    temps += Constantes.tempsPourEntrerOuSortirDeLaCabine;
                    listePassagersEtage.remove(0);
                } else {
                    // plus de place !
                    break;
                }
            }
        }

        return temps;
    }

    public Passager getPremierPassager() {
        try {
            return listePassagersEtage.get(0);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public Passager contientPassagerCompatible(char sens) {
        if (isModeParfait()) {
            for (Passager p : listePassagersEtage) {
                if (p.sens() == sens)
                    return p;
            }
            return null;
        } else {
            return listePassagersEtage.isEmpty() ? null : listePassagersEtage.get(0);
        }
    }
}
