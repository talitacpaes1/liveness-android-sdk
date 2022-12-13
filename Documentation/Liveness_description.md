# Liveness (Prova de vida do usuário)

Consiste em um conjunto de recursos técnicos para combater ataques de falsificação biométrica e determinar se a fonte da imagem está viva e presente no momento da captura ou se é uma reprodução. Por exemplo: foto de foto, foto de vídeo, deepfake etc.

Neste repositório você vai encontrar uma descrição das soluções **Liveness Ativo (Liveness 2D)** e **Liveness 3D**. Além de ficar por dentro dos retornos (Responses) de cada Liveness mencionados aqui.

## O que é Liveness Ativo (Liveness 2D)?
Esta solução permite a validação do usuário através de tecnologias para o reconhecimento e leitura de **movimentos da face**.

### Liveness Captcha
É um **SDK** que reúne um conjunto de propriedades de segurança, para agilizar a autenticação de pessoas através de **desafios (movimento da face)** em tempo real. Esses desafios servem para validar a qualidade das fotos garantindo a prova de vida do usuário no momento da operação.

### Como funciona?
É solicitado ao usuário uma interação em **tempo real** para executar os desafios. 
Após as capturas da face, são realizadas as análises e os tratamentos de cada uma para garantir que todos os desafios sejam cumpridos com sucesso.
Parâmetros para os **tipos de desafios** e **níveis de confiança**:

- **Tipos de desafios (movimentos da face)**: 
  Fique sério; Sorria; Vire para a direita; Vire para a esquerda; Olhe para a câmera (frontal); Incline a cabeça para a direita; Incline a cabeça para a esquerda; Olhe para cima; Olhe para baixo.
- Os **níveis de confiança** disponíveis refletem o nível de exigência medido para a execução dos desafios. 
  Exemplo: variação de grau da inclinação da face que pode ser de nível baixo, médio ou alto.

## O que é Liveness 3D?
Um SDK para **detecção da prova de vida** de usuários em três verificações: identificação de vivacidade, reconhecimento facial utilizando tecnologia 3D e a autenticação da identidade.

### O que significam essas verificações?
1. **Identificação de vivacidade**: validar se é uma pessoa "mesmo" e não um objeto ou foto de foto por exemplo.
2. **Reconhecimento facial**: depois de identificar um rosto de uma pessoa, é feito o processo de reconhecimento facial.
3. **Autenticação da identidade**: depois de coletar as evidências da pessoal é validado no backend se aquele rosto bate com o rosto de cadastro.

### Como funciona?
A solução utiliza algoritmos para detecção de distorções e alterações da face através de uma tecnologia 3D.
Após a captura, é gerado um mapa tridimensional que mede a profundidade da face, a textura da pele e o reflexo dos olhos. Em poucos segundos esse SDK é capaz de processar mais de 100 quadros de vídeo e realizar engenharia reversa de um mapa da face.

## Resultado
As soluções de Liveness mencionadas acima fornecem alguns retornos para o usuário. Entenda quais são eles:

### Resultado positivo
Acontece quando os desafios são concluídos com sucesso. Isso significa que existe um baixo risco de fraude.

### Resultado negativo
Ocorre quando há uma falha na autenticação facial. Esses resultados podem indicar fraude.
- falha na validação da autenticidade facial;
- erro na execução de interações do usuário;
- alcance do limite de tentativas de execução do liveness pelo usuário.

Os retornos são representados pelos seguintes códigos:

codID | Descrição |
| - | - |
| 1.1 | Cadastro com sucesso |
| 1.2 | Certificação positiva (Conhecido = True) |
| 200.1 | Cadastro com alertas  |
| 200.2 | Certificação negativa (Conhecido = True)  |
| 200.3 | Certificação positiva (Conhecido = False)  |
| 200.4 | Certificação negativa (Conhecido = False)  |
| 300.1 | Prova de vida inválida |
| 300.2 | Usuário bloqueado |


