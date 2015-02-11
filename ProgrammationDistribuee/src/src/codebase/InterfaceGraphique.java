package codebase;

import serveur.BlagueProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;


public class InterfaceGraphique extends JFrame{
	
	/**
	 * blague provider observ�
	 */
	public BlagueProvider bp;
	
	/**
	 * la liste � gerer pour l'affichage des blagues locales
	 * mise � jour avec MaJBlagues() 
	 */
	JList blaguesLocales; 
	
	/**
	 * la liste � gerer pour l'affichage des blagues distantes
	 * mise � jour avec MaJBlaguesDistantes() 
	 */
	JList blaguesDistantes; 
	
	/**
	 * la liste � gerer pour l'affichage des serveurs
	 * mise � jour avec MaJServeurs() 
	 */
	JList serveurs;
	
	
	String listRefChoisi;
	String blChoisi;
    String blc;


    String nnom;
    String nreponse;
    String nquestion;
	/**
	 * ajoute l'onglet distant � l'interface
	 */
	public JPanel ongletDistant() throws RemoteException, BlagueAbsenteException {
		
		JPanel pdistant=new JPanel();
		
		//la boite contenant tout
		Box distant=new Box(BoxLayout.Y_AXIS);
		
		//etiquette 1
		distant.add(new JLabel("Serveur distant"));
				
		//la partie serveur distant
		JPanel Pserveurs=new JPanel();
		serveurs=new JList();
        MaJServeurs();

        serveurs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                listRefChoisi = (String) serveurs.getSelectedValue();
                try {
                    MaJBlagueDist();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                } catch (BlagueAbsenteException e1) {
                    e1.printStackTrace();
                }

            }
        });


		Pserveurs.add(serveurs);
		//ajouter boite
		distant.add(Pserveurs);

		//etiquette2
		distant.add(new JLabel("Blague sur serveur selectionne"));
		
		//la partie blague distante
		JPanel PblaguesDistantes=new JPanel();
		blaguesDistantes=new JList();
        blaguesDistantes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                blChoisi=(String)blaguesDistantes.getSelectedValue();
            }
        });
		blaguesDistantes.setPreferredSize(new Dimension(300,200));
		PblaguesDistantes.add(blaguesDistantes);
		//ajoute � la boite
		distant.add(PblaguesDistantes);
		
		// bouton de sauvegarde
		JButton bouton = new JButton("telecharge");
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(blChoisi!=null && listRefChoisi!=null) {
                    BlagueProviderPairApair choisi = (BlagueProviderPairApair) bp.getListeRef().get(listRefChoisi);
                    bp.telechargeBlague(choisi,blChoisi);
                    try {
                        MaJBlagues();
                        blChoisi=null;
                        listRefChoisi=null;
                    } catch (BlagueAbsenteException e1) {
                        e1.printStackTrace();
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
		distant.add(bouton);

		
		//encapsuler dans un jpanel
		pdistant.add(distant);
		return(pdistant);
	}
	
	
	
	/**
	 * ajoute l'onglet local � l'interface
	 */
	public JPanel ongletLocal() throws BlagueAbsenteException, RemoteException {
		//l'onglet local
		JPanel local=new JPanel();
		Box blocal=new Box(BoxLayout.Y_AXIS);
		
		//etiquette 1
		blocal.add(new JLabel("Blagues connues en local"));
		
		//blagues locales
		JPanel PblaguesLocales=new JPanel();
		blaguesLocales=new JList();
		MaJBlagues();
		blaguesLocales.setPreferredSize(new Dimension(300,200));
		PblaguesLocales.add(blaguesLocales);
		//ajout dans boite
		blocal.add(PblaguesLocales);
		
		//etiquette2
		blocal.add(new JLabel("Information blague locale"));

		//les informations sur les blagues
		//nom de la blague
		final JTextField nom=new JTextField();
		blocal.add(nom);
		
		//contenu 
		final JTextField question=new JTextField();
		blocal.add(question);
		
		//reponse
		final JTextArea reponse=new JTextArea();
		reponse.setPreferredSize(new Dimension(300,200));
		blocal.add(reponse);


        blaguesLocales.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                blc= (String)blaguesLocales.getSelectedValue();
                try {
                    nom.setText(bp.getBlague(blc).getNom());
                    question.setText(bp.getBlague(blc).getQuestion());
                    reponse.setText(bp.getBlague(blc).getReponse());

                } catch (RemoteException e1) {
                    e1.printStackTrace();
                } catch (BlagueAbsenteException e1) {
                    e1.printStackTrace();
                }
            }
        });
        nom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nnom = nom.getText();
            }
        });

        reponse.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                nreponse=reponse.getText();
            }
        });


        question.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nquestion=question.getText();
            }
        });




        //bouton de sauvegarde
		JButton bouton=new JButton("sauve");
		blocal.add(bouton);

        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    Blague nouveau = new Blague(nnom, nquestion, nreponse);
                    bp.ajoutBlague(nouveau);
                    try {
                        MaJBlagues();
                        nnom = null;
                        nreponse = null;
                        nquestion = null;
                    } catch (BlagueAbsenteException e1) {
                        e1.printStackTrace();
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }

            }});
		local.add(blocal);
		return(local);
	}
	
	
	/**
	 * construction de l'interface
	 */
	public InterfaceGraphique(String nom, BlagueProvider bp) throws BlagueAbsenteException, RemoteException {
		super("Blaguemule: "+nom);
		
		//mise � jour du lien vers le modele
		this.bp=bp;
		
		//construction de l'interface
		JTabbedPane onglets=new JTabbedPane();
		onglets.addTab("local", ongletLocal());
		onglets.addTab("distant",ongletDistant());

		
		//affichage du JFRAME
		setContentPane(onglets);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	/**
	 * methode charg�e de mettre � jour l'affichage des blagues
	 * � partir de bp
	 */
	public void MaJBlagues() throws BlagueAbsenteException, RemoteException {

    Object[] tab=new String[bp.getCollection().keySet().size()];
    int i = 0;
    for (Object mapKey : bp.getCollection().keySet()) {
        tab[i] = mapKey;
        i++;
    }
    blaguesLocales.setListData(tab);
}


	/**
	 * methode charg�e de mettre � jour l'affichage des serveurs
	 * � partir de bp quand lisRef est modifi�
	 */
	public void MaJServeurs() throws RemoteException, BlagueAbsenteException {
        String[] tab=new String[bp.getListeRef().keySet().size()];
        int i = 0;
        for (Object mapKey : bp.getListeRef().keySet()) {
            tab[i] = (String) mapKey;
            i++;
        }
        serveurs.setListData(tab);
    }

	
	/**
	 * methode charg�e de mettre � jour l'affichage des blagues distantes
	 * quand on selectionne un listeref
	 */
	public void MaJBlagueDist() throws RemoteException, BlagueAbsenteException {
        ArrayList<String> as=new ArrayList<String>();
        for (Iterator<BlagueProviderPairApair> i = bp.getListeRef().values().iterator() ; i.hasNext() ;) {
            BlagueProviderPairApair blaguep = i.next();
            for (Object mapKey : blaguep.getCollection().keySet()) {
                as.add(blaguep.getBlague(mapKey).getNom());
            }
        }

        Object[]ta = new Object[as.size()];

        for(int s =0;s<as.size();s++){
            ta[s] = as.get(s);
        }

        blaguesDistantes.setListData(ta);
	}
	
	
	public static void main (String args[])
	{
        try {
            InterfaceGraphique test=new InterfaceGraphique("toto",new BlagueProvider("toto"));
        } catch (BlagueAbsenteException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
	
	
}