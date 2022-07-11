package br.com.ifgoiano.cdsquimica.model;

public class Error {
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String showErrorAuth(String errorCode) {

        switch (errorCode) {

            case "ERROR_INVALID_CUSTOM_TOKEN":
                errorCode = "O formato de token personalizado está incorreto. Por favor, verifique a documentação.";
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                errorCode = "O token personalizado corresponde a um público diferente.";
                break;

            case "ERROR_INVALID_CREDENTIAL":
                errorCode = "A credencial de autenticação fornecida está malformada ou expirou.";
                break;

            case "ERROR_INVALID_EMAIL":
                errorCode = "Email digitado está mal formatado";

                break;

            case "ERROR_WRONG_PASSWORD":
                errorCode = "A senha é inválida ou o usuário não possui uma senha.";
                break;

            case "ERROR_USER_MISMATCH":
                errorCode = "As credenciais fornecidas não correspondem ao usuário conectado anteriormente.";
                break;

            case "ERROR_REQUIRES_RECENT_LOGIN":
                errorCode = "Esta operação é sensível e requer autenticação recente. Faça login novamente antes de tentar novamente esta solicitação.";
                break;

            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                errorCode = "Já existe uma conta com o mesmo endereço de e-mail, mas com credenciais de login diferentes. Faça login usando um provedor associado a este endereço de e-mail";
                break;

            case "ERROR_EMAIL_ALREADY_IN_USE":
                errorCode = "O endereço de e-mail já está sendo usado por outra conta.";
                break;

            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                errorCode = "Esta credencial já está associada a uma conta de usuário diferente.";
                break;

            case "ERROR_USER_DISABLED":
                errorCode = "The user account has been disabled by an administrator.";
                break;

            case "ERROR_USER_TOKEN_EXPIRED":
                errorCode = "A conta de usuário foi desativada por um administrador.";
                break;

            case "ERROR_USER_NOT_FOUND":
                errorCode = "Não há registro de usuário correspondente a este identificador. O usuário pode ter sido excluído.";
                break;

            case "ERROR_INVALID_USER_TOKEN":
                errorCode = "A credencial do usuário não é mais válida. O usuário deve fazer login novamente.";
                break;

            case "ERROR_OPERATION_NOT_ALLOWED":
                errorCode = "Esta operação não é permitida. Você deve habilitar este serviço no console.";
                break;

            case "ERROR_WEAK_PASSWORD":
                errorCode = "A senha fornecida é inválida.";
                break;

        }
        setError(errorCode);
        return getError();
    }
}
