package cl.duoc.dsy1103.pacientes.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/*
 * Anotación para validar el formato y el dígito verificador del RUN chileno.
 */
@Documented
@Constraint(validatedBy = RunValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRun {
    String message() default "El RUN ingresado no es válido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
