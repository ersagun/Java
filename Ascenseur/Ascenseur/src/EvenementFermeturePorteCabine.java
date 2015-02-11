public class EvenementFermeturePorteCabine extends Evenement {

    public EvenementFermeturePorteCabine(long d) {
        super(d);
    }

    public void afficheDetails(Immeuble immeuble) {
        System.out.print("FPC");
    }

    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        Cabine cabine = immeuble.cabine;

        cabine.porteOuverte = false;

        // le sens de la cabine ne peut pas changer ici

        echeancier.ajouter(new EvenementPassageCabinePalier(
                date + tempsPourBougerLaCabineDUnEtage,
                cabine.prochainEtage(immeuble)));
    }
}
