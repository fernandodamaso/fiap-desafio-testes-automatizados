package org.example.projetoesgfiap.steps;

import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.example.projetoesgfiap.dto.PontoColetaRequestDTO;
import org.example.projetoesgfiap.service.CadastroPontoColetaService;

import java.util.Set;


public class CadastroPontoColetaSteps {

    private final CadastroPontoColetaService cadastroPontoColetaService = new CadastroPontoColetaService();

    private  PontoColetaRequestDTO pontoColetaRequestDTO;
    private Response response;

    @Dado("que eu tenha os seguintes dados do ponto de coleta:")
    public void queEuTenhaOsSeguintesDadosDoPontoDeColeta(io.cucumber.datatable.DataTable dataTable) {
        java.util.Map<String, String> dados = dataTable.asMap(String.class, String.class);
        pontoColetaRequestDTO = new PontoColetaRequestDTO(
                dados.get("nome"),
                dados.get("endereco"),
                dados.get("horarioFuncionamento"),
                Set.of(Long.parseLong(dados.get("tiposResiduosIds")))
        );
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastrar ponto de coleta")
    public void euEnviarARequisicaoParaOEndpointDeCadastrarPontoDeColeta(String endpoint) {
        response = cadastroPontoColetaService.cadastrarPontoColeta(pontoColetaRequestDTO, endpoint);
    }

    @Entao("o status code da resposta de ponto de coleta deve ser {int}")
    public void oStatusCodeDaRespostaDePontoDeColetaDeveSer(int statusCode){
        org.junit.Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @E("o arquivo de contrato esperado é o {string} de ponto de coleta")
    public void oCorpoDaRespostaDeveSeguirOSchemaDePontoDeColeta(String schemaPath) throws java.io.IOException, org.json.JSONException {
        cadastroPontoColetaService.setContract(schemaPath);
    }

    @Entao("a resposta da requisição deve estar em conformidade com o contrato de ponto de coleta")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionadoDePontoDeColeta() throws java.io.IOException, org.json.JSONException {
        java.util.Set<com.networknt.schema.ValidationMessage> validateResponse = cadastroPontoColetaService.validateResponseAgainstSchema(response);
        org.junit.Assert.assertTrue("O contrato está inválido!. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @After
    public void limparDados() {
        if (response != null && response.getStatusCode() == 201) {
            Long id = response.jsonPath().getLong("id");
            cadastroPontoColetaService.excluirPontoColeta(id);
        }
    }
}
