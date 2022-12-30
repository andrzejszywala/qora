package pl.as.qora.oracle.rollback.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class RollbackSegId implements Serializable {
	
	public String segmentName;
	public String owner;

	@Override
	public int hashCode() {
		return Objects.hash(owner, segmentName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RollbackSegId other = (RollbackSegId) obj;
		return Objects.equals(owner, other.owner) && Objects.equals(segmentName, other.segmentName);
	}

}
