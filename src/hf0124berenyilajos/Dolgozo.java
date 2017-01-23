package hf0124berenyilajos;

public class Dolgozo {

  private String orszag, varos, cim, dolgozoNev;

  public Dolgozo(String orszag, String varos, String cim, String dolgozoNev) {
    this.orszag = orszag;
    this.varos = varos;
    this.cim = cim;
    this.dolgozoNev = dolgozoNev;
  }

  public String getOrszag() {
    return orszag;
  }

  public String getVaros() {
    return varos;
  }

  public String getCim() {
    return cim;
  }

  public String getDolgozoNev() {
    return dolgozoNev;
  }

  @Override
  public String toString() {
    return "Dolgozo{" + "orszag=" + orszag + ", varos=" + varos + ", cim=" + cim + ", dolgozoNev=" + dolgozoNev + '}';
  }

}
