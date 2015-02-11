public class EvenementPassageCabinePalier extends Evenement {

    private Etage etage;

    public EvenementPassageCabinePalier(long d, Etage e) {
        super(d);
        etage = e;
    }

    public void afficheDetails(Immeuble immeuble) {
        System.out.print("PCP ");
        System.out.print(etage.numero());
    }

    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        Cabine cabine = immeuble.cabine;

        // bouger la cabine
        cabine.etage = etage;

        // il va falloir distinguer entre mode parfait et mode infernal pour savoir si on s'arrête...
        // PS: doitStopper pourrait être plus efficace
        if (cabine.doitStopper(immeuble) || etage.doitStopper(cabine)) {
            // générer un OPC
            echeancier.ajouter(new EvenementOuverturePorteCabine(
                    date + tempsPourOuvrirOuFermerLesPortes));
        } else {
            // si la cabine doit continuer, regénérer PCP
            etage = cabine.prochainEtage(immeuble);
            date += tempsPourBougerLaCabineDUnEtage;
            echeancier.ajouter(this);
        }
    }
}
