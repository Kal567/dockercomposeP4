package com.pucmm.compose.services;

import com.pucmm.compose.entities.Encuesta;
import com.pucmm.compose.repositories.EncuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EncuestaServices {


    @Autowired
    EncuestaRepository encuestaRepository;

    //CREATE AND UPDATE ENCUESTA
    public void createOrUpdateEncuesta(Encuesta encuesta){
        encuestaRepository.save(encuesta);
    }

    //GET ALL ENCUESTAS
    public List<Encuesta> allEncuestas(){
        List<Encuesta> encuestaList = new ArrayList<Encuesta>();
        encuestaRepository.findAll().forEach(encuesta -> encuestaList.add(encuesta));
        return encuestaList;
    }

    //GET ONE SPECIFIC ENCUESTA BY ID
    public Encuesta getEncuesta(int idEncuesta){
        for (Encuesta user : allEncuestas()){
            if(user.getIdEncuesta() == idEncuesta){
                return user;
            }
        }
        return null;
    }

    //GET ALL COMMENTS
    public List<String> getComments(){
        List<String> comentarios = null;
        for (Encuesta encuesta : allEncuestas()){
            comentarios.add(encuesta.getQuestionFour());
        }
        return comentarios;
    }

    //DELETE ENCUESTA
    public boolean deleteEncuesta(int idEncuesta){
        for (Encuesta encuesta : allEncuestas()){
            if(encuesta.getIdEncuesta() == idEncuesta){
                encuestaRepository.delete(encuesta);
                return true;
            }
        }
        return false;
    }

}
