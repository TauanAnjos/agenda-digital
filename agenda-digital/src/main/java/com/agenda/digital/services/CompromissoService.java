package com.agenda.digital.services;

import com.agenda.digital.models.CategoriaModel;
import com.agenda.digital.models.CompromissoModel;
import com.agenda.digital.models.UserModel;
import com.agenda.digital.repositories.CategoriaRepository;
import com.agenda.digital.repositories.CompromissoRepository;
import com.agenda.digital.repositories.UserRepository;
import com.agenda.digital.rest.dtos.CompromissoDtoRequest;
import com.agenda.digital.rest.dtos.CompromissoDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompromissoService {

    @Autowired
    private CompromissoRepository compromissoRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public CompromissoModel criarCompromisso(Long userId, CompromissoDtoRequest compromissoDtoRequest){
        try {
            CategoriaModel categoriaModel = categoriaRepository.findById(compromissoDtoRequest.categoriaId()).orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
            UserModel userExistente = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
            CompromissoModel compromissoModel = new CompromissoModel(null,compromissoDtoRequest.titulo(),compromissoDtoRequest.descricao(),compromissoDtoRequest.data_hora(),userExistente,categoriaModel,compromissoDtoRequest.status());
            return compromissoRepository.save(compromissoModel);
        }catch (RuntimeException e){
            throw new RuntimeException("Erro ao criar compromisso"+ e.getMessage(),e);
        }
    }
    @Transactional(readOnly = true)
    public List<CompromissoDtoResponse> listaDeCompromissos(Long userId){
        List<CompromissoModel> compromissos = compromissoRepository.findAllByUserId(userId);
        return compromissos.stream().map(CompromissoModel::toDtoResponse).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public CompromissoDtoResponse BuscarCompromissoPorId(Long userId, Long compromissoId ){
        try {
            CompromissoModel compromissoModel = compromissoRepository.findById(compromissoId).orElseThrow(() -> new RuntimeException("Compromisso não encontrado."));
            if(!compromissoModel.getUsuario().getId().equals(userId)){
                throw new RuntimeException("Esse compromisso não pertence ao usuário.");
            }
            return compromissoModel.toDtoResponse();
        }catch (RuntimeException e){
            throw new RuntimeException("Erro ao buscar compromisso:"+ e.getMessage(), e);
        }
    }
    @Transactional
    public CompromissoDtoResponse atualizarCompromisso(Long userId, Long compromisso_id, CompromissoDtoRequest compromissoDtoRequest){
        CompromissoModel compromissoModel = compromissoRepository.findById(compromisso_id).orElseThrow(() -> new RuntimeException("Compromisso não encontrado."));
        if(!compromissoModel.getUsuario().getId().equals(userId)){
            throw new RuntimeException("Esse compromisso não pertence ao usário.");
        }
        CategoriaModel categoriaModel = categoriaRepository.findById(compromissoDtoRequest.categoriaId()).orElseThrow(() -> new RuntimeException("Catégoria não encontrada."));
        compromissoModel.atualizarCom(compromissoDtoRequest, categoriaModel);
        return compromissoRepository.save(compromissoModel).toDtoResponse();
    }
    @Transactional
    public void deleteCompromissoPorId(Long userId, Long compromissoId ){
        CompromissoModel compromissoModel = compromissoRepository.findById(compromissoId).orElseThrow(() -> new RuntimeException("Compromisso não encontrado."));
        if(!compromissoModel.getUsuario().getId().equals(userId)){
            throw new RuntimeException("Esse compromisso não pertence ao usuário.");
        }
        compromissoRepository.delete(compromissoModel);
    }
}
