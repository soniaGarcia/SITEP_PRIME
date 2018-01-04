package sv.gob.mh.siteputil.exception;

import sv.gob.mh.sitepcommon.util.exception.TechnicalException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.omnifaces.util.Messages;
import org.omnifaces.util.Messages.Message;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import sv.gob.mh.sitepcommon.util.enums.ExceptionAdvancePatameterEnum;
import sv.gob.mh.sitepcommon.util.enums.ExceptionSeverityEnum;
import static sv.gob.mh.sitepcommon.util.enums.ExceptionSeverityEnum.WARN;
import sv.gob.mh.sitepcommon.util.enums.SecurityExceptionMessageConstant;
import sv.gob.mh.sitepcommon.util.exception.BusinessException;
import sv.gob.mh.sitepcommon.util.exception.ConfigurationException;
import sv.gob.mh.sitepcommon.util.exception.DatabaseException;
import sv.gob.mh.sitepcommon.util.exception.FunctionalException;
import sv.gob.mh.sitepcommon.util.exception.SecurityException;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Log
public class ExceptionMessageManager {

	/**
	 * Constante para mensaje de error general de log.
	 */
	private static final String LOG_MESSAGER_ERROR = ": Ocurrió el siguiente error: ";

	/**
	 * Constante para mensaje de error general de log.
	 */
	private static final String LOG_MESSAGER_ERROR2 = " en la clase: ";

	/**
	 * Constante para mensaje de error general de log.
	 */
	private static final String LOG_MESSAGER_ERROR3 = " y el método: ";

	/**
	 * Constante para mensaje de error general de log.
	 */
	private static final String LOG_MESSAGER_ERROR4 = " en el host: ";

	/**
	 * Constante para mensaje de error general de log.
	 */
	private static final String LOG_MESSAGER_ERROR5 = " con el usuario: ";

	/**
	 * Constante para mensaje de error general de log.
	 */

	private static final String LOG_MESSAGER_ERROR6 = " con la excepción original: ";

	public static void throwReportException(final String messageSummary, final String messageCodeDetail) throws TechnicalException {
		throwException(new ReportException(messageSummary, messageCodeDetail, null));
	}

	public static void throwReportException(final String messageSummary, final String messageCodeDetail,
			final Map<ExceptionAdvancePatameterEnum, Object> advanceParameters) throws TechnicalException {
		throwException(new ReportException(messageSummary, messageCodeDetail, advanceParameters));
	}

	public static void throwReportException(final String messageSummary, final String messageCodeDetail,
			final String messageLog) throws TechnicalException {
		throwException(new ReportException(messageSummary, messageCodeDetail, messageLog, null));
	}

	public static void throwReportException(final String messageSummary, final String messageCodeDetail,
			final String messageLog, final Map<ExceptionAdvancePatameterEnum, Object> advanceParameters) throws TechnicalException {
		throwException(new ReportException(messageSummary, messageCodeDetail, messageLog, advanceParameters));
	}

	public static void throwConfigurationException(final String messageSummary, final String messageCodeDetail) throws TechnicalException {
		throwException(new ReportException(messageSummary, messageCodeDetail, null));
	}

	public static void throwConfigurationException(final String messageSummary, final String messageCodeDetail,
			final Map<ExceptionAdvancePatameterEnum, Object> advanceParameters) throws TechnicalException {
		throwException(new ConfigurationException(messageSummary, messageCodeDetail, advanceParameters));
	}

	public static void throwConfigurationException(final String messageSummary, final String messageCodeDetail,
			final String messageLog) throws TechnicalException {
		throwException(new ConfigurationException(messageSummary, messageCodeDetail, messageLog, null));
	}

	public static void throwConfigurationException(final String messageSummary, final String messageCodeDetail,
			final String messageLog, final Map<ExceptionAdvancePatameterEnum, Object> advanceParameters) throws TechnicalException {
		throwException(new ConfigurationException(messageSummary, messageCodeDetail, messageLog, advanceParameters));
	}

	public static void throwBusinessException(final String messageSummary, final String messageCodeDetail) throws TechnicalException {
		throwException(new BusinessException(messageSummary, messageCodeDetail, null));
	}

	public static void throwBusinessException(final String messageSummary, final String messageCodeDetail,
			final Map<ExceptionAdvancePatameterEnum, Object> advanceParameters) throws TechnicalException {
		throwException(new BusinessException(messageSummary, messageCodeDetail, advanceParameters));
	}

	public static void throwBusinessException(final String messageSummary, final String messageCodeDetail,
			final String messageLog) throws TechnicalException {
		throwException(new BusinessException(messageSummary, messageCodeDetail, messageLog, null));
	}

