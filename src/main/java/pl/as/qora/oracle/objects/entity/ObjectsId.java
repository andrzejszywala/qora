package pl.as.qora.oracle.objects.entity;

import java.io.Serializable;
import java.util.Objects;

public class ObjectsId implements Serializable {
	public String owner;

	public String objectName;

	@Override
	public int hashCode() {
		return Objects.hash(objectName, owner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjectsId other = (ObjectsId) obj;
		return Objects.equals(objectName, other.objectName) && Objects.equals(owner, other.owner);
	}


}
