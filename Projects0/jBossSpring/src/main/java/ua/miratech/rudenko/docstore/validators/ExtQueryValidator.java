package ua.miratech.rudenko.docstore.validators;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.miratech.rudenko.docstore.domain.NewArticle;
import ua.miratech.rudenko.docstore.textIndex.ExtQuery;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by RomanR on 2/28/14.
 */
@Component
public class ExtQueryValidator implements Validator {

    public static final Logger LOG = Logger.getLogger("rootLogger");
//    DateValidator dateValidator = new DateValidator();

    @Override
    public boolean supports(Class<?> clazz) {
        return NewArticle.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        LOG.info("entered ExtQueryValidator");

        ExtQuery extQuery = (ExtQuery) target;
        if (!validateDate(extQuery.getCreated())) {
            errors.rejectValue("created", "required.created");
        }
        if (!validateDate(extQuery.getCreated())) {
            errors.rejectValue("modified", "required.created");
        }
    }

    private boolean validateDate(String date){
        boolean b = false;
        DateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        try {
            sdf.parse(date);
            b = true;
        } catch (ParseException e) {
            LOG.info("I could not parse the date " + date);
        }
        return b;
    }
}