	public static void throwBusinessException(final String messageSummary, final String messageCodeDetail,
			final String messageLog, final Map<ExceptionAdvancePatameterEnum, Object> advanceParameters) throws TechnicalException {
		throwException(new BusinessException(messageSummary, messageCodeDetail, messageLog, advanceParameters));
	}

	public static void throwDatabaseException(final String messageSummary, final String messageCodeDetail) throws TechnicalException {
		throwException(new DatabaseException(messageSummary, messageCodeDetail, null));
	}

	public static void throwDatabaseException(final String messageSummary, final String messageCodeDetail,
			final Map<ExceptionAdvancePatameterEnum, Object> advanceParameters) throws TechnicalException {
		throwException(new DatabaseException(messageSummary, messageCodeDetail, advanceParameters));
	}

	public static void throwDatabaseException(final String messageSummary, final String messageCodeDetail,
			final String messageLog) throws TechnicalException {
		throwException(new DatabaseException(messageSummary, messageCodeDetail, messageLog, null));
	}

	public static void throwDatabaseException(final String messageSummary, final String messageCodeDetail,
			final String messageLog, final Map<ExceptionAdvancePatameterEnum, Object> advanceParameters) throws TechnicalException {
		throwException(new DatabaseException(messageSummary, messageCodeDetail, messageLog, advanceParameters));
	}

	public static void throwSecurityException(final String messageSummary, final String messageCodeDetail) throws TechnicalException {
		throwException(new SecurityException(messageSummary, messageCodeDetail, null));
	}

	public static void throwSecurityException(final String messageSummary, final String messageCodeDetail,
			final Map<ExceptionAdvancePatameterEnum, Object> advanceParameters) throws TechnicalException {
		throwException(new SecurityException(messageSummary, messageCodeDetail, advanceParameters));
	}

	public static void throwSecurityException(final String messageSummary, final String messageCodeDetail,
			final String messageLog) throws TechnicalException {
		throwException(new SecurityException(messageSummary, messageCodeDetail, messageLog, null));
	}

	public static void throwSecurityException(final String messageSummary, final String messageCodeDetail,
			final String messageLog, final Map<ExceptionAdvancePatameterEnum, Object> advanceParameters) throws TechnicalException {
		throwException(new SecurityException(messageSummary, messageCodeDetail, messageLog, advanceParameters));
	}

	public static void throwException(final String messageSummary, final String messageCodeDetail) throws TechnicalException {
		throwException(new TechnicalException(messageSummary, messageCodeDetail, null));
	}

	public static void throwException(final String messageSummary, final String messageCodeDetail,
			final Map<ExceptionAdvancePatameterEnum, Object> advanceParameters) throws TechnicalException {
		throwException(new TechnicalException(messageSummary, messageCodeDetail, advanceParameters));
	}

	public static void throwException(final String messageSummary, final String messageCodeDetail,
			final String messageLog) throws TechnicalException {
		throwException(new TechnicalException(messageSummary, messageCodeDetail, messageLog, null));
	}

	public static void throwException(final String messageSummary, final String messageCodeDetail,
			final String messageLog, final Map<ExceptionAdvancePatameterEnum, Object> advanceParameters) throws TechnicalException {
		throwException(new TechnicalException(messageSummary, messageCodeDetail, messageLog, advanceParameters));
	}

	private static void throwException(TechnicalException technicalException) throws TechnicalException {
		String messageDetail = Messages
				.create(technicalException.getMessageCodeDetail(), technicalException.getMessageParameters()).get()
				.getDetail();
		technicalException.setMessageDetail(messageDetail);
		if (technicalException.getMessageLog() == null) {
			technicalException.setMessageLog(messageDetail);
		} else {
			technicalException.setMessageLog(
					Messages.create(technicalException.getMessageLog(), technicalException.getMessageLogParameters())
							.get().getDetail());
		}
		registerLog(technicalException);
		throw technicalException;
	}

	public static void throwFunctionalException(final String messageSummary, final String messageCodeDetail)
			throws FunctionalException {
		throwFunctionalException(messageSummary, messageCodeDetail, null);
	}

	public static void throwFunctionalException(final String messageSummary, final String messageCodeDetail,
			Map<ExceptionAdvancePatameterEnum, Object> advanceParameters) throws FunctionalException {
		throwFunctionalException(new FunctionalException(messageSummary, messageCodeDetail, advanceParameters));
	}

	public static void throwFunctionalException(FunctionalException functionalException) throws FunctionalException {
		String messageDetail = Messages
				.create(functionalException.getMessageCodeDetail(), functionalException.getMessageParameters()).get()
				.getDetail();
		functionalException.setMessageDetail(messageDetail);
		throw functionalException;
	}

