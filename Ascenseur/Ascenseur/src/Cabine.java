import java.util.Arrays;

public class Cabine extends Constantes {

    public Etage etage;
    public boolean porteOuverte = false;
    private char status = '-';
    private Passager[] tableauPassager = new Passager[nombreDePlacesDansLaCabine];
    public boolean premierArrivePremierServi = true;

    public Cabine(Etage e) {
        etage = e;
    }

    public void afficheLaSituation() {
        System.out.print("Contenu de la cabine: ");
        for (int i = 0; i < tableauPassager.length; i++) {
            Passager p = tableauPassager[i];
            if (p != null) {
                System.out.print(p + ", ");
            }
        }
        System.out.println("\nPriorité de la cabine: " + status);
    }

    /**
     * Ajoute un passager déjà en adéquation avec la direction de la cabine
     * (i.e. l'adéquation de ce passager doit être vérifiée AVANT d'appeler
     * cette fonction.)
     *
     * @return true s'il y avait assez de place pour ajouter ce passager
     */
    public boolean ajouterPassager(Passager p) {

        for (int i = 0; i < tableauPassager.length; i++) {
            if (tableauPassager[i] == null) {
                tableauPassager[i] = p;
                premierArrivePremierServi = false;
                return true;
            }
        }
        return false;
    }

    public char status() {
        return status;
    }

    public Etage prochainEtage(Immeuble immeuble) {
        int n = etage.numero() + (status == 'v' ? -1 : 1);
        return immeuble.etage(n);
    }

    public boolean doitStopper(Immeuble immeuble) {
        if (etage == immeuble.etageLePlusBas() || etage == immeuble.etageLePlusHaut())
            return true;
        for (int i = 0; i < tableauPassager.length; i++) {
            Passager p = tableauPassager[i];
            if (p != null && p.etageDestination() == etage)
                return true;
        }
        return false;
    }

    public boolean accepteParfait(Passager p) {
        return p.sens() == status || premierArrivePremierServi;
    }

    public long sortiePassagers(Immeuble immeuble, long date) {
        long temps = 0;
        for (int i = 0; i < tableauPassager.length; i++) {
            Passager p = tableauPassager[i];
            if (p != null && p.etageDestination() == etage) {
                tableauPassager[i] = null;
                immeuble.cumulDesTempsDeTransport += (date - p.dateDepart());
                immeuble.nombreTotalDesPassagersSortis++;
                temps += tempsPourEntrerOuSortirDeLaCabine;
            }
        }
        return temps;
    }

    public void demarrer(Passager p) {

        premierArrivePremierServi = true;
        int delta = etage.numero() - p.numeroDepart();
        if (delta == 0) {
            status = p.sens();
            premierArrivePremierServi = false;
        } else {
            status = delta > 0 ? 'v' : '^';
        }
    }

    public void recalculStatus(Immeuble immeuble) {

        if (etage == immeuble.etageLePlusHaut()) {
            status = 'v';
            return;
        } else if (etage == immeuble.etageLePlusBas()) {
            status = '^';
            return;
        }

        boolean vide = true;
        premierArrivePremierServi = false;

        // si un passager déjà dans la cabine va dans le même sens
        // que la cabine, continuer dans ce sens
        for (int i = 0; i < tableauPassager.length; i++) {
            Passager p = tableauPassager[i];
            if (p != null) {
                vide = false;
                if (p.sens() == status) {
                    return;
                }
            }
        }

        if (!vide) {
            // tous les passagers de la cabine vont dans le sens inverse
            // changer le sens de la cabine
            status = status == '^' ? 'v' : '^';
            return;
        }

        int delta, limite;
        if (status == '^') {
            delta = 1;
            limite = immeuble.etageLePlusHaut().numero() + 1;
        } else {
            delta = -1;
            limite = immeuble.etageLePlusBas().numero() - 1;
        }

        // regarder dans les étages où l'on se dirige si un passager en attente est compatible avec la cabine

        for (int i = etage.numero(); i != limite; i += delta) {
            Passager compat = immeuble.etage(i).contientPassagerCompatible(status);
            if (compat != null) {

                // laisser le passager compatible indiquer sa direction (inutile en mode parfait, nécessaire en mode infernal)
                status = compat.sens();
                return;
            }
        }

        // la cabine est vide et personne n'est compatible
        // continuer dans le sens-là s'il y a quelqu'un en attente, même incompatible

        premierArrivePremierServi = true;

        for (int i = etage.numero(); i != limite; i += delta) {
            Passager p = immeuble.etage(i).getPremierPassager();
            if (p != null) {
                if (p.etageDepart() == etage) {
                    status = p.sens();
                }
                premierArrivePremierServi = true;
                return;
            }
        }

        // changer de sens
        status = status == '^' ? 'v' : '^';
    }

}
