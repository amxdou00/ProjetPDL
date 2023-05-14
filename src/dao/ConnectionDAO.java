package dao;


public class ConnectionDAO {
	
	public final static String URL   = "jdbc:oracle:thin:@oracle.esigelec.fr:1521:orcl";
	public final static String LOGIN = "C##BDD9_8"; 
	public final static String PASS  = "BDD98";   
	
	
	public ConnectionDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}
	}
}
