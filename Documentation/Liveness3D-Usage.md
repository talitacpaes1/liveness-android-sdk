#  Guia de Uso e Integração do Liveness 3D

###  Iniciando o Liveness 3D

**PASSO 1.** Crie um objeto do tipo `Liveness3DUser`, passando uma appKey (recebida previamente). Deve diferir para cada vez que for apresentar o Liveness.

```kotlin
data class Liveness3DUser(
  val appKey: String,
  val liveness3DTheme: Liveness3DTheme?
)
```

Detalhes de como customizar o Liveness3DTheme são encontrados [neste link](Liveness3D-Liveness3DTheme.md).

**PASSO 2.**  Instancie um Intent para a classe `Liveness3DActivity`, com os seguintes extras:

- *Liveness3DActivity.PARAM_ENDPOINT*: URL apontando para o ambiente desejado.
- *Liveness3DActivity.PARAM_LIVENESS3D_USER*: objeto do tipo `Liveness3DUser`, criado no passo anterior.
- *Liveness3DActivity.PARAM_DEBUG_ON (opcional)*: booleano para ajudar a depurar. Faz com que mensagens de log sejam exibidas na tela através de `Toasts`.

```kotlin
val intent = Intent(this, Liveness3DActivity::class.java).apply {
    putExtra(Liveness3DActivity.PARAM_ENDPOINT, ENDPOINT)
    putExtra(Liveness3DActivity.PARAM_LIVENESS3D_USER, liveness3DUser)
    putExtra(Liveness3DActivity.PARAM_DEBUG_ON, false) // Passar true para mostrar logs na tela
}
```

**PASSO 3.** Chame `startActivityForResult`:

```kotlin
startActivityForResult(intent, 3D_RESULT_REQUEST)
```

###  Customização

Além de poder usar o SDK em sua forma padrão de exibição, são fornecidas duas formas de customização:

####  Componentes e propriedades customizáveis 

**1. Customização de tela de desafios:**

```kotlin
val intent = Intent(this, Liveness3DActivity::class.java).apply {
    putExtra(Liveness3DActivity.PARAM_ENDPOINT, ENDPOINT)
    putExtra(Liveness3DActivity.PARAM_LIVENESS3D_USER, liveness3DUser)
    putExtra(Liveness3DActivity.PARAM_DEBUG_ON, false) // Passar true para mostrar logs na tela
}
```


**2. Customização completa:**

```kotlin
val intent = Intent(this, Liveness3DActivity::class.java).apply {
    putExtra(Liveness3DActivity.PARAM_ENDPOINT, ENDPOINT)
    putExtra(Liveness3DActivity.PARAM_LIVENESS3D_USER, liveness3DUser)
    putExtra(Liveness3DActivity.PARAM_DEBUG_ON, false) // Passar true para mostrar logs na tela
    putExtra(Liveness3DActivity.PARAM_CUSTOM_INSTRUCTION_SCREEN, R.layout.fragment_custom)
    putExtra(Liveness3DActivity.PARAM_CUSTOM_PERMISSION_SCREEN, R.layout.fragment_custom)
}
```

Os dois últimos parâmetros *(PARAM_CUSTOM_INSTRUCTION_SCREEN; PARAM_CUSTOM_PERMISSION_SCREEN)* permitem o envio de tela customizáveis, porém exigem os componentes obrigatórios abaixo:

|Tela|Componentes|id|Descrição|
|------|--|-----------|---------|
|Instruction_Screen|ImageButton|backButton|Botão de retornar à tela anterior.|
|Instruction_Screen|Button|continueButton|Avançar para a próxima etapa.|
|Permission_Screen|ImageButton|backButton|Botão de retornar à tela anterior.|
|Permission_Screen|Button|continueButton|Solicitar permissões de câmera ao usuário.|


Detalhes de como customizar os elementos dos desafios são encontrados [neste link](Liveness3D-CustomView.md).



###  Tratando o retorno

**Receber Resultado Liveness** 

Para receber o resultado do Liveness, implemente o método `onActivityResult`. No caso de desafio concluído, o resultCode será `RESULT_OK`, e no caso de algum erro, o resultCode será `RESULT_CANCELED`.

```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == 3D_RESULT_REQUEST) {
        when (resultCode) {
            Activity.RESULT_OK -> onLiveness3DResultSuccess(data)
            Activity.RESULT_CANCELED -> onLiveness3DResultCancelled(data)
        }
    }
}
```


>⚠️ Para mais detalhes dos tipos de retorno, [clique aqui](https://certifaceid.readme.io/docs/integra%C3%A7%C3%A3o-atualizada#42-3d-liveness).



**Tratar Desafio Concluído**

Para tratar o caso de desafio concluído, três parâmetros podem ser avaliados:

- *Liveness3DActivity.PARAM_RESULT*: Booleano que indica se a prova de vida foi válida ou não.
- *Liveness3DActivity.PARAM_RESULT_CAUSE*: String que indica a causa da falha.
- *Liveness3DActivity.PARAM_RESULT_COD_ID*: Double com o código de retorno.


```kotlin
private fun onLiveness3DResultSuccess(data: Intent?) {
    val result = data?.getBooleanExtra(Liveness3DActivity.PARAM_RESULT, false)
    val cause = data?.getStringExtra(Liveness3DActivity.PARAM_RESULT_CAUSE)
    val codID = data?.getDoubleExtra(Liveness3DActivity.PARAM_RESULT_COD_ID, 0.0)
    // Handle returned values…
}
```



**Tratar Caso de Erro** 

Para tratar o caso de erro, os seguintes parâmetros podem ser avaliados:

- *Liveness3DActivity.PARAM_RESULT_ERROR*: String que contém uma mensagem explicativa sobre o erro.
- *Liveness3DActivity.PARAM_RESULT_ERROR_CODE*: Enum do tipo `Liveness3DErrorCode`, que indica qual erro ocorreu.

```kotlin
private fun onLiveness3DResultCancelled(data: Intent?) {
    val errorMessage = data?.getStringExtra(Liveness3DActivity.PARAM_RESULT_ERROR)
    val errorCode = data?.getSerializableExtra(Liveness3DActivity.PARAM_RESULT_ERROR_CODE) as? Liveness3DErrorCode
// Handle error…
}
```

`Liveness3DErrorCode` pode assumir os seguintes valores:

```kotlin
enum class Liveness3DErrorCode {

// Parâmetros inválidos
   INVALID_BUNDLE_PARAMS,

// App Key inválido.
   INVALID_APP_KEY,

// Aparelho não possui câmera frontal
   NO_FRONT_CAMERA,

// Não foi concedida permissão de acesso à câmera do aparelho.
   NO_CAMERA_PERMISSION,

// Sem conexão à Internet.
   NO_INTERNET_CONNECTION,

// Erro na requisição.
   REQUEST_ERROR,

// XML fornecido para a view customizada é inválido
   INVALID_CUSTOM_FRAGMENT_INSTRUCTION_SCREEN
   INVALID_CUSTOM_FRAGMENT_PERMISSION_SCREEN
}
```
