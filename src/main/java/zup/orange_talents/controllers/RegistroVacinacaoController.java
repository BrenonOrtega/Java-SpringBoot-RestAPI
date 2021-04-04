package zup.orange_talents.controllers;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import zup.orange_talents.services.Services;

import zup.orange_talents.dtos.RegistroVacinacao.RegistroVacinacaoCreateDto;
import zup.orange_talents.dtos.RegistroVacinacao.RegistroVacinacaoReadDto;

import zup.orange_talents.repository.RegistroVacinacaoRepository;
import zup.orange_talents.repository.CadastroRepository;
import zup.orange_talents.repository.VacinaRepository;

@RestController 
@RequestMapping(path = "/registros")
public class RegistroVacinacaoController {

    private Services services;

    public RegistroVacinacaoController( RegistroVacinacaoRepository registroVacinacaoRepository,
                                        VacinaRepository vacinaRepository,
                                        CadastroRepository cadastroRepository){

        this.services = new Services(vacinaRepository, registroVacinacaoRepository, cadastroRepository);
    }

    @GetMapping
    ResponseEntity<List<RegistroVacinacaoReadDto>> getAllRegistros(){
        List<RegistroVacinacaoReadDto> listaRegistrosDto = services.getAllRegistros();

        return listaRegistrosDto.isEmpty() 
                ? ResponseEntity.noContent().build() 
                : ResponseEntity.ok(listaRegistrosDto); 
    }

    @GetMapping(path="/{id}")
    ResponseEntity<RegistroVacinacaoReadDto> getRegistro(@PathVariable long id){
        RegistroVacinacaoReadDto registro = services.getRegistroById(id);

        return registro == null  
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.ok(registro); 
    }

    @PostMapping
    ResponseEntity<?> novoRegistro(@RequestBody @Valid RegistroVacinacaoCreateDto novoRegistroCreateDto,
                                                            UriComponentsBuilder uriBuilder){
        String email = novoRegistroCreateDto.getEmailVacinado();
        String nomeVacina = novoRegistroCreateDto.getNomeVacina();

        if (services.hasCadastro(email) == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(services.CADASTRO_NAO_ENCONTRADO);
        }   

        if (services.isCadastroVacinated(email, nomeVacina)){ 
            return ResponseEntity.badRequest().body(services.CADASTRO_JA_VACINADO);
        }
        var createdRegistro =  services.CreateAndSaveRegistroVacinacao(novoRegistroCreateDto);
        URI uri = uriBuilder.path("/registros/{id}").buildAndExpand(createdRegistro.getId()).toUri();
        return ResponseEntity.created(uri).body(createdRegistro);
    }

    @PutMapping(path="/{id}")
    ResponseEntity<RegistroVacinacaoReadDto> updateRegistro(@RequestBody RegistroVacinacaoCreateDto registroUpdate, 
                                        @PathVariable long id) {
        RegistroVacinacaoReadDto updatedRegistro =  services.updateRegistro(registroUpdate, id);
        boolean isRegistroUpdated = updatedRegistro != null;                                  
        return isRegistroUpdated 
            ? ResponseEntity.ok(updatedRegistro) 
            : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(path="/{id}")
    ResponseEntity<?> deleteCadastro(@PathVariable long id){
        boolean isRegistroDeleted = services.deleteRegistro(id);
        return isRegistroDeleted 
        ? ResponseEntity.noContent().build()
        : ResponseEntity.status(HttpStatus.NOT_FOUND).body(services.REGISTRO_NAO_ENCONTRADO);
    }
    
}
