public class EvenementArriveePassagerPalier extends Evenement {

    private Etage etageDeDepart;//etage de depart du guguss

    public EvenementArriveePassagerPalier(long d, Etage edd) {
        super(d);
        etageDeDepart = edd;
    }

    public void afficheDetails(Immeuble immeuble) {
        System.out.print("APP ");
        System.out.print(etageDeDepart.numero());
    }

    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        Passager p = new Passager(date, etageDeDepart, immeuble);

        etageDeDepart.ajouter(p);

        Cabine cabine = immeuble.cabine;
        if (cabine.etage == etageDeDepart && cabine.porteOuverte) {
            long tempsEntree = etageDeDepart.entreePassagers(cabine);
            if (tempsEntree > 0)
                echeancier.retarderFermeture(tempsEntree + tempsPourOuvrirOuFermerLesPortes);
        } else {
            if (cabine.status() == '-') {
                cabine.demarrer(p);
                if (cabine.etage == etageDeDepart) {
                    echeancier.ajouter(new EvenementOuverturePorteCabine(
                            date + tempsPourOuvrirOuFermerLesPortes));
                } else {
                    echeancier.ajouter(new EvenementPassageCabinePalier(
                            date + tempsPourBougerLaCabineDUnEtage,
                            cabine.prochainEtage(immeuble)));
                }
            }
        }

        // regénérer APP
        date += etageDeDepart.arriveeSuivant();
        echeancier.ajouter(this);
    }
}
