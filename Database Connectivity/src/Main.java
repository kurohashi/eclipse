import executeSQLQuery.ExecuteSQLStatement;


public class Main {

	public static void main(String[] args) {
		ExecuteSQLStatement e = new ExecuteSQLStatement();
		e.showData("select * from db;");

	}

}
