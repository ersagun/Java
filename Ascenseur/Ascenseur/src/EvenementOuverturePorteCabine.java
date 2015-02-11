public class EvenementOuverturePorteCabine extends Evenement {

    public EvenementOuverturePorteCabine(long d) {
        super(d);
    }

    public void afficheDetails(Immeuble immeuble) {
        System.out.print("OPC");
    }

    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        Cabine cabine = immeuble.cabine;
        Etage etage = cabine.etage;

        cabine.porteOuverte = true;

        long tempsSortie = cabine.sortiePassagers(immeuble, date);

        cabine.recalculStatus(immeuble);

        long tempsEntree = etage.entreePassagers(cabine);

        echeancier.ajouter(new EvenementFermeturePorteCabine(
                date + tempsPourOuvrirOuFermerLesPortes + tempsSortie + tempsEntree));
    }

}
