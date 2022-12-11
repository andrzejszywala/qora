package pl.as.qora.oracle.tables.entity;

import java.io.Serializable;
import java.util.Objects;

public class TablesId implements Serializable {
	public String owner;

	public String tableName;

	@Override
	public int hashCode() {
		return Objects.hash(owner, tableName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TablesId other = (TablesId) obj;
		return Objects.equals(owner, other.owner) && Objects.equals(tableName, other.tableName);
	}

}
