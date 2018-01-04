package sv.gob.mh.sitepcommon.util.exception;

import java.util.Map;

import javax.ejb.ApplicationException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import sv.gob.mh.sitepcommon.util.enums.ExceptionAdvancePatameterEnum;
import sv.gob.mh.sitepcommon.util.enums.ExceptionSeverityEnum;

@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@ApplicationException(rollback=true)
public class FunctionalException extends Exception {

	/**
	 * Serial de la Clase
	 */
	private static final long serialVersionUID = 2141997906717026749L;

	/**
	 * message Summary.
	 */
	private String messageSummary;

	/**
	 * message Code Detail.
	 */
	private String messageCodeDetail;

	/**
	 * message Detail.
	 */
	private String messageDetail;

	/**
	 * message Parameters.
	 */
	private Object[] messageParameters;

	/**
	 * severity.
	 */
	private ExceptionSeverityEnum severity= ExceptionSeverityEnum.SEVERE;

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
	public FunctionalException(final String messageSummary, final String messageCodeDetail,
			Map<ExceptionAdvancePatameterEnum, ?> adavanceParameters) {

		this.messageSummary = messageSummary;
		this.messageCodeDetail = messageCodeDetail;

		if (adavanceParameters != null) {
			Object ojectParameter = adavanceParameters.get(ExceptionAdvancePatameterEnum.MESSAGE_PARAMETERS);

			if (ojectParameter != null && ojectParameter instanceof Object[]) {
				this.messageParameters = (Object[]) ojectParameter;
			}

			ojectParameter = adavanceParameters.get(ExceptionAdvancePatameterEnum.SEVERITY);

			if (ojectParameter != null && ojectParameter instanceof ExceptionSeverityEnum) {
				this.severity = (ExceptionSeverityEnum) ojectParameter;
			}
		}
	}

}
