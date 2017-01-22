package hf0124berenyilajos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model implements Adatok {
  private static Model model = null;
  private Connection kapcsolat;
  
  private Model() {
    
  }
  
  public static Model getInstance() {
    if (model == null)
      model = new Model();
    return model;
  }
  
  private void connect() throws SQLException, ClassNotFoundException {
    Class.forName(DRIVER);
    kapcsolat = DriverManager.getConnection(
              URL,
              USERNAME,
              PASSWORD);
  }
  
  private void connectClose() throws SQLException {
    if (!kapcsolat.isClosed())
      kapcsolat.close();
  }
  
  // Csak azokat az országokat adja vissza, ahol vannak dolgozók nyilvántartva
  public Orszag[] orszagok() {
    ArrayList<Orszag> orszagok = new ArrayList<>();
    orszagok.add(new Orszag("", 0, "Mind"));
    try {
      connect();
      Statement stm = kapcsolat.createStatement();
      String SQL = "SELECT DISTINCT C.COUNTRY_ID azon, REGION_ID regioAzon, COUNTRY_Name name\n" +
                    "FROM COUNTRIES C JOIN LOCATIONS L ON C.COUNTRY_ID=L.COUNTRY_ID\n"
              + "JOIN DEPARTMENTS D ON L.LOCATION_ID=D.LOCATION_ID JOIN EMPLOYEES E ON D.DEPARTMENT_ID=E.DEPARTMENT_ID\n" +
                    "ORDER BY name";
      ResultSet rs = stm.executeQuery(SQL);
      while (rs.next()) {
        orszagok.add(new Orszag(rs.getString("azon"), rs.getInt("regioAzon"), rs.getString("name")));
      }
      connectClose();
    }
    catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
      return new Orszag[]{new Orszag("", 0, "Mind")};
    }
    return orszagok.toArray(new Orszag[orszagok.size()]);
  }
  
  public ArrayList<Dolgozo> dolgozok(String countryId) {
    ArrayList<Dolgozo> dolgozok = new ArrayList<>();
    try {
      connect();
      String SQL = "SELECT COUNTRY_NAME orszag, CITY varos, STREET_ADDRESS cim, FIRST_NAME || ' ' || LAST_NAME AS empName\n" +
"FROM LOCATIONS L JOIN DEPARTMENTS D ON L.LOCATION_ID=D.LOCATION_ID\n"
              + "JOIN EMPLOYEES E ON D.DEPARTMENT_ID=E.DEPARTMENT_ID JOIN COUNTRIES C ON C.COUNTRY_ID=L.COUNTRY_ID\n" +
(countryId.isEmpty() ? "" : "WHERE C.COUNTRY_ID=?\n") +
              "ORDER BY orszag, varos, cim, empName";
//      System.out.println(SQL);
      PreparedStatement pstm = kapcsolat.prepareStatement(SQL);
      if (!countryId.isEmpty())
        pstm.setString(1, countryId);
      ResultSet rs = pstm.executeQuery();
      while (rs.next()) {
        dolgozok.add(new Dolgozo(rs.getString("orszag"), rs.getString("varos"),
                rs.getString("cim"), rs.getString("empName")));
      }
      connectClose();
    }
    catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
      return new ArrayList<>();
    }
    return dolgozok;
  }
  
}
