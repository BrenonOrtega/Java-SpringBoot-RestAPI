package zup.orange_talents.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import zup.orange_talents.dtos.Cadastro.CadastroCreateDto;
import zup.orange_talents.dtos.Cadastro.CadastroReadDto;
import zup.orange_talents.dtos.Cadastro.RegistroVacinacaoCadastroDto;
import zup.orange_talents.dtos.Cadastro.VacinaCadastroDto;
import zup.orange_talents.models.CadastroModel;
import zup.orange_talents.repository.CadastroRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;


@RestController
@RequestMapping("/cadastros")
public class CadastroController {

    private ModelMapper modelMapper = new ModelMapper();
    private CadastroRepository _cadastroRepository;

    private CadastroController(CadastroRepository cadastroRepository){
        this._cadastroRepository = cadastroRepository;
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<CadastroReadDto> getCadastroById(@PathVariable Long id) {
        CadastroModel cadastro = _cadastroRepository.findById(id).orElse(null);

        if (cadastro != null){
            CadastroReadDto  cadastroDto = modelMapper.map(cadastro, CadastroReadDto.class);

            cadastro.getRegistroVacinacoes().forEach(registro -> {
                RegistroVacinacaoCadastroDto mappedRegistro = modelMapper.map(registro, RegistroVacinacaoCadastroDto.class);
                VacinaCadastroDto vacina = modelMapper.map(registro.getVacina(), VacinaCadastroDto.class);
                mappedRegistro.setVacina(vacina);
                cadastroDto.registroVacinacoes.add(mappedRegistro);
            });

            return ResponseEntity.ok().body(cadastroDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    ResponseEntity<List<CadastroReadDto>> getCadastros(){
        List<CadastroReadDto> listaCadastrosDto = new ArrayList<>();

        _cadastroRepository.findAll()
            .forEach(cadastro -> {
                CadastroReadDto mappedCadastro = modelMapper.map(cadastro, CadastroReadDto.class);
                cadastro.getRegistroVacinacoes().forEach(registro -> {
                    RegistroVacinacaoCadastroDto mappedRegistro = modelMapper.map(registro, RegistroVacinacaoCadastroDto.class);
                    VacinaCadastroDto vacina = modelMapper.map(registro.getVacina(), VacinaCadastroDto.class);
                    mappedRegistro.setVacina(vacina);
                    mappedCadastro.registroVacinacoes.add(mappedRegistro);
                });

                listaCadastrosDto.add(mappedCadastro);
            });

        return listaCadastrosDto.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaCadastrosDto);
    }

    @PostMapping
    public ResponseEntity<CadastroReadDto> createCadastro(
        @RequestBody @Valid CadastroCreateDto novoCadastro,
        UriComponentsBuilder uriBuilder
    ){
        CadastroModel cadastro = modelMapper.map(novoCadastro, CadastroModel.class);
        _cadastroRepository.save(cadastro); 
        URI uri = uriBuilder.path("/cadastros/{id}").buildAndExpand(cadastro.getId()).toUri();
        return ResponseEntity.created(uri).body( modelMapper.map(cadastro, CadastroReadDto.class) );
    }
} 

