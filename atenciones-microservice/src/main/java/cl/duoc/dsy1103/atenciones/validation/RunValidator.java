package cl.duoc.dsy1103.atenciones.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/*
* Valida el formato y el dígito verificador del RUN chileno.
* El RUN debe tener el formato "12345678-9" o "12345678-K".
 */
public class RunValidator implements ConstraintValidator<ValidRun, String> {
    private static final String RUN_PATTERN = "^\\d{6,8}-[\\dKk]$";

    @Override
    public boolean isValid(String run, ConstraintValidatorContext context) {
        return validarRut(run);
    }

    /**
     * Valida el formato y el dígito verificador del RUN.
     *
     * @param rut El RUN a validar.
     * @return true si el RUN es válido, false en caso contrario.
     */
    private boolean validarRut(String rut) {
        if (rut == null || rut.isBlank())
            return false;
        if (!rut.matches(RUN_PATTERN))
            return false;

        String[] partes = rut.split("-");
        int numero = Integer.parseInt(partes[0]);
        String dvIngresado = partes[1].toUpperCase();
        String dvCalculado = calcularDv(numero);

        return dvIngresado.equals(dvCalculado);
    }

    /**
     * Calcula el dígito verificador del RUN.
     *
     * @param numero El número del RUN.
     * @return El dígito verificador calculado.
     */
    private String calcularDv(int numero) {
        int suma = 0;
        int multiplicador = 2;

        while (numero != 0) {
            suma += (numero % 10) * multiplicador;
            numero /= 10;
            multiplicador = multiplicador == 7 ? 2 : multiplicador + 1;
        }

        int resto = 11 - (suma % 11);

        return switch (resto) {
            case 11 -> "0";
            case 10 -> "K";
            default -> String.valueOf(resto);
        };
    }
}
