package ua.miratech.rudenko.docstore.validators;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.miratech.rudenko.docstore.domain.NewArticle;

/**
 * Created by RomanR on 2/27/14.
 */
@Component
public class NewArticleValidator implements Validator {

    public static final Logger LOG = Logger.getLogger("rootLogger");

    @Override
    public boolean supports(Class<?> clazz) {
        return NewArticle.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        LOG.info("entered NewArticleValidator");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idArticleType",
                "required.type", "Field is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idLang",
                "required.language", "Field is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idSharedType",
                "required.idSharedType", "Field is required.");

    }
}
