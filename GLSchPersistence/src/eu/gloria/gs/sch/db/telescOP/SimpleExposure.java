package eu.gloria.gs.sch.db.telescOP;

import java.io.Serializable;
import javax.persistence.*;

import eu.gloria.gs.sch.db.gloriaOP.OPsegmentation;

import java.util.Set;


/**
 * The persistent class for the SimpleExposure database table.
 * 
 */
@Entity
public class SimpleExposure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	private String filter;

	private int reallocated;

	private String simpleExid;

	@Enumerated( EnumType.ORDINAL)
	private TelescopeOPState stateId;

	private String targetName;

	private String targetRADEC;

	//bi-directional many-to-one association to AvailableTelescope
	@OneToMany(mappedBy="simpleExposure")
	private Set<AvailableTelescope> availableTelescopes;

	//bi-directional many-to-one association to OPsegmentation
	@OneToMany(mappedBy="simpleExposure")
	private Set<OPsegmentation> opsegmentations;

	//bi-directional many-to-one association to AvailableTelescope
    @ManyToOne
	@JoinColumn(name="chosenTelescopeId")
	private AvailableTelescope availableTelescope;

    public SimpleExposure() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilter() {
		return this.filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public int getReallocated() {
		return this.reallocated;
	}

	public void setReallocated(int reallocated) {
		this.reallocated = reallocated;
	}

	public String getSimpleExid() {
		return this.simpleExid;
	}

	public void setSimpleExid(String simpleExid) {
		this.simpleExid = simpleExid;
	}

	public TelescopeOPState getStateId() {
		return this.stateId;
	}

	public void setStateId(TelescopeOPState stateId) {
		this.stateId = stateId;
	}

	public String getTargetName() {
		return this.targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTargetRADEC() {
		return this.targetRADEC;
	}

	public void setTargetRADEC(String targetRADEC) {
		this.targetRADEC = targetRADEC;
	}

	public Set<AvailableTelescope> getAvailableTelescopes() {
		return this.availableTelescopes;
	}

	public void setAvailableTelescopes(Set<AvailableTelescope> availableTelescopes) {
		this.availableTelescopes = availableTelescopes;
	}
	
	public Set<OPsegmentation> getOpsegmentations() {
		return this.opsegmentations;
	}

	public void setOpsegmentations(Set<OPsegmentation> opsegmentations) {
		this.opsegmentations = opsegmentations;
	}
	
	public AvailableTelescope getAvailableTelescope() {
		return this.availableTelescope;
	}

	public void setAvailableTelescope(AvailableTelescope availableTelescope) {
		this.availableTelescope = availableTelescope;
	}
	
}