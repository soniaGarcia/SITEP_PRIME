package sv.gob.mh.sitepcommon.util.exception;

import java.util.Map;
import sv.gob.mh.sitepcommon.util.enums.ExceptionAdvancePatameterEnum;

/**
 * Clase encargada de manejar la excepciones de Seguridad.
 *
 * 
 */
public class SecurityException extends TechnicalException {

	/**
	 * Serial de la Clase
	 */
	private static final long serialVersionUID = 2940929197295431664L;

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
	public SecurityException(final String messageSummary, final String messageCodeDetail,
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
	public SecurityException(final String messageSummary, final String messageCodeDetail, final String messageLog,
			Map<ExceptionAdvancePatameterEnum, ?> adavanceParameters) {
		super(messageSummary, messageCodeDetail, messageLog, adavanceParameters);
		this.setErrorCode(SECURITY_ERROR_CODE);
	}

}
