package zup.orange_talents.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;

import zup.orange_talents.dtos.RegistroVacinacao.CadastroRegistroVacinacaoDto;
import zup.orange_talents.dtos.RegistroVacinacao.RegistroVacinacaoCreateDto;
import zup.orange_talents.dtos.RegistroVacinacao.RegistroVacinacaoReadDto;
import zup.orange_talents.dtos.RegistroVacinacao.VacinaRegistroVacinacaoDto;

import zup.orange_talents.repository.RegistroVacinacaoRepository;
import zup.orange_talents.repository.CadastroRepository;
import zup.orange_talents.repository.VacinaRepository;

import zup.orange_talents.models.RegistroVacinacaoModel;

@RestController @RequestMapping(path = "/registros")
public class RegistroVacinacaoController {

    private VacinaRepository _vacinaRepository;
    private CadastroRepository _cadastroRepository;
    private RegistroVacinacaoRepository _RegistroVacinacaoRepository;
    private ModelMapper modelMapper; 

    public RegistroVacinacaoController( RegistroVacinacaoRepository registroVacinacaoRepository,
                                        VacinaRepository vacinaRepository,
                                        CadastroRepository cadastroRepository
                                        ) {
        this._vacinaRepository = vacinaRepository;
        this._RegistroVacinacaoRepository = registroVacinacaoRepository;
        this._cadastroRepository = cadastroRepository;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping
    ResponseEntity<List<RegistroVacinacaoReadDto>> getRegistros(){
        List<RegistroVacinacaoReadDto> listaRegistrosDto = new ArrayList<>();
   
        _RegistroVacinacaoRepository.findAll().forEach(registro -> {
                RegistroVacinacaoReadDto mappedRegistro = modelMapper.map(registro, RegistroVacinacaoReadDto.class);
                mappedRegistro.setPessoa( modelMapper.map(registro.getPessoa(), CadastroRegistroVacinacaoDto.class) );
                mappedRegistro.setVacina( modelMapper.map(registro.getVacina(), VacinaRegistroVacinacaoDto.class) );
                listaRegistrosDto.add(mappedRegistro);
            }
        );

        return listaRegistrosDto.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaRegistrosDto); 
    }

    @PostMapping
    ResponseEntity<RegistroVacinacaoReadDto> novoRegistro(@RequestBody RegistroVacinacaoCreateDto novoRegistro/* ,
                                                            UriBuilder uriBuilder */
                                                            ){
        var vacina = _vacinaRepository.findByNome(novoRegistro.getNomeVacina());
        var cadastro = _cadastroRepository.findByCpf(novoRegistro.getCpfVacinado());
        boolean existeCadastro = _cadastroRepository.existsCadastroByCpf(novoRegistro.getCpfVacinado());
        System.out.println(String.format("%1$s %2$s", vacina, cadastro));
        return ResponseEntity.ok().build();
    }
    
}
