package pl.as.qora.oracle.synonyms.entity;

import java.io.Serializable;
import java.util.Objects;

public class SynonymId implements Serializable {
	public String owner;

	public String synonymName;

	@Override
	public int hashCode() {
		return Objects.hash(owner, synonymName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SynonymId other = (SynonymId) obj;
		return Objects.equals(owner, other.owner) && Objects.equals(synonymName, other.synonymName);
	}

}
