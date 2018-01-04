package sv.gob.mh.sitepcommon.util.exception;

import java.util.Map;
import sv.gob.mh.sitepcommon.util.enums.ExceptionAdvancePatameterEnum;

public class BusinessException extends TechnicalException {

	/**
	 * Serial de la Clase
	 */
	private static final long serialVersionUID = -1686069217698494886L;

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
	public BusinessException(final String messageSummary, final String messageCodeDetail,
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
	public BusinessException(final String messageSummary, final String messageCodeDetail, final String messageLog,
			Map<ExceptionAdvancePatameterEnum, ?> adavanceParameters) {
		super(messageSummary, messageCodeDetail, messageLog, adavanceParameters);
		this.setErrorCode(BUSINESS_ERROR_CODE);
	}

}
