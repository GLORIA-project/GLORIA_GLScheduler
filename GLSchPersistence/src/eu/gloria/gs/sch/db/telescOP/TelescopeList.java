package eu.gloria.gs.sch.db.telescOP;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;


/**
 * The persistent class for the TelescopeList database table.
 * 
 */
@Entity
public class TelescopeList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int offered;

	private String telescope;

	//bi-directional many-to-one association to AvailableTelescope
	@OneToMany(mappedBy="telescopeList")
	private Set<AvailableTelescope> availableTelescopes;

    public TelescopeList() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOffered() {
		return this.offered;
	}

	public void setOffered(int offered) {
		this.offered = offered;
	}

	public String getTelescope() {
		return this.telescope;
	}

	public void setTelescope(String telescope) {
		this.telescope = telescope;
	}

	public Set<AvailableTelescope> getAvailableTelescopes() {
		return this.availableTelescopes;
	}

	public void setAvailableTelescopes(Set<AvailableTelescope> availableTelescopes) {
		this.availableTelescopes = availableTelescopes;
	}
	
}