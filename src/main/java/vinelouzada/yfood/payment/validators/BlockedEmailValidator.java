package vinelouzada.yfood.payment.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import vinelouzada.yfood.payment.PaymentType;
import vinelouzada.yfood.user.User;

import java.util.Set;

@Component
public class BlockedEmailValidator implements PaymentValidator{

    private final static Set<String> BLOCKED_DOMAINS = Set.of("email.com");

    @Override
    public boolean isValid(PaymentType type, User user) {
        if (type.isModePayOffline()) return true;

        return !isBlockedEmailDomain(user.getEmail());
    }

    private boolean isBlockedEmailDomain(String email) {
        String domain = StringUtils.substringAfter(email, "@");
        return BLOCKED_DOMAINS.contains(domain);
    }
}