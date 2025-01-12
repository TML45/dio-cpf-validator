package br.com.dio.cpfvalidatordio.validator;

public class CPFValidator {

    public static boolean isCPFValid(String cpf) {
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int primeiroDigitoVerificador = 11 - (soma % 11);
            if (primeiroDigitoVerificador >= 10) {
                primeiroDigitoVerificador = 0;
            }

            if (primeiroDigitoVerificador != Character.getNumericValue(cpf.charAt(9))) {
                return false;
            }

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            int segundoDigitoVerificador = 11 - (soma % 11);
            if (segundoDigitoVerificador >= 10) {
                segundoDigitoVerificador = 0;
            }

            return segundoDigitoVerificador == Character.getNumericValue(cpf.charAt(10));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String[] cpfs = {
                "123.456.789-09",
                "111.444.777-35",
                "000.000.000-00",
                "12345678909"
        };

        for (String cpf : cpfs) {
            System.out.println("CPF: " + cpf + " é válido? " + isCPFValid(cpf));
        }
    }
}

