package eu.gloria.gs.sch.db.gloriaOP;

import java.io.Serializable;
import javax.persistence.*;

import eu.gloria.gs.sch.db.telescOP.SimpleExposure;


/**
 * The persistent class for the OPsegmentation database table.
 * 
 */
@Entity
public class OPsegmentation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	//bi-directional many-to-one association to GloriaOP
    @ManyToOne
	@JoinColumn(name="glOPid")
	private GloriaOP gloriaOp;

	//bi-directional many-to-one association to SimpleExposure
    @ManyToOne
	@JoinColumn(name="simpleEXid")
	private SimpleExposure simpleExposure;

    public OPsegmentation() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GloriaOP getGloriaOp() {
		return this.gloriaOp;
	}

	public void setGloriaOp(GloriaOP gloriaOp) {
		this.gloriaOp = gloriaOp;
	}
	
	public SimpleExposure getSimpleExposure() {
		return this.simpleExposure;
	}

	public void setSimpleExposure(SimpleExposure simpleExposure) {
		this.simpleExposure = simpleExposure;
	}
	
}