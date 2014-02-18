package eu.gloria.gs.sch.db.telescOP;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the AvailableTelescopes database table.
 * 
 */
@Entity
@Table(name="AvailableTelescopes")
public class AvailableTelescope implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private double magnitude;

	//bi-directional many-to-one association to SimpleExposure
    @ManyToOne
	@JoinColumn(name="simpleExid")
	private SimpleExposure simpleExposure;

	//bi-directional many-to-one association to TelescopeList
    @ManyToOne
	@JoinColumn(name="telescopeId")
	private TelescopeList telescopeList;

	//bi-directional many-to-one association to SimpleExposure
	@OneToMany(mappedBy="availableTelescope")
	private Set<SimpleExposure> simpleExposures;

    public AvailableTelescope() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMagnitude() {
		return this.magnitude;
	}

	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}

	public SimpleExposure getSimpleExposure() {
		return this.simpleExposure;
	}

	public void setSimpleExposure(SimpleExposure simpleExposure) {
		this.simpleExposure = simpleExposure;
	}
	
	public TelescopeList getTelescopeList() {
		return this.telescopeList;
	}

	public void setTelescopeList(TelescopeList telescopeList) {
		this.telescopeList = telescopeList;
	}
	
	public Set<SimpleExposure> getSimpleExposures() {
		return this.simpleExposures;
	}

	public void setSimpleExposures(Set<SimpleExposure> simpleExposures) {
		this.simpleExposures = simpleExposures;
	}
	
}