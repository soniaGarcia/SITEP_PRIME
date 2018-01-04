package sv.gob.mh.sitepcommon.util.exception;

import java.util.Map;
import sv.gob.mh.sitepcommon.util.enums.ExceptionAdvancePatameterEnum;

public class DatabaseException extends TechnicalException {

	/**
	 * Serial de la Clase
	 */
	private static final long serialVersionUID = -2902832572658336785L;

	/**
	 * Constructor de la Excepción.
	 * 
	 * @param messageSummary
	 *            Resumen del Mensaje.
	 * @param messageCodeDetail
	 *            Códido del Detalle del Mensaje.
	 * @param adavanceParameters
	 *            Parametros mas avanzados para crear la exepción
	 */
	public DatabaseException(final String messageSummary, final String messageCodeDetail,
			Map<ExceptionAdvancePatameterEnum, ?> adavanceParameters) {
		this(messageSummary, messageCodeDetail, null, adavanceParameters);
	}

	/**
	 * Constructor de la Excepción.
	 * 
	 * @param messageSummary
	 *            Resumen del Mensaje.
	 * @param messageCodeDetail
	 *            Códido del Detalle del Mensaje.
	 * @param messageLog
	 *            Mensaje del log.
	 * @param adavanceParameters
	 *            Parametros mas avanzados para crear la exepción
	 */
	public DatabaseException(final String messageSummary, final String messageCodeDetail, final String messageLog,
			Map<ExceptionAdvancePatameterEnum, ?> adavanceParameters) {
		super(messageSummary, messageCodeDetail, messageLog, adavanceParameters);
		this.setErrorCode(DATABASE_ERROR_CODE);
	}

}
