package sv.gob.mh.sitepcommon.domain.util;

import java.io.Serializable;

public interface BaseEntity<ID> extends Serializable {

	public static final String SEQ_STORE = "SEQ_STORE";

	public ID getId();

	public void setId(ID id);
}
