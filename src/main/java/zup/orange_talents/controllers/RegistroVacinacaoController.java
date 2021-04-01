package zup.orange_talents.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zup.orange_talents.dtos.RegistroVacinacao.RegistroVacinacaoReadDto;
import zup.orange_talents.repository.CadastroRepository;
import zup.orange_talents.repository.RegistroVacinacaoRepository;

@RestController @RequestMapping(path = "/registros")
public class RegistroVacinacaoController {

    private CadastroRepository _CadastroRepository;
    private RegistroVacinacaoRepository _RegistroVacinacaoRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public RegistroVacinacaoController(
        CadastroRepository cadastroRepository, 
        RegistroVacinacaoRepository registroVacinacaoRepository
    ) {
        this._CadastroRepository = cadastroRepository;
        this._RegistroVacinacaoRepository = registroVacinacaoRepository;
    }

    @GetMapping
    ResponseEntity<List<RegistroVacinacaoReadDto>> getRegistros(){
        List<RegistroVacinacaoReadDto> registrosDto = new ArrayList<>();

        _RegistroVacinacaoRepository.findAll()
            .forEach(registro -> {
                RegistroVacinacaoReadDto mapped = modelMapper.map(registro, RegistroVacinacaoReadDto.class);
                registrosDto.add(mapped);
            });

        if (registrosDto.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(registrosDto);
        
    }
    
}
