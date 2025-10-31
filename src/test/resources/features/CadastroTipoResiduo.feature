#language: pt

  @regressivo
  Funcionalidade: Cadastro de novo tipo de residuo
    Como usuário da API
    Quero cadastrar um novo tipo de resíduo
    Para que o registro seja salvo corretamente no sistema

    Cenário: Cadastro bem-sucedido de tipo de resíduo
      Dado que eu tenha os seguintes dados do tipo de resíduo:
        | campo     | valor                          |
        | nome      | Metal                          |
        | descricao | Resíduos Metalicos             |
      Quando  eu enviar a requisição para o endpoint "/post" de cadastrar tipo de resíduo
      Então o status code da resposta de tipo de resíduo deve ser 201
      E o arquivo de contrato esperado é o "Cadastro de tipo de residuo bem sucedido" de tipo de resíduo
      Então a resposta da requisição deve estar em conformidade com o contrato de tipo de resíduo