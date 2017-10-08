package io.rr.mytube0.validation;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author roman on 9/10/16.
 */
public class GenericValidator<T> {
    private final List<Predicate<T>> validators = new LinkedList<>();

    public GenericValidator(List<Predicate<T>> validators) {
        this.validators.addAll(validators);
    }

    public boolean isValid(T toValidate) {
        return validators.parallelStream().allMatch(predicate -> predicate.test(toValidate));
    }

    public boolean inNotValid(T toValidate) {
        return !(isValid(toValidate));
    }
}
