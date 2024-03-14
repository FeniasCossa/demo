package mz.sga.ujc.demo.service.auth;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public String EmailText(String codigo){
        return "<div id=\"wrapper\" style=\" display: -webkit-flex; display: -ms-flex; display: flex;\n" +
                "min-height: 100vh;\">\n" +
                "<div id=\"main\" style=\" -moz-flex-grow: 1; -webkit-flex-grow: 1; -ms-flex-grow: 1; flex-grow: 1;\n" +
                "-moz-flex-shrink: 1; -webkit-flex-shrink: 1; -ms-flex-shrink: 1; flex-shrink: 1; width: 100%;\">\n" +
                "<div class=\"inner\" style=\" padding: 0 6em 0.1em 6em ; margin: 0 auto; max-width: 110em;\">\n" +
                "<header style=\" padding: 6em 0 1em 0; display: flex; flex-direction: row; justify-content: space-between;\">\n" +
                "<a href=\"localhost:8080/\" style=\"   border-bottom: 0; color: inherit; font-family: 'Roboto Slab', serif; font-size: 1.125em; \" class=\"logo\" >\n" +
                "<strong>Universidade Joaquim Chissano</strong></a>\n" +
                "</header>\n" +
                "<section id=\"banner\">\n" +
                "<div class=\"content\">\n" +
                "<header>\n" +
                "<h3 style=\"text-align: center;\">A sua conta foi criada com sucesso</h3>\n" +
                "</header>\n" +
                "<p>O seu codigo de candidato é: <strong>"+codigo+"</strong></p>\n" +
                "<p>Use o codigo para fazer login, inserindo o codigo e a senha por si definida.</p>\n" +
                "</div>\n" +
                "</section>\n" +
                "<h2>Contactos</h2>\n" +
                "<ul class=\"contact\">\n" +
                "<li class=\"icon solid fa-envelope\"><a href=\"secretaria.geral@ujc.ac.mz\">secretaria.geral@ujc.ac.mz</a></li>\n" +
                "<li class=\"icon solid fa-phone\">(258) 823032129</li>\n" +
                "<li class=\"icon solid fa-home\">Campus do Zimpeto, Rua do Grande Maputo, Q.88, Bairro de Zimpeto, Cidade de Maputo</li>\n" +
                "</ul>\n" +
                "<ul class=\"icons\" style=\"text-align: right; list-style: none; display: flex; justify-content: space-between;\">\n" +
                "<li style=\"padding-right: 10px;\"><a target=\"_parent\" href=\"https://www.facebook.com/ujc.ac.mz\" class=\"icon brands fa-facebook-f\"><span class=\"label\">Facebook</span></a></li>\n" +
                "<li><a href=\"https://www.instagram.com/universidadejoaquimchissano/?igsh=MngwOHluYnFlaWh1\"class=\"icon brands fa-instagram\"><span class=\"label\">Instagram</span></a></li>\n" +
                "</ul>\n" +
                "<footer id=\"footer\">\n" +
                "<p class=\"copyright\" style=\"text-align: center;\"> &copy; UJC. Todos direitos reservados.</p>\n" +
                "</footer>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>";
    }
}
