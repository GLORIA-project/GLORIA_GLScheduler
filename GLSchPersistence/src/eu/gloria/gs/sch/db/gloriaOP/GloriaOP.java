package eu.gloria.gs.sch.db.gloriaOP;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the GloriaOP database table.
 * 
 */
@Entity
public class GloriaOP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	private String comment;

	private String description;

	private String user;
	
	private String glOPid;

	@Enumerated( EnumType.ORDINAL)
	private GloriaOPState stateId;

	//bi-directional many-to-one association to OPsegmentation
	@OneToMany(mappedBy="gloriaOp")
	private Set<OPsegmentation> opsegmentations;

    public GloriaOP() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getGlOPid() {
		return this.glOPid;
	}

	public void setGlOPid(String glOPid) {
		this.glOPid = glOPid;
	}

	public GloriaOPState getStateId() {
		return this.stateId;
	}

	public void setStateId(GloriaOPState stateId) {
		this.stateId = stateId;
	}

	public Set<OPsegmentation> getOpsegmentations() {
		return this.opsegmentations;
	}

	public void setOpsegmentations(Set<OPsegmentation> opsegmentations) {
		this.opsegmentations = opsegmentations;
	}
	
}