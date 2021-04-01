package zup.orange_talents.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import zup.orange_talents.dtos.Cadastro.CadastroCreateDto;
import zup.orange_talents.dtos.Cadastro.CadastroReadDto;
import zup.orange_talents.models.CadastroModel;
import zup.orange_talents.repository.CadastroRepository;
import zup.orange_talents.repository.RegistroVacinacaoRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {

    private ModelMapper modelMapper = new ModelMapper();
    private CadastroRepository _cadastroRepository;
    private RegistroVacinacaoRepository _registroVacinacaoRepository;

    private CadastroController(
            CadastroRepository cadastroRepository, 
            RegistroVacinacaoRepository registroVacinacaoRepository
        ){
        this._cadastroRepository = cadastroRepository;
        this._registroVacinacaoRepository = registroVacinacaoRepository;
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<CadastroReadDto> getCadastrosById(@PathVariable Long id) {

        return _cadastroRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(modelMapper.map(record, CadastroReadDto.class) ))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    ResponseEntity<List<CadastroReadDto>> getCadastros(){
        List<CadastroReadDto> listaCadastros = new ArrayList<>();

        _cadastroRepository.findAll()
            .forEach(cadastro -> {
                CadastroReadDto mapped = modelMapper.map(cadastro, CadastroReadDto.class);
                listaCadastros.add(mapped);
            });

        if (listaCadastros.isEmpty()){ 
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(listaCadastros);
    }

    @PostMapping
    public ResponseEntity<CadastroReadDto> novoCadastro(
        @RequestBody @Valid CadastroCreateDto novoCadastro,
        UriComponentsBuilder uriBuilder
    ){
        CadastroModel cadastro = novoCadastro.createEntity();
        _cadastroRepository.save(cadastro); 
        URI uri = uriBuilder.path("/cadastros/{id}").buildAndExpand(cadastro.getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(cadastro, CadastroReadDto.class));
    }
}

