package pl.as.qora.oracle.sequence.entity;

import java.io.Serializable;
import java.util.Objects;

public class SequenceId implements Serializable {
	public String sequenceOwner;

	public String sequenceName;

	@Override
	public int hashCode() {
		return Objects.hash(sequenceName, sequenceOwner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SequenceId other = (SequenceId) obj;
		return Objects.equals(sequenceName, other.sequenceName) && Objects.equals(sequenceOwner, other.sequenceOwner);
	}

}
