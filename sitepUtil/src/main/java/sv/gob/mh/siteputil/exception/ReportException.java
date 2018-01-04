package sv.gob.mh.siteputil.exception;

import java.util.Map;
import sv.gob.mh.sitepcommon.util.enums.ExceptionAdvancePatameterEnum;
import sv.gob.mh.sitepcommon.util.exception.TechnicalException;
import static sv.gob.mh.sitepcommon.util.exception.TechnicalException.REPORT_ERROR_CODE;


public class ReportException extends TechnicalException {

	/**
	 * Serial de la Clase
	 */
	private static final long serialVersionUID = 1927573842213613177L;

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
	public ReportException(final String messageSummary, final String messageCodeDetail,
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
	public ReportException(final String messageSummary, final String messageCodeDetail, final String messageLog,
			Map<ExceptionAdvancePatameterEnum, ?> adavanceParameters) {
		super(messageSummary, messageCodeDetail, messageLog, adavanceParameters);
		this.setErrorCode(REPORT_ERROR_CODE);
	}

}
