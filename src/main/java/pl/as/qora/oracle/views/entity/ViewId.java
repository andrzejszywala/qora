package pl.as.qora.oracle.views.entity;

import java.io.Serializable;
import java.util.Objects;

public class ViewId implements Serializable {
	public String owner;

	public String viewName;

	@Override
	public int hashCode() {
		return Objects.hash(viewName, owner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ViewId other = (ViewId) obj;
		return Objects.equals(viewName, other.viewName) && Objects.equals(owner, other.owner);
	}

}
