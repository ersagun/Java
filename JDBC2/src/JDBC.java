/**
 * Created by Ers on 24/03/2014.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class JDBC {

    // les tableaux qui contient le result
    static String[] idcmd;
    static String[] total;
    static String[] idclient;
    static String[] idvendeur;

    public static String str_cmd;
    public static String str_total;
    public static String str_client;
    public static String str_vendeur;

    public static String req;

    //idd et mot passe recupéré de l'interface
    public static String idd;
    public static String mpp;
    public static int vous;
    public static boolean disp=false;
    public static boolean requeteRecu=false;
    public static boolean reponseEnvoye=false;

    //resultRes

    public static ResultSet r;
    public static Statement stmt;




    public static class IGAuthentification
    {
        public JTextField id=new JTextField();
        public JTextField mp=new JTextField();
        final JComboBox<String> cbox = new JComboBox<String>(new String[] { "Client", "Vendeur" });
        public IGAuthentification()
        {
            // Le JFrame pour l'Authentification

            final JFrame fenetre = new JFrame("Authentification");
            JPanel auth = new JPanel();
            auth.setLayout(new GridLayout(3, 2));

            // Les Labels

            final JLabel l1 = new JLabel("ID :");
            final JLabel l2 = new JLabel("Mot de passe :");
            JLabel l3 = new JLabel("Vous etes :");
            l1.setHorizontalAlignment(SwingConstants.CENTER);
            l2.setHorizontalAlignment(SwingConstants.CENTER);

            // Le bouton

            JButton connexion = new JButton("Se connecter");
            connexion.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    idd=id.getText().toString();
                    mpp=mp.getText().toString();
                    vous= cbox.getSelectedIndex();
                    disp=true;
                    if(vous == -1 || vous==0){
                        vous=8;
                    }
                    if(vous==1){
                        vous=178;
                    }
                    IGRequete igr = new IGRequete(vous);
                    fenetre.setVisible(false);
                }
            });

            auth.add(l1);
            auth.add(l2);
            auth.add(l3);
            auth.add(id);
            auth.add(mp);
            auth.add(cbox);
            auth.add(connexion);

            fenetre.setContentPane(auth);
            fenetre.setSize(300, 150);
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.setVisible(true);
        }
    }



    public static class IGRequete
    {
        // Attributs de classe

        public static int MODE_CLIENT = 8;
        public static int MODE_VENDEUR = 178;
        public static int MODE_RESPONSABLE = 17178;

        private int mode;


        // Constructeur

        final JTextArea jtextarea = new JTextArea(5, 5);


        public IGRequete(final int mode)
        {
            this.mode = mode;

            // Création de tous les Panel

            final JFrame f = new JFrame(this.getTitle());
            JPanel rq = new JPanel();
            JPanel rqnorth = new JPanel();
            final JPanel rqcenter = new JPanel();
            rq.setLayout(new BorderLayout());
            rqnorth.setLayout(new GridLayout(1, 2));
            rqnorth.setLayout(new GridLayout(1, 4));
            JLabel l3 = new JLabel("Inserer votre requete : ");

            // Création des labels affichant les résultats
            l3.setHorizontalAlignment(SwingConstants.CENTER);

            // On ajoute tous les éléments au JPanels
            rqnorth.add(l3);
            rqnorth.add(jtextarea);
            rq.add(rqnorth, BorderLayout.NORTH);
            rq.add(rqcenter, BorderLayout.CENTER);

            f.setContentPane(rq);
            // On lance la fenetre
            f.setSize(600, 600);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);

            // Le bouton
            JButton requete = new JButton("Lancer Requete");
            rq.add(requete, BorderLayout.SOUTH);
            requete.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {

                    if(jtextarea.getText().toUpperCase().startsWith("SELECT * FROM COMMANDE"))
                    {
                        f.setTitle("Select * from commande");
                        req=jtextarea.getText();


                        if(mode==8 ){

                            // demande

                            System.out.println(idd);


                            System.out.println(mpp);

                            //recup requete

                            String req = "SELECT * FROM Client WHERE ID=" + idd
                                    + " AND PASSWORD='"+mpp.toString()+"'";
                            System.out.println(req);
                            try {
                                r = stmt.executeQuery(req);
                                int i = 0;
                                while (r != null && r.next()) {i++;}
                                if (i == 1) {

                                    String rr = req;
                                    String avant = rr.substring(0, rr.indexOf('*'));
                                    String apres = rr.substring(rr.indexOf('*') + 1);
                                    String fin = avant + "IDCmd,Total,IDClient" + apres;
                                    System.out.println(fin);

                                    // modification de la requete
                                    String r = fin + " WHERE IDCLIENT=" + idd;
                                    System.out.println(r);
                                    if (r != null) {
                                        // pour recuperer la requiete de l'utilisateur
                                        int it = 0;
                                        ResultSet rrs = stmt.executeQuery(r);
                                        System.out.println("La table Commande:");
                                        while (rrs != null && rrs.next()) {
                                            idcmd[it] = rrs.getString("IDCmd");
                                            total[it] = rrs.getString("Total");
                                            idclient[it] = rrs.getString("IDClient");
                                            it++;
                                        }
                                        String idcmmd = "";
                                        String tott = "";
                                        String idcc = "";

                                        for (int j = 0; j <= idcmd.length; j++) {
                                            idcmmd = idcmmd + "<br />" + idcmd[j];
                                            tott = tott + "<br />" + total[j];
                                            idcc = idcc + "<br />" + idclient[j];
                                        }

                                        str_cmd = "<html><br /><br /><br />IDCmd<br />------------ " + idcmmd + "</br></html>";
                                        str_total = "<html><br /><br /><br />Total<br />------------ " + tott + "</br></html>";
                                        str_client = "<html><br /><br /><br />IDClient<br />------------ " + idcc + "</br></html>";
                                        reponseEnvoye = true;

                                    }
                                } else {
                                    System.out
                                            .println("Vous n'etes pas un utilisateur. Mettez votre id et votre mot de passe correct. ");
                                }
                            }catch (SQLException sql){
                                sql.printStackTrace();
                            }

                            IGRequete.afficherResultat(str_cmd, str_total, str_client, rqcenter);
                        }
                        if(mode==178 || mode == 1718) {
                            try {
                                int responsable = 0;

                                // demande
                                String mp = "";

                                System.out.println("Ecrivez votre id :");
                                System.out.println(idd);

                                System.out.println("Ecrivez votre mot de passe :");
                                System.out.println(mpp);


                                String reqq = "SELECT * FROM Vendeur WHERE IDVendeur=" + idd
                                        + " AND PASSWORD='" + mpp + "'";
                                System.out.println(reqq);

                                ResultSet r = stmt.executeQuery(reqq);
                                int i = 0;
                                while (r != null && r.next()) {

                                    responsable = r.getInt(4);
                                    i++;

                                }
                                if (i == 1) {

                                    if (responsable == 0) {

                                        String rr = req;
                                        if (rr != null) {
                                            //modification
                                            String avant = rr.substring(0, rr.indexOf('*'));
                                            String apres = rr.substring(rr.indexOf('*') + 1);
                                            String fin = avant + "IDCmd,Total,IDClient,IDVendeur" + apres;
                                            String r2 = fin + " WHERE IDVENDEUR=" + idd;
                                            System.out.println(r2);
                                            // pour recuperer la requiete de l'utilisateur
                                            ResultSet rrs = stmt.executeQuery(r2);
                                            System.out.println("La table Commande:");
                                            int it = 0;
                                            while (rrs != null && rrs.next()) {
                                                idcmd[it] = rrs.getString("IDCmd");
                                                total[it] = rrs.getString("Total");
                                                idclient[it] = rrs.getString("IDClient");
                                                idvendeur[it] = rrs.getString("IDVendeur");

                                            }
                                            String idcmmd = "";
                                            String tott = "";
                                            String idcc = "";
                                            String idvv = "";
                                            for (int j = 0; j <= idcmd.length; j++) {
                                                idcmmd = idcmmd + "<br />" + idcmd[j];
                                                tott = tott + "<br />" + total[j];
                                                idcc = idcc + "<br />" + idclient[j];
                                                idvv = idvv + "<br />" + idvendeur[j];
                                            }

                                            str_cmd = "<html><br /><br /><br />IDCmd<br />------------ " + idcmmd + "</br></html>";
                                            str_total = "<html><br /><br /><br />Total<br />------------ " + tott + "</br></html>";
                                            str_client = "<html><br /><br /><br />IDClient<br />------------ " + idcc + "</br></html>";
                                            str_vendeur = "<html><br /><br /><br />IDClient<br />------------ " + idvv + "</br></html>";
                                            reponseEnvoye = true;

                                        }

                                    } else {
                                        System.out.println("Tapez correctement la requete. ");
                                    }

                                } else {

                                    String rr = req;
                                    System.out.println(rr);
                                    if (rr != null) {
                                        ResultSet rrs = stmt.executeQuery(rr);
                                        int it = 0;
                                        System.out.println("La table Commande:");
                                        while (rrs != null && rrs.next()) {
                                            idcmd[it] = rrs.getString("IDCmd");
                                            total[it] = rrs.getString("Total");
                                            idclient[it] = rrs.getString("IDClient");
                                            idvendeur[it] = rrs.getString("IDVendeur");
                                        }
                                        String idcmmd = "";
                                        String tott = "";
                                        String idcc = "";
                                        String idvv = "";
                                        for (int j = 0; j <= idcmd.length; j++) {
                                            idcmmd = idcmmd + "<br />" + idcmd[j];
                                            tott = tott + "<br />" + total[j];
                                            idcc = idcc + "<br />" + idclient[j];
                                            idvv = idvv + "<br />" + idvendeur[j];
                                        }

                                        str_cmd = "<html><br /><br /><br />IDCmd<br />------------ " + idcmmd + "</br></html>";
                                        str_total = "<html><br /><br /><br />Total<br />------------ " + tott + "</br></html>";
                                        str_client = "<html><br /><br /><br />IDClient<br />------------ " + idcc + "</br></html>";
                                        str_vendeur = "<html><br /><br /><br />IDClient<br />------------ " + idvv + "</br></html>";
                                        IGRequete.afficherResultat(str_cmd, str_total, str_client, str_vendeur, rqcenter);


                                    } else {
                                        System.out.println("Tapez correctement la requete. ");
                                    }


                                }


                            }catch (SQLException sql){
                        sql.printStackTrace();
                     }

                        }else {
                            System.out
                                    .println("Vous n'etes pas un utilisateur. Mettez votre id et votre mot de passe correct. ");
                        }
                    }


                    if(mode != 0 && mode !=1){
                        JOptionPane.showMessageDialog(null,"Erreur choisissez votre status ","TITLE",JOptionPane.WARNING_MESSAGE);
                    }


                    else if(jtextarea.getText().toUpperCase().startsWith("UPDATE COMMANDE"))
                    {
                        f.setTitle("Update commande");
                    }
                    else
                    {
                    }
                }
            });
        }

        /**
         *
         * @return Le titre pour le JFrame.
         */

        public String getTitle()
        {
            switch(this.mode)
            {
                case 8:
                    return "Lanceur de requete en mode client";

                case 178:
                    return "Lanceur de requete en mode vendeur";

                case 17178:
                    return "Lanceur de requete en mode responsable";

                default:
                    return "";
            }
        }

        /**
         *
         * @param str1
         * @param panel
         */

        public static JPanel afficherResultat(String str1, JPanel panel)
        {
            Font f2 = new Font("Courier New", Font.PLAIN, 16);
            JLabel lc1 = new JLabel(str1);
            lc1.setHorizontalAlignment(SwingConstants.CENTER);
            lc1.setFont(f2);
            panel.add(lc1);
            return panel;
        }

        /**
         *
         * @param str1
         * @param str2
         * @param str3
         * @param panel
         */

        public static JPanel afficherResultat(String str1, String str2, String str3, JPanel panel)
        {
            Font f2 = new Font("Courier New", Font.PLAIN, 16);

            JLabel lc1 = new JLabel(str1);
            JLabel lc2 = new JLabel(str2);
            JLabel lc3 = new JLabel(str3);

            lc1.setHorizontalAlignment(SwingConstants.CENTER);
            lc2.setHorizontalAlignment(SwingConstants.CENTER);
            lc3.setHorizontalAlignment(SwingConstants.CENTER);
            lc1.setFont(f2);
            lc2.setFont(f2);
            lc3.setFont(f2);

            panel.add(lc1);
            panel.add(lc2);
            panel.add(lc3);

            return panel;
        }

        /**
         *
         * @param str1
         * @param str2
         * @param str3
         * @param str4
         * @param panel
         */

        public static JPanel afficherResultat(String str1, String str2, String str3, String str4, JPanel panel)
        {
            Font f2 = new Font("Courier New", Font.PLAIN, 16);

            JLabel lc1 = new JLabel(str1);
            JLabel lc2 = new JLabel(str2);
            JLabel lc3 = new JLabel(str3);
            JLabel lc4 = new JLabel(str4);

            lc1.setHorizontalAlignment(SwingConstants.CENTER);
            lc2.setHorizontalAlignment(SwingConstants.CENTER);
            lc3.setHorizontalAlignment(SwingConstants.CENTER);
            lc4.setHorizontalAlignment(SwingConstants.CENTER);
            lc1.setFont(f2);
            lc2.setFont(f2);
            lc3.setFont(f2);
            lc4.setFont(f2);

            panel.add(lc1);
            panel.add(lc2);
            panel.add(lc3);
            panel.add(lc4);

            return panel;
        }
    }




    public static void main(String args[]) throws SQLException {
        String url = "jdbc:oracle:thin:@Ersagun:1521:xe ";
        Connection con = null;
        Statement stmt = null;

        //cration auth
        IGAuthentification IGa= new IGAuthentification();
        //valeurs
        idcmd=new String[100];
        total=new String[100];
        idclient=new String[100];
        idvendeur=new String[100];

        // connection
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Class.forName("com.mysql.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException (try): ");
            System.err.println(e.getMessage());
        }
        try {
            con = DriverManager.getConnection(url, args[0], args[1]);
            stmt = con.createStatement();

            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

    }
}