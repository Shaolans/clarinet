package database;
public interface Database {
	 
	String POSTGRESQL_URL = "jdbc:postgresql://ec2-54-217-218-80.eu-west-1.compute.amazonaws.com:5432/db0soifg2usrm6?user=rgffqsjedycebs&password=9061e68bf9221690bfcec4712223d0a2745c1c305c164ed33de5a0163338c025";
	String POSTGRESQL_URI="jdbc:postgresql://rgffqsjedycebs:9061e68bf9221690bfcec4712223d0a2745c1c305c164ed33de5a0163338c025@ec2-54-217-218-80.eu-west-1.compute.amazonaws.com:5432/db0soifg2usrm6";  
	String POSTGRESQL_USERNAME="rgffqsjedycebs";  
	String POSTGRESQL_PASSWORD="9061e68bf9221690bfcec4712223d0a2745c1c305c164ed33de5a0163338c025";
	String POSTGRESQL_HOST = "ec2-54-217-218-80.eu-west-1.compute.amazonaws.com";
	String POSTGRESQL_DATABASE = "db0soifg2usrm6";
	int POSTGRESQL_PORT = 5432;
	
	String MONGO_USERNAME = "admin";
	String MONGO_MDP = "clarinet2018";
	String MONGO_URL = "ds237072.mlab.com";
	int MONGO_PORT = 37072;
	String MONGO_DB = "clarinetdatabase";
	

}
