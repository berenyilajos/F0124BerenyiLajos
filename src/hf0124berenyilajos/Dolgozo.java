
package hf0124berenyilajos;

public class Dolgozo {
  private String orszag, varos, cim, empName;

  public Dolgozo(String orszag, String varos, String cim, String empName) {
    this.orszag = orszag;
    this.varos = varos;
    this.cim = cim;
    this.empName = empName;
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

  public String getEmpName() {
    return empName;
  }

  @Override
  public String toString() {
    return "Dolgozo{" + "orszag=" + orszag + ", varos=" + varos + ", cim=" + cim + ", empName=" + empName + '}';
  }
  
  
  
}
