package hf0124berenyilajos;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class Nezet extends JFrame implements ItemListener {

  private JComboBox cb;
  private JTable tDolgozok;
  private ArrayList<Dolgozo> dolgozok;
  private JScrollPane spDolgozok;
  private JLabel lDolgozokSzama;

  public Nezet() {
    super("Dolgozók listája");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(800, 500);
    JPanel pnFent = new JPanel();
    pnFent.add(new JLabel("Ország:  "));
    cb = new JComboBox(Model.getInstance().orszagok());
    pnFent.add(cb);
    cb.addItemListener(this);
    dolgozok = Model.getInstance().dolgozok("");
    lDolgozokSzama = new JLabel("  " + dolgozok.size() + " dolgozó.");
    pnFent.add(lDolgozokSzama);
    add(pnFent, BorderLayout.PAGE_START);
    tDolgozok = new JTable(new TableModel() {
      @Override
      public int getRowCount() {
        return dolgozok.size();
      }

      @Override
      public int getColumnCount() {
        return 4;
      }

      @Override
      public String getColumnName(int columnIndex) {
        switch (columnIndex) {
          case 0:
            return "ORSZÁG";
          case 1:
            return "VÁROS";
          case 2:
            return "CÍM";
          default:
            return "DOLGZÓ NEVE";
        }
      }

      @Override
      public Class<?> getColumnClass(int columnIndex) {
        return String.class;
      }

      @Override
      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
      }

      @Override
      public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
          case 0:
            return dolgozok.get(rowIndex).getOrszag();
          case 1:
            return dolgozok.get(rowIndex).getVaros();
          case 2:
            return dolgozok.get(rowIndex).getCim();
          default:
            return dolgozok.get(rowIndex).getDolgozoNev();
        }
      }

      @Override
      public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

      }

      @Override
      public void addTableModelListener(TableModelListener l) {

      }

      @Override
      public void removeTableModelListener(TableModelListener l) {

      }
    });
    tDolgozok.setAutoCreateRowSorter(true);
    spDolgozok = new JScrollPane(tDolgozok);
    add(spDolgozok);
    setLocationRelativeTo(this);
    setVisible(true);
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.SELECTED) {
      tDolgozok.setAutoCreateRowSorter(false);
      dolgozok = Model.getInstance().dolgozok(((Orszag) cb.getSelectedItem()).getAzon());
      spDolgozok.revalidate();
      spDolgozok.repaint();
      lDolgozokSzama.setText("  " + dolgozok.size() + " dolgozó.");
      tDolgozok.setAutoCreateRowSorter(true);
    }
  }

}
