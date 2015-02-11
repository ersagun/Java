public class Immeuble extends Constantes {

    private Etage[] tableauDesEtages;

    public Cabine cabine; // de l'ascenseur.

    private int niveauDuSol; // le niveau 0 en g�n�ral.

    public long cumulDesTempsDeTransport = 0;

    public long nombreTotalDesPassagersSortis = 0;

    public Etage etageLePlusBas() {
        return tableauDesEtages[0];
    }

    public Etage etageLePlusHaut() {
        return tableauDesEtages[tableauDesEtages.length - 1];
    }

    public Etage niveauDuSol() {
        return etage(niveauDuSol);
    }

    public Immeuble(Echeancier echeancier) {
        int etageMin = -1;
        niveauDuSol = 0;
        tableauDesEtages = new Etage[8];

        int n = etageMin;
        int i = 0;
        while (i < tableauDesEtages.length) {
            // Une personnne toutes les 3 secondes:
            int fa = 30;
            if (n != niveauDuSol) {
                fa = fa * (tableauDesEtages.length - 1);
            }
            tableauDesEtages[i] = new Etage(n, fa, this);
            i++;
            n++;
        }
        cabine = new Cabine(etageLePlusHaut());
        // Initialisation des premiers EvenementArrivee pour chaque Etage:
        for (i = 0; i < tableauDesEtages.length; i++) {
            Etage etage = tableauDesEtages[i];
            long date = etage.arriveeSuivant();
            echeancier.ajouter(new EvenementArriveePassagerPalier(date, etage));
        }
    }

    public void afficheLaSituation() {
        System.out.print("Immeuble (mode ");
        if (isModeParfait()) {
            System.out.print("parfait");
        } else {
            System.out.print("infernal");
        }
        System.out.println("):");
        int i = etageLePlusHaut().numero();
        while (i >= etageLePlusBas().numero()) {
            etage(i).afficheLaSituation();
            i--;
        }
        cabine.afficheLaSituation();
        System.out.println("Cumul des temps de transport: " + cumulDesTempsDeTransport);
        System.out.println("Nombre total des passagers sortis: " + nombreTotalDesPassagersSortis);
        System.out.println("Ratio cumul temps / nb passagers : " +
                (nombreTotalDesPassagersSortis == 0 ? 0 : (cumulDesTempsDeTransport / nombreTotalDesPassagersSortis)));
    }

    public Etage etage(int i) {
        // Retrouve par calcul (assez lent) un Etage avec son numero.
        Etage res = tableauDesEtages[i - etageLePlusBas().numero()];
        return res;
    }

    public int nbEtages() {
        int res = tableauDesEtages.length;
        return res;
    }
}
