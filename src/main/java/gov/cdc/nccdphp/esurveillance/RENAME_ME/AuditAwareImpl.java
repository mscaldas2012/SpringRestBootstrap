package gov.cdc.nccdphp.esurveillance.RENAME_ME;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("<<CHANGE_ME>>");
	}

}
