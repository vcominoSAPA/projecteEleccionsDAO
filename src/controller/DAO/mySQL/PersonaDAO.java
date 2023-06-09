package controller.DAO.mySQL;

import controller.DAO.DAODB;
import model.Persona;


import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class PersonaDAO implements DAODB<Persona> {

    /**
     * Insereix una persona a la BD amb els valors de la persona passada per paràmetres.
     * @param p Persona a inserir.
     * @return true si s'ha inserit correctament, false si no.
     */
    @Override
    public boolean create(Persona p) {
        // INSERT SQL
        String query = "INSERT INTO persones (nom,cog1,cog2,sexe,data_naixement,dni) VALUES (?,?,?,?,?,?)";

        // Escrivim a la BD amb els valors de la persona passada per paràmetres
        int r = DBMySQLManager.write(query,
                                        p.getNom(),
                                        p.getCog1(),
                                        p.getCog2(),
                                        p.getSexe(),
                                        p.getDataNaixement(),
                                        p.getDni());

        // Si s'ha modificat alguna fila, retornem true
        return r > 0;
    }

    /**
     * Actualitza la persona passada per paràmetres amb
     * els valors de la persona de la BD amb el mateix id.
     * @param p Persona a actualitzar.
     * @return true si s'ha actualitzat correctament,
     * false si no existeix a la BD.
     */
    @Override
    public boolean read (Persona p) {
        // Obtenim una candidatura de la BD amb el mateix id que la candidatura passada per paràmetres
        Persona pr = readById(p.getId());

        // Si no existeix, retornem false
        if (pr == null) return false;

        // Si existeix, actualitzem la persona passada per paràmetres amb els valors de la persona de la BD
        p.set(pr.getNom(), pr.getCog1(), pr.getCog2(), pr.getSexe(), pr.getDataNaixement(), pr.getDni());

        // i retornem true
        return true;
    }

    /**
     * Retorna un objecte Persona amb els valors del registre
     * de la taula persones amb l'id passat per paràmetres.
     * @param id Id de la persona a obtenir.
     * @return Persona amb els valors del registre de la taula
     * persones amb l'id passat per paràmetres o null si no existeix.
     */
    @Override
    public Persona readById (Object id) {

        // SELECT SQL
        String query = "SELECT nom,cog1,cog2,sexe,data_naixement,dni FROM persones WHERE persona_id=?";

        // Llegim de la BD amb l'id de la persona passada per paràmetres
        List<Object[]> r = DBMySQLManager.read(query, id);

        // Si no existeix, retornem null
        if (r.size() != 1L) return null;

        // Si existeix, retornem la persona
        Object[] row = r.iterator().next();

        String nom = (String)row[0];
        String cog1 = (String)row[1];
        String cog2 = (String)row[2];
        String sexe = (String)row[3];
        Date dataNaixement = (Date) row[4];
        String dni = (String)row[5];

        return new Persona((long)id, nom, cog1, cog2, sexe, dataNaixement, dni);
    }


    /**
     * Actualitza la persona de la BD amb el mateix id que la persona passada
     * per paràmetres amb els valors de la persona passada per paràmetres.
     * @param p Persona a actualitzar.
     * @return true si s'ha actualitzat correctament, false si no.
     */
    @Override
    public boolean update(Persona p) {
        // UPDATE SQL
        String query = "UPDATE persones SET nom=?,cog1=?,cog2=?,sexe=?,data_naixement=?,dni=? WHERE persona_id=?";

        // Actualitzem la BD amb els valors de la persona passada per paràmetres
        String nom = p.getNom();
        String cog1 = p.getCog1();
        String cog2 = p.getCog2();
        String sexe = String.valueOf(p.getSexe());
        Date dataNaixement = p.getDataNaixement();
        String dni = p.getDni();
        long persona_id = p.getId();

        int r = DBMySQLManager.write(query, nom, cog1, cog2, sexe, dataNaixement, dni, persona_id);

        // Si s'ha modificat alguna fila, retornem true
        return r > 0;
    }

    /**
     * Actualitza només els camps passats per paràmetre de la persona
     * de la BD amb el mateix id que la persona passada per paràmetres
     * amb els valors de la persona passada per paràmetres.
     * @param p Persona a actualitzar.
     * @param camps Camps a actualitzar.
     * @return true si s'ha actualitzat correctament, false si no.
     */
    @Override
    public boolean update(Persona p, String... camps) {
        // Creem un array de paràmetres de la mida de la quantitat de camps + 1 (per l'id)
        Object[] params = new Object[camps.length + 1];

        // Construïm la query
        StringBuilder query = new StringBuilder("UPDATE persones SET ");

        // Per cada camp,
        for (int i = 0; i < camps.length; i++) {

            // Afegim el camp a la query
            query.append(camps[i]).append("=?");

            // i una coma si no és l'últim camp
            if (i < camps.length - 1) query.append(",");

            // També afegim el valor del camp a l'array de paràmetres
            switch (camps[i]) {
                case "nom" -> params[i] = p.getNom();
                case "cog1" -> params[i] = p.getCog1();
                case "cog2" -> params[i] = p.getCog2();
                case "sexe" -> params[i] = p.getSexe();
                case "data_naixement" -> params[i] = p.getDataNaixement();
                case "dni" -> params[i] = p.getDni();
                default -> throw new IllegalArgumentException("El camp " + camps[i] + " no existeix");
            }
        }

        // Afegim l'id al final de la query
        query.append(" WHERE persona_id=?");
        params[params.length - 1] = p.getId();

        // Executem la query
        int r = DBMySQLManager.write(query.toString(), params);

        // Si s'ha modificat alguna fila, retornem true
        return r > 0;
    }

    /**
     * Elimina la persona passada per paràmetres de la BD.
     * @param p Persona a eliminar.
     * @return true si s'ha eliminat correctament, false si no.
     */
    @Override
    public boolean delete(Persona p) {
        // DELETE SQL
        String query = "DELETE FROM persones WHERE persona_id=?";

        // Eliminem de la BD la persona passada per paràmetres
        int r = DBMySQLManager.write(query, p.getId());

        // Si s'ha eliminat alguna fila, retornem true
        return r > 0;
    }

    /**
     * Cerca persones a la BD amb el valor del camp passat per paràmetres.
     * @param camp camp pel qual es vol cercar.
     * @param valor valor del camp.
     * @return llista de persones trobades a la cerca.
     */
    @Override
    public List<Persona> search(String camp, Object valor) {
        // Creem una llista buida de persones
        List<Persona> l = new LinkedList<>();

        // Query per obtenir totes les persones aamb el valor del camp passat per paràmetres
        String query = "SELECT * FROM persones WHERE " + camp + "=?";

        // Executem la query
        List<Object[]> r = DBMySQLManager.read(query, valor);

        // Per cada resultat obtingut
        for (Object[] row : r) {
            // Obtenim els valors dels camps de la persona
            long id = (long)row[0];
            String nom = (String)row[1];
            String cog1 = (String)row[2];
            String cog2 = (String)row[3];
            String sexe = (String)row[4];
            String dni = (String)row[5];
            Date dataNaixement = (Date) row[6];

            // Creem una nova persona amb els valors obtinguts
            Persona p = new Persona(id, nom, cog1, cog2, sexe, dataNaixement, dni);

            // Afegim la persona a la llista
            l.add(p);
        }

        // Retornem la llista de persones
        return l.size() > 0 ? l : null;
    }

    /**
     * Comprova si una persona existeix a la BD.
     * @param p Persona a comprovar.
     * @return true si la persona existeix a la BD, false si no.
     */
    @Override
    public boolean exists(Persona p) {
        return read(p);
    }

    /**
     * Fa el recompte de registres a la taula persones.
     * @return nombre de registres de la taula persones.
     */
    @Override
    public long count() {
        // Query per comptar el nombre de files de la taula
        String query = "SELECT COUNT(*) FROM persones";

        // Executem la query
        List<Object[]> r = DBMySQLManager.read(query);

        // Si la llista no te un sol element, retornem -1 (s'ha produit un error)
        if (r.size() != 1) return -1;

        // Retornem el nombre de files de la taula
        Object[] o = r.iterator().next();
        return (long)o[0];
    }

    /**
     * Retorna totes les persones de la BD.
     * @return llista de persones de la BD.
     */
    @Override
    public List<Persona> all() {
        // Creem una llista buida de persones
        List<Persona> l = new LinkedList<>();

        // Query per obtenir totes les persones
        String query = "SELECT persona_id,nom,cog1,cog2,sexe,data_naixement,dni FROM persones";

        // Executem la query
        List<Object[]> r = DBMySQLManager.read(query);

        // Per cada registre
        for (Object[] row : r) {

            // Obtenim les dades de la persona
            long id = (long) row[0];
            String nom = (String) row[1];
            String cog1 = (String) row[2];
            String cog2 = (String) row[3];
            String sexe = (String) row[4];
            Date dataNaixement = (Date) row[5];
            String dni = (String) row[6];

            // Afegim la persona a la llista
            l.add(new Persona(id, nom, cog1, cog2, sexe, dataNaixement, dni));
        }

        // Retornem la llista
        return l.size() > 0 ? l : null;
    }
}
