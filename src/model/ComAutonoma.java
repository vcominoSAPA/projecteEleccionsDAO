package model;

public class ComAutonoma {
    int comunitat_aut_id;
    String nom = null;
    String codi_ine;

    public ComAutonoma(int comunitat_aut_id, String nom, String codi_ine) {
        this.comunitat_aut_id = comunitat_aut_id;
        this.nom = nom;
        this.codi_ine = codi_ine;
    }

    public ComAutonoma(int comunitat_aut_id) {
        this.comunitat_aut_id = comunitat_aut_id;
    }

    public ComAutonoma(String nom, String codi_ine) {
        this.nom = nom;
        this.codi_ine = codi_ine;
    }

    public void set(String nom, String codi_ine) {
        this.nom = nom;
        this.codi_ine = codi_ine;
    }

    public int getId() {
        return comunitat_aut_id;
    }

    public void setId(int comunitat_aut_id) {
        this.comunitat_aut_id = comunitat_aut_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodiIne() {
        return codi_ine;
    }

    public void setCodiIne(String codi_ine) {
        this.codi_ine = codi_ine;
    }

    @Override
    public String toString() {
        return "ComAutonoma{" +
                "comunitat_aut_id=" + comunitat_aut_id +
                ", nom='" + nom + '\'' +
                ", codi_ine='" + codi_ine + '\'' +
                '}';
    }
}
