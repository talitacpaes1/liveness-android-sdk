
# Liveness - Release Notes

#### 1.4.0

- Foi criado um novo enum, `FaceCaptchaErrorCode`, que indica o tipo de erro retornado pelo SDK. 
```kotlin
/**  
 * Erros que podem ser retornados pelo FaceCaptcha.
 */
enum class FaceCaptchaErrorCode {  
    // Parâmetros inválidos  
    INVALID_BUNDLE_PARAMS,  
    // App Key inválido.  
    INVALID_APP_KEY,  
    // Certiface offline.  
    CERTIFACE_OFF,  
    // Aparelho não possui câmera frontal  
    NO_FRONT_CAMERA,  
    // Não foi concedida permissão de acesso à câmera do aparelho.  
    NO_CAMERA_PERMISSION,  
    // Sem conexão à Internet.  
    NO_INTERNET_CONNECTION,  
    // Chamada telefônica em andamento. Não é possível iniciar o desafio durante uma chamada telefônica.  
    PHONE_CALL_IN_PROGRESS,  
    // Erro na requisição.  
    REQUEST_ERROR,  
    // App foi minimizado durante o uso do FaceCaptcha, isso faz com que o desafio seja encerrado.  
    CHALLENGE_INTERRUPTED,  
    // Memória do aparelho está baixa  
    LOW_MEMORY  
}
```
Este valor pode ser acessado através da chave `PARAM_RESULT_ERROR_CODE`, na callback `onActivityResult`, como no exemplo abaixo:
```kotlin
val errorCode = data?.getSerializableExtra(FaceCaptchaActivity.PARAM_RESULT_ERROR_CODE) as? FaceCaptchaErrorCode
```

- A chave `PARAM_RESULT_ERROR_APPKEY`, que indicava que a AppKey fornecida era inválida, ficou obsoleta nesta versão. Este erro agora pode ser verificado pelo código de erro `FaceCaptchaErrorCode.INVALID_APP_KEY `, através da chave `PARAM_RESULT_ERROR_CODE`.

## Guias de migração

- [1.4.0](Documentation/Migration-Guide-1.4.0.md)
