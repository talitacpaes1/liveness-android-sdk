# Guia de Implementação de View Customizada do Liveness 3D - Versão Beta



A Interface do Liveness 3D é altamente customizável e permite alterar cores, fontes, bordas, ícones, textos, fundos e botões. 

**São elementos customizáveis:** textos, cores, imagens, fontes, bordas e linhas.

**São telas customizáveis:** tela principal e de identificação, de orientações para usuários; tela de preparação, de progresso e de sucesso.

**Não são customizáveis**: propriedades de segurança e direitos autorais, desvios de layout significativos, dimensionamento das fontes.


## Elementos Customizáveis

**Botão de cancelamento:** Imagem e Localização.

**Todas as telas Liveness 3D e Tela de fundo:** Cores: borda, largura, raio, fundo; Sombra.

**Marca do cliente:** Logotipo de marca personalizada exibido na interface.

**Tela de execução do liveness:** Cores: borda, largura, raio, fundo, texto; Fonte do texto; Sombra.

**Barra de Texto:** Cores: borda, largura, raio, fundo, texto; Fonte de texto; Sombra.

**Bordas da captura da face:** Cor, largura, raios; Estilo de animação.

**Quadro de captura e identificação:** Cores:  borda, largura, raio, fundo.

**Botões de ação:** Cor da borda, largura, raio, fonte, fundo; Fonte de texto.

**INSERIR IMAGEM AQUI 

## Customizações gerais


**INSERIR IMAGEM AQUI 

**1 Botão Voltar**
- CancelButtonCustomization.customImage
- CancelButtonCustomization.location
- CancelButtonCustomization.customLocation

**2  Barra de texto e Frame Geral**
- FeedbackCustomization.backgroundColor
- FeedbackCustomization.cornerRadius
- FeedbackCustomization.shadow
- FeedbackCustomization.textColor
- FeedbackCustomization.textFont

**3  Borda da captura da face**
- OvalCustomization.strokeColor
- OvalCustomization.strokeWidth
- OvalCustomization.progressColor1
- OvalCustomization.progressColor2
- OvalCustomization.progressStrokeWidth
- OvalCustomization.progressRadialOffset

### Outras customizações

**Cores do Texto**
- GuidanceCustomization.foregroundColor

**Botões**
- GuidanceCustomization.buttonFont
- GuidanceCustomization.buttonBorderWidth
- GuidanceCustomization.buttonBorderColor
- GuidanceCustomization.buttonCornerRadius
- GuidanceCustomization.buttonTextNormalColor
- GuidanceCustomization.buttonTextHighlightColor
- GuidanceCustomization.buttonTextDisabledColor
- GuidanceCustomization.buttonBackgroundNormalColor
- GuidanceCustomization.buttonBackgroundHighlightColor
- GuidanceCustomization.buttonBackgroundDisabledColor

**Customização de orientações**
- GuidanceCustomization.readyScreenTextBackgroundColor
- GuidanceCustomization.readyScreenTextBackgroundCornerRadius

> As customizações de orientações são aplicáveis apenas para dispositivos muito pequenos e com espaço de tela reduzido.


### Outras customizações relevantes:

**Customize o estilo da appearing:**
- FaceTecEntryAnimationCustomization: EntryAnimationStyle

**Customize o estilo da disappearing:**
- FaceTecExitAnimationCustomization: ExitAnimationStyle


## Customizações da Tela de Execução do Liveness

**As customização da tela de execução do Liveness devem respeitar as propriedades customizáveis vistas no fluxo anterior.**


##  Customização da Tela de Nova Tentativa (Retry  Screen)

Nesta tela, são fornecidas orientações que guiam o usuário a realizar uma nova Sessão Liveness 3D. 
Apresenta boa iluminação e pose, permitindo que o usuário compreenda as correções necessárias.

**1 Orientação Principal**
- headerFont

**1.1 Orientação Principal - Adicionais**
- retryScreenHeaderFont
- retryScreenHeaderTextColor
- retryScreenHeaderAttributedString

**2 Orientação da Face**
- retryScreenIdealFaceTecImage
- retryScreenSlideshowImages
- retryScreenSlideshowInterval
- enableRetryScreenSlideshowShuffle
- retryScreenImageBorderColor
- retryScreenImageBorderWidth
- retryScreenImageCornerRadius
- retryScreenOvalStrokerColor

**2.1 Orientação Secundária - Adicionais**
- retryScreenSubtextFont
- retryScreenSubtextTextColor
- retryScreenSubtextAttributedString

**3 Orientações secundárias**
- subtextFont