	public static void handleException(TechnicalException e) {
		handleException(e, null);
	}

	public static void handleException(TechnicalException e, String clientId) {
		Message message = Messages.create(e.getMessageSummary()).detail(e.getMessageDetail(), e.getMessageParameters());
		createMessage(e.getSeverity(), message, clientId);
	}

	public static void handleException(FunctionalException e) {
		handleException(e, null);
	}

	public static void handleException(FunctionalException e, String clientId) {
		Message message = Messages.create(e.getMessageSummary()).detail(e.getMessageDetail(), e.getMessageParameters());
		createMessage(e.getSeverity(), message, clientId);
	}

	public static void handleException(Exception e) {
		handleException(e, null);
	}

	public static void handleException(Exception e, String clientId) {
		if (e instanceof FunctionalException) {
			FunctionalException funcEx = (FunctionalException) e;
			ExceptionMessageManager.handleException(funcEx);
		} else if (e instanceof TechnicalException) {
			TechnicalException techEx = (TechnicalException) e;
			ExceptionMessageManager.handleException(techEx);
		} else if (e.getCause() instanceof TechnicalException) {
			TechnicalException techEx = (TechnicalException) e.getCause();
			ExceptionMessageManager.handleException(techEx);
		} else if (e.getCause() instanceof FunctionalException) {
			FunctionalException funcEx = (FunctionalException) e.getCause();
			ExceptionMessageManager.handleException(funcEx);
		} else {
			Message message = Messages.create("").detail("", "");
			createMessage(ExceptionSeverityEnum.SEVERE, message, clientId);
		}
	}

	public static void wrapTechnicalException(Exception e, final String messageSummary,
			final String messageCodeDetail) throws TechnicalException {
		wrapTechnicalException(e, messageSummary, messageCodeDetail, null, null, false);
	}

	public static void wrapTechnicalException(Exception e, final String messageSummary, final String messageCodeDetail,
			boolean handle) throws TechnicalException {
		wrapTechnicalException(e, messageSummary, messageCodeDetail, null, null, handle);
	}

	public static void wrapTechnicalException(Exception e, final String messageSummary, final String messageCodeDetail,
			final Map<ExceptionAdvancePatameterEnum, Object> advanceParameters) throws TechnicalException {
		wrapTechnicalException(e, messageSummary, messageCodeDetail, null, advanceParameters, false);
	}

	public static void wrapTechnicalException(Exception e, final String messageSummary, final String messageCodeDetail,
			final Map<ExceptionAdvancePatameterEnum, Object> advanceParameters, boolean handle) throws TechnicalException {
		wrapTechnicalException(e, messageSummary, messageCodeDetail, null, advanceParameters, handle);
	}

	public static void wrapTechnicalException(Exception e, final String messageSummary, final String messageCodeDetail,
			final String messageLog) throws TechnicalException {
		wrapTechnicalException(e, messageSummary, messageCodeDetail, messageLog, null, false);
	}

	public static void wrapTechnicalException(Exception e, final String messageSummary, final String messageCodeDetail,
			final String messageLog, boolean handle) throws TechnicalException {
		wrapTechnicalException(e, messageSummary, messageCodeDetail, messageLog, null, handle);
	}

	public static void wrapTechnicalException(Exception e, final String messageSummary, final String messageCodeDetail,
			final String messageLog, Map<ExceptionAdvancePatameterEnum, Object> advanceParameters) throws TechnicalException {
		wrapTechnicalException(e, messageSummary, messageCodeDetail, messageLog, advanceParameters, false);
	}

	public static void wrapTechnicalException(Exception e, final String messageSummary, final String messageCodeDetail,
			final String messageLog, Map<ExceptionAdvancePatameterEnum, Object> advanceParameters, boolean handle) throws TechnicalException {

		try {
			if (e instanceof TechnicalException) {
				throw (TechnicalException) e;
			} else if (e instanceof FunctionalException) {
				ExceptionMessageManager.throwException(((FunctionalException) e).getMessageSummary(),
						((FunctionalException) e).getMessageCodeDetail(), advanceParameters);
			} else {
				if (advanceParameters == null) {
					advanceParameters = new HashMap<>();
				}
				advanceParameters.put(ExceptionAdvancePatameterEnum.PREVIOUS_EXCEPTION, e);
				ExceptionMessageManager.throwException(messageSummary, messageCodeDetail, messageLog,
						advanceParameters);
			}
		} catch (TechnicalException ex) {
			if (handle) {
				handleException(ex);
			} else {
				throw ex;
			}
		}

	}

