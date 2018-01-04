package sv.gob.mh.sitepcommon.util.enums;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityExceptionMessageConstant {

	/**
	 * Constante para archivo de excepciones de seguridad.
	 */
	
	
	public static final String PROCESS_POLICY_ERROR_SUMMARY = "processPoliciesErrorSummary";

	public static final String PROCESS_POLICY_ERROR_DETAIL = "processPoliciesErrorDetail";

	public static final String PROCESS_POLICY_ERROR_LOG = "processPoliciesErrorLog";
	
	public static final String CHANGE_PASSWORD_ERROR_SUMMARY = "changePasswordErrorSummary";

	public static final String CHANGE_PASSWORD_ERROR_DETAIL = "changePasswordErrorDetail";

	public static final String CHANGE_PASSWORD_ERROR_LOG = "changePasswordErrorLog";
	
	public static final String FAILED_ATTEMPTS_ERROR_SUMMARY = "failedAttemptsErrorSummary";

	public static final String FAILED_ATTEMPTS_ERROR_DETAIL = "failedAttemptsErrorDetail";
	
	public static final String PROFILE_ERROR_SUMMARY = "profileErrorSummary";

	public static final String PROFILE_ERROR_DETAIL = "profileErrorDetail";

	public static final String RESTRICTION_QUERY_TYPE_ERROR_SUMMARY = "restrictionQueryTypeErrorSummary";

	public static final String RESTRICTION_QUERY_TYPE_ERROR_DETAIL = "restrictionQueryTypeErrorDetail";

	public static final String RESTRICTION_QUERY_TYPE_ERROR_LOG = "restrictionQueryTypeErrorLog";

	public static final String UNDEFINED_INSTITUTION_SUMMARY = "undefinedInstitutionErrorSummary";

	public static final String UNDEFINED_INSTITUTION_DETAIL = "undefinedInstitutionErrorDetail";

	public static final String UNDEFINED_INSTITUTION_LOG = "undefinedInstitutionErrorLog";

	public static final String PREDICATE_ERROR_SUMMARY = "predicateErrorSummary";

	public static final String PREDICATE_ERROR_DETAIL = "predicateErrorDetail";

	public static final String PREDICATE_ERROR_LOG = "predicateErrorLog";
	
	public static final String RESTRICTION_QUERY_NOT_FOUND_ERROR_SUMMARY = "restrictionQueryNotFoundErrorSummary";

	public static final String RESTRICTION_QUERY_NOT_FOUND_ERROR_DETAIL = "restrictionQueryNotFoundErrorDetail";

	public static final String RESTRICTION_QUERY_NOT_FOUND_ERROR_LOG = "restrictionQueryNotFoundErrorLog";
	
	public static final String CONSULT_TYPE_ERROR_SUMMARY = "consultTypeErrorSummary";

	public static final String CONSULT_TYPE_ERROR_DETAIL = "consultTypeErrorDetail";

	public static final String CONSULT_TYPE_ERROR_LOG = "consultTypeErrorLog";
	
	public static final String LOGIN_MODULE_ERROR_SUMMARY = "LoginModuleErrorSummary";

	public static final String LOGIN_MODULE_ERROR_DETAIL = "LoginModuleErrorDetail";

	public static final String LOGIN_MODULE_ERROR_LOG = "LoginModuleErrorLog";

}
