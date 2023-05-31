package model;

public class Grupos {
	  public String nome;
      public String tema;
      public String area;
      public String subarea;
      public String data;
      public String codigo;
      public  String Ra1;
      public  String Ra2;
      public  String Ra3;
      public  String Ra4;

	@Override
	public String toString() {
		return nome+";"+tema+";"+area+";"+subarea+";" +data+ ";" +codigo +";" + Ra1 + ";" + Ra2 + ";" + Ra3 + ";" + Ra4;
	}
  
	
	
}