package design.swira.aennyapp.pojo.aenny;

import java.io.Serializable;

public class DisabilitiesTypesidData implements Serializable {
    int disabilitytypeid;

    public DisabilitiesTypesidData(int disabilitytypeid) {
        this.disabilitytypeid = disabilitytypeid;
    }

    public int getDisabilitytypeid() {
        return disabilitytypeid;
    }

    public void setDisabilitytypeid(int disabilitytypeid) {
        this.disabilitytypeid = disabilitytypeid;
    }
}
