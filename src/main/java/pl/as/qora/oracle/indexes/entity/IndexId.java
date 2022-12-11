package pl.as.qora.oracle.indexes.entity;

import java.io.Serializable;
import java.util.Objects;

public class IndexId implements Serializable {
	public String owner;

	public String indexName;

	@Override
	public int hashCode() {
		return Objects.hash(indexName, owner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IndexId other = (IndexId) obj;
		return Objects.equals(indexName, other.indexName) && Objects.equals(owner, other.owner);
	}

}