	public static void wrapInvocationTargetException(InvocationTargetException e) throws FunctionalException {
		if (e.getTargetException() instanceof FunctionalException) {
			FunctionalException funcEx = (FunctionalException) e.getTargetException();
			throw funcEx;
		} else if (e.getTargetException() instanceof TechnicalException) {
			TechnicalException techEx = (TechnicalException) e.getTargetException();
			throw techEx;
		} else if (e.getTargetException() instanceof RuntimeException) {
			TechnicalException techEx = (TechnicalException) e.getTargetException().getCause();
			ExceptionMessageManager.throwFunctionalException("EtapaRequisitosError", techEx.getMessageDetail());
		}
		ExceptionMessageManager.throwFunctionalException("EtapaRequisitosError", e.getTargetException().getMessage());
	}

	public static void throwPredicateError(String login, Exception e, Object predicate) throws TechnicalException {
		Map<ExceptionAdvancePatameterEnum, Object> advanceParameters = new HashMap<>();
		advanceParameters.put(ExceptionAdvancePatameterEnum.PREVIOUS_EXCEPTION, e);
		advanceParameters.put(ExceptionAdvancePatameterEnum.MESSAGE_LOG_PARAMETERS,
				new Object[] {login, predicate, e.getMessage() });

		ExceptionMessageManager.throwSecurityException(SecurityExceptionMessageConstant.PREDICATE_ERROR_SUMMARY,
				SecurityExceptionMessageConstant.PREDICATE_ERROR_DETAIL,
				SecurityExceptionMessageConstant.PREDICATE_ERROR_LOG, advanceParameters);
	}

	public static void throwTypeConsultError(String userName, String perfilName, String restrictionValue) throws TechnicalException {
		Map<ExceptionAdvancePatameterEnum, Object> advanceParameters = new HashMap<>();
		advanceParameters.put(ExceptionAdvancePatameterEnum.MESSAGE_LOG_PARAMETERS,
				new Object[] { userName, perfilName, restrictionValue });

		ExceptionMessageManager.throwSecurityException(SecurityExceptionMessageConstant.CONSULT_TYPE_ERROR_SUMMARY,
				SecurityExceptionMessageConstant.CONSULT_TYPE_ERROR_DETAIL,
				SecurityExceptionMessageConstant.CONSULT_TYPE_ERROR_LOG, advanceParameters);
	}

	private static void createMessage(ExceptionSeverityEnum exceptionSeverityEnum, Message message, String clientId) {

		if (clientId != null) {
			Messages.add(clientId, message.get());

			switch (exceptionSeverityEnum) {

			case SEVERE:
				message.error();
				FacesContext.getCurrentInstance().validationFailed();
				break;

			case WARN:
				message.warn();
				break;

			default:
				break;
			}

		} else {
			switch (exceptionSeverityEnum) {

			case SEVERE:
				message.error().add();
				FacesContext.getCurrentInstance().validationFailed();
				break;

			case INFO:
				message.add();
				break;

			case WARN:
				message.warn().add();
				break;

			default:
				break;
			}
		}
	}

	/**
	 * Método encargado de registar los logs de una excepción de tipo técnico.
	 *
	 * @param technicalException
	 *            Excepción de tipo técnico que se registrará en los logs.
	 * @param advance
	 *            Especifica si el log generado será avanzado o simple. Si es un
	 *            true el log será avanzado de lo contrario será simple.
	 */
	private static void registerLog(final TechnicalException technicalException) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(technicalException.getErrorCode());
		stringBuffer.append(LOG_MESSAGER_ERROR);
		stringBuffer.append(technicalException.getMessageLog());

		if (technicalException.getClassName() != null && technicalException.getMethodName() != null) {
			stringBuffer.append(LOG_MESSAGER_ERROR2);
			stringBuffer.append(technicalException.getClassName());
			stringBuffer.append(LOG_MESSAGER_ERROR3);
			stringBuffer.append(technicalException.getMethodName());
		}

		if (technicalException.getAdvanceLog()) {
			stringBuffer.append(LOG_MESSAGER_ERROR4);
			stringBuffer.append(technicalException.getHostName());
			stringBuffer.append(LOG_MESSAGER_ERROR5);
			stringBuffer.append(technicalException.getUserName());
			stringBuffer.append(LOG_MESSAGER_ERROR6);
			stringBuffer.append(technicalException.getPreviousExeption());

		}

		switch (technicalException.getSeverity()) {

		case SEVERE:
			log.severe(stringBuffer.toString());
			break;

		case WARN:
			log.warning(stringBuffer.toString());
			break;

		default:
			log.info(stringBuffer.toString());
			break;
		}

	}

}
