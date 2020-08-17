package com.pucmm.compose.controllers;

import com.pucmm.compose.entities.Encuesta;
import com.pucmm.compose.entities.Usuario;
import com.pucmm.compose.services.EncuestaServices;
import com.pucmm.compose.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class MainController {


    @Autowired
    UsuarioServices usuarioServices;

    @Autowired
    EncuestaServices encuestaServices;

    @GetMapping("/")
    public String home(){

        return "login";
    }

    @PostMapping("/login")
    public String setCookie(HttpServletResponse response, @RequestParam String username,
                            @RequestParam String password, HttpServletRequest request){

        Usuario usuario = usuarioServices.isRegistered(username, password);
        String template = null;
        if(usuario != null){
            Cookie cookie = new Cookie("idUsuario", "" + usuario.getIdUsuario());
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            response.addCookie(cookie);

            Cookie cookie2 = new Cookie("username", "" + usuario.getUsername());
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            response.addCookie(cookie2);

            if(usuario.isAdmin()){
                template = "showGraphs";
            } else {
                template = "showEncuestas";
            }

        } else {
            template = "login";
        }

        return template;

    }

    @GetMapping("/logout")
    public String killCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("idUsuario", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        Cookie cookie2 = new Cookie("username", null);
        cookie2.setMaxAge(0);
        response.addCookie(cookie2);

        return "login";
    }

    @PostMapping("/saveencuesta")
    public String saveencuesta(@RequestParam int questionOne, @RequestParam int questionTwo,
                               @RequestParam int questionThree, @RequestParam String questionFour){

        Encuesta encuesta = new Encuesta(questionOne, questionTwo, questionThree, questionFour);
        encuestaServices.createOrUpdateEncuesta(encuesta);
        return "redirect:/";
    }

    @GetMapping("/showGraficos")
    public String historialCliente(Model model){
        List<Encuesta> questions =  encuestaServices.allEncuestas();
        //---------------->  Q1:

        int qOne1 = 0;
        int qOne2 = 0;
        int qOne3 = 0;
        int qOne4 = 0;
        int qOne5 = 0;
        for (Encuesta encuesta: questions) {
            if(encuesta.getQuestionOne() == 1){
                qOne1 += 1;
            }
            else if(encuesta.getQuestionOne() == 2){
                qOne2 += 1;
            }
            else if(encuesta.getQuestionOne() == 3){
                qOne3 += 1;
            }
            else if(encuesta.getQuestionOne() == 4){
                qOne4 += 1;
            } else {
                qOne5 += 1;
            }
        }
        Map<String, Integer> graphDataQ1 = new TreeMap<>();
        graphDataQ1.put("1", qOne1);
        graphDataQ1.put("2", qOne2);
        graphDataQ1.put("3", qOne3);
        graphDataQ1.put("4", qOne4);
        graphDataQ1.put("5", qOne5);

        model.addAttribute("questionOne", graphDataQ1);
/*
        //---------------->  Q2:
        int qTwo1 = 0;
        int qTwo2 = 0;
        int qTwo3 = 0;
        int qTwo4 = 0;
        int qTwo5 = 0;
        for (Encuesta encuesta: questions) {
            if(encuesta.getQuestionTwo() == 1){
                qTwo1 += 1;
            }
            else if(encuesta.getQuestionTwo() == 2){
                qTwo2 += 1;
            }
            else if(encuesta.getQuestionTwo() == 3){
                qTwo3 += 1;
            }
            else if(encuesta.getQuestionTwo() == 4){
                qTwo4 += 1;
            } else {
                qTwo5 += 1;
            }
        }
        Map<String, Integer> graphDataQ2 = new TreeMap<>();
        graphDataQ1.put("1", qTwo1);
        graphDataQ1.put("2", qTwo2);
        graphDataQ1.put("3", qTwo3);
        graphDataQ1.put("4", qTwo4);
        graphDataQ1.put("5", qTwo5);

        model.addAttribute("questionTwo", graphDataQ2);

        //---------------->  Q4:
        int qThree1 = 0;
        int qThree2 = 0;
        int qThree3 = 0;
        int qThree4 = 0;
        int qThree5 = 0;
        for (Encuesta encuesta: questions) {
            if(encuesta.getQuestionThree() == 1){
                qThree1 += 1;
            }
            else if(encuesta.getQuestionThree() == 2){
                qThree2 += 1;
            }
            else if(encuesta.getQuestionThree() == 3){
                qThree3 += 1;
            }
            else if(encuesta.getQuestionThree() == 4){
                qThree4 += 1;
            } else {
                qThree5 += 1;
            }
        }
        Map<String, Integer> graphDataQ3 = new TreeMap<>();
        graphDataQ1.put("1", qThree1);
        graphDataQ1.put("2", qThree2);
        graphDataQ1.put("3", qThree3);
        graphDataQ1.put("4", qThree4);
        graphDataQ1.put("5", qThree5);

        model.addAttribute("questionThree", graphDataQ3);

        //---------------->  Q4:
        List<String> comentarios = encuestaServices.getComments();
        model.addAttribute("questionFour", comentarios);
*/
        return "showGraphs";
    }




}
