package codebase;
import java.io.Serializable;
public class Blague implements Serializable{

	private String nom;
	private String question;
	private String reponse;
	
	public Blague(String n,String q, String r){
		this.nom = n ;
		this.question = q;
		this.reponse = r;
	}
	
	public String getQuestion(){
		return this.question;
	}
	public String getReponse(){
		return this.reponse;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	
	
}
