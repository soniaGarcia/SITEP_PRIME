
package sv.gob.mh.sitepcommon.util.exception;

import java.util.Map;

import javax.ejb.EJBException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import sv.gob.mh.sitepcommon.util.enums.ExceptionAdvancePatameterEnum;
import sv.gob.mh.sitepcommon.util.enums.ExceptionSeverityEnum;

@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TechnicalException extends EJBException {

	/**
	 * Constante para el código de un error de tipo de base de datos.
	 */
	public static final String DATABASE_ERROR_CODE = "SITEP.0099";

	/**
	 * Constante para el código de un error de tipo de configuración.
	 */
	public static final String CONFIGURATION_ERROR_CODE = "SITEP.0098";

	/**
	 * Constante para el código de un error de tipo de negocio.
	 */
	public static final String BUSINESS_ERROR_CODE = "SITEP.0097";

	/**
	 * Constante para el código de un error de tipo de seguridad.
	 */
	public static final String SECURITY_ERROR_CODE = "SITEP.0097";

	/**
	 * Constante para el código de un error de los reportes.
	 */
	public static final String REPORT_ERROR_CODE = "SITEP.0096";

	/**
	 * Constante para el código de un error de tipo incontrolable en el sistema.
	 */
	public static final String SYSTEM_ERROR_CODE = "SITEP.0095";

	/**
	 * 
	 */
	private static final long serialVersionUID = 7024467796140220234L;

	/**
	 * method name.
	 */
	private String methodName = null;

	/**
	 * class name.
	 */
	private String className = null;

	/**
	 * host name.
	 */
	private String hostName = null;

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
	 * message Detail.
	 */
	private String messageLog;

	/**
	 * message Parameters.
	 */
	private Object[] messageParameters;
	
	/**
	 * message Parameters.
	 */
	private Object[] messageLogParameters;

	/**
	 * error Code.
	 */
	private String errorCode;

	/**
	 * previous exeption.
	 */
	private RuntimeException previousExeption = null;

	/**
	 * severity.
	 */
	private ExceptionSeverityEnum severity = ExceptionSeverityEnum.SEVERE;

	/**
	 * user.
	 */
	private String userName = null;

	private Boolean advanceLog = false;

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
	public TechnicalException(final String messageSummary, final String messageCodeDetail,
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
	public TechnicalException(final String messageSummary, final String messageCodeDetail, final String messageLog,
			Map<ExceptionAdvancePatameterEnum, ?> adavanceParameters) {

		this.messageSummary = messageSummary;
		this.messageCodeDetail = messageCodeDetail;
		this.messageLog = messageLog;

		if (adavanceParameters != null) {
			Object ojectParameter = adavanceParameters.get(ExceptionAdvancePatameterEnum.MESSAGE_PARAMETERS);

			if (ojectParameter != null && ojectParameter instanceof Object[]) {
				this.messageParameters = (Object[]) ojectParameter;
			}
			
			ojectParameter = adavanceParameters.get(ExceptionAdvancePatameterEnum.MESSAGE_LOG_PARAMETERS);

			if (ojectParameter != null && ojectParameter instanceof Object[]) {
				this.messageLogParameters = (Object[]) ojectParameter;
			}

			ojectParameter = adavanceParameters.get(ExceptionAdvancePatameterEnum.SEVERITY);

			if (ojectParameter != null && ojectParameter instanceof ExceptionSeverityEnum) {
				this.severity = (ExceptionSeverityEnum) ojectParameter;
			}

			ojectParameter = adavanceParameters.get(ExceptionAdvancePatameterEnum.CLASS_NAME);

			if (ojectParameter != null && ojectParameter instanceof String) {
				this.className = (String) ojectParameter;
			}

			ojectParameter = adavanceParameters.get(ExceptionAdvancePatameterEnum.METHOD_NAME);

			if (ojectParameter != null && ojectParameter instanceof String) {
				this.methodName = (String) ojectParameter;
			}

			ojectParameter = adavanceParameters.get(ExceptionAdvancePatameterEnum.USER_NAME);

			if (ojectParameter != null && ojectParameter instanceof String) {
				this.userName = (String) ojectParameter;
			}

			ojectParameter = adavanceParameters.get(ExceptionAdvancePatameterEnum.PREVIOUS_EXCEPTION);

			if (ojectParameter != null && ojectParameter instanceof RuntimeException) {
				this.previousExeption = (RuntimeException) ojectParameter;
			}

			ojectParameter = adavanceParameters.get(ExceptionAdvancePatameterEnum.ADVANCE_LOG);

			if (ojectParameter != null && ojectParameter instanceof Boolean) {
				this.advanceLog = (Boolean) ojectParameter;
			}
		}

		this.errorCode = SYSTEM_ERROR_CODE;

	}

}
