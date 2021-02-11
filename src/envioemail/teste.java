/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envioemail;

/**
 *
 * @author alex.silva
 */
public class teste {
// https://www.google.com/settings/security/lesssecureapps Link do gmail para habilitar uso de aplicativo de terceiros

    public static void main(String[] args) {
        EnvioEmail aq = new EnvioEmail();
        String[] destinatarios = new String[10];
        destinatarios[0] = "alexsouzasilvax@gmail.com";
        destinatarios[1] = "alex.silva@jmm.org.br";
        destinatarios[2] = "guimaraes.silas@gmail.com";
        destinatarios[3] = "";

        //aq.enviaEmail("alexsouza8045@gmail.com", destinatarios[1], "Testando3", "Testando minha API de envio de email com java.", "gu@nabara964645673", "alexsouza8045@gmail.com");
        String remetente = "alexsouza8045@gmail.com";
        String assunto = "Testando Envio de Email com Java";
        String conteudo = "Testando envio de email com java.<br/>Segue em anexo o projeto dessa api.<br/><br/>Obrigado.<br/><br/><br/>Alex Souza da Silva";
        String senhaRemetente = "SENHA";

        aq.enviaEmail(remetente, destinatarios[1], assunto, conteudo, senhaRemetente, remetente);
        //aq.enviaEmail(remetente, destinatarios[2], assunto, conteudo, senhaRemetente, remetente);

    }
}
