import java.math.BigDecimal;

/**
 * Created by mrtn on 15-12-15.
 */
public class AngularConnectionsResultObject {

    private BigDecimal unitId;
    private Integer connections;

    public AngularConnectionsResultObject(BigDecimal unitId, Integer connections) {
        this.unitId = unitId;
        this.connections = connections;
    }


    public BigDecimal getUnitId() {
        return unitId;
    }

    public void setUnitId(BigDecimal unitId) {
        this.unitId = unitId;
    }

    public Integer getConnections() {
        return connections;
    }

    public void setConnections(Integer connections) {
        this.connections = connections;
    }
}
