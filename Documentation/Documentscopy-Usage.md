# Documentoscopia

Guia de uso e integração da Documentoscopia para Android.

### Iniciando a Documentoscopia

1. Instancie um Intent para a classe `DocumentscopyActivity`, com os seguintes extras:
- *DocumentscopyActivity.PARAM_ENDPOINT*: URL apontando para o ambiente desejado.
- *DocumentscopyActivity.PARAM_APP_KEY*: App Key (recebida previamente).
- *DocumentscopyActivity.PARAM_DEBUG_ON (opcional)*: booleano para ajudar a depurar. Faz com que mensagens de log sejam exibidas na tela através de `Toasts`.
```kotlin
val intent = Intent(this, DocumentscopyActivity::class.java).apply {
    putExtra(DocumentscopyActivity.PARAM_ENDPOINT, ENDPOINT)
    putExtra(DocumentscopyActivity.PARAM_APP_KEY, APP_KEY)
    putExtra(FaceCaptchaActivity.PARAM_DEBUG_ON, false) // Passar true para mostrar logs na tela
}
```

2. Chame `startActivityForResult`:
```kotlin
startActivityForResult(intent, DOCUMENTSCOPY_RESULT_REQUEST)
```

### Customização

Além de poder usar o SDK em sua forma padrão de exibição, é possível customizar o seu visual.

Detalhes de como implementar a view customizada são encontrados [neste link](Documentscopy-CustomView.md).

### Tratando o retorno

1. Para receber o resultado da Documentoscopia, implemente o método `onActivityResult`. No caso de envio de documento concluído, o resultCode será `RESULT_OK`, e no caso de algum erro, o resultCode será `RESULT_CANCELED`.
```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == DOCUMENTSCOPY_RESULT_REQUEST) {
        when (resultCode) {
            Activity.RESULT_OK -> onDocumentscopyResultSuccess()
            Activity.RESULT_CANCELED -> onDocumentscopyCancelled(data)
        }
    }
}
```

2. Para tratar o caso de erro, os seguintes parâmetros podem ser avaliados:
- *DocumentscopyActivity.PARAM_RESULT_ERROR*: String que contém uma mensagem explicativa sobre o erro.
- *DocumentscopyActivity.PARAM_RESULT_ERROR_CODE*: Enum do tipo `DocumentscopyErrorCode`, que indica qual erro ocorreu.
```kotlin
private fun onDocumentscopyCancelled(data: Intent?) {
    val errorMessage = data?.getStringExtra(DocumentscopyActivity.PARAM_RESULT_ERROR)
    val errorCode = data?.getSerializableExtra(DocumentscopyActivity.PARAM_RESULT_ERROR_CODE) as? DocumentscopyActivity
    // Handle error…
}
```

`DocumentscopyErrorCode` pode assumir os seguintes valores:
```kotlin
enum class DocumentscopyErrorCode {
    // Parâmetros inválidos
    INVALID_BUNDLE_PARAMS,
    // App Key inválido.
    INVALID_APP_KEY,
    // Certiface offline.
    CERTIFACE_OFF,
    // Aparelho não possui câmera traseira
    NO_BACK_CAMERA,
    // Não foi concedida permissão de acesso à câmera do aparelho.
    NO_CAMERA_PERMISSION,
    // Sem conexão à Internet.
    NO_INTERNET_CONNECTION,
    // Erro na requisição.
    REQUEST_ERROR,
    // Memória do aparelho está baixa
    LOW_MEMORY,
    // Erro ao configurar câmera
    ERROR_CAMERA_SETUP,
    // Erro ao capturar foto
    ERROR_CAPTURE_PICTURE
    // XML fornecido para a view customizada do home fragment é inválido
    INVALID_CUSTOM_HOME_FRAGMENT,
    // XML fornecido para a view customizada do camera fragment é inválido
    INVALID_CUSTOM_CAMERA_FRAGMENT,
    // XML fornecido para a view customizada do confirmation fragment é inválido
    INVALID_CUSTOM_CONFIRMATION_FRAGMENT
}
```
