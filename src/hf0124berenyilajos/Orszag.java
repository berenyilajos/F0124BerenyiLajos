package hf0124berenyilajos;

public class Orszag {
  private String azon;
  private int regioAzon;
  private String name;

  public Orszag(String azon, int regioAzon, String name) {
    this.azon = azon;
    this.regioAzon = regioAzon;
    this.name = name;
  }

  public String getAzon() {
    return azon;
  }

  public int getRegioAzon() {
    return regioAzon;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }
  
  
  
}
