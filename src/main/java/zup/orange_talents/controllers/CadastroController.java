package zup.orange_talents.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import zup.orange_talents.services.Services;
import zup.orange_talents.dtos.Cadastro.CadastroCreateDto;
import zup.orange_talents.dtos.Cadastro.CadastroReadDto;
import zup.orange_talents.dtos.Cadastro.CadastroUpdateDto;
import zup.orange_talents.repository.CadastroRepository;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {

    private Services services;

    private CadastroController(CadastroRepository cadastroRepository){
        this.services = new Services(cadastroRepository);
    }

    @GetMapping
    ResponseEntity<List<CadastroReadDto>> getCadastros(){
        List<CadastroReadDto> listaCadastrosDto = services.getAllCadastros();
        return listaCadastrosDto.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaCadastrosDto);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<CadastroReadDto> getCadastroById(@PathVariable Long id) {
        if ( services.hasCadastro(id)){
            CadastroReadDto  cadastroDto = services.getCadastro(id);
            return ResponseEntity.ok().body(cadastroDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CadastroReadDto());
    }

    @PostMapping
    public ResponseEntity<CadastroReadDto> createCadastro(@RequestBody CadastroCreateDto novoCadastro,
                                                            UriComponentsBuilder uriBuilder ){
        CadastroReadDto cadastroReadDto = services.createAndSaveCadastro(novoCadastro);
        URI uri = uriBuilder.path("/cadastros/{id}").buildAndExpand(cadastroReadDto.getId()).toUri();
        return ResponseEntity.created(uri).body( cadastroReadDto );
    }

    @PutMapping(path="/{email}")
    public ResponseEntity<?> updateCadastro(@RequestBody @Valid CadastroUpdateDto cadastroUpdate,
                                            @PathVariable @Email String email) {
        if (services.hasCadastro(email)) {
            CadastroReadDto updatedCadastro = services.updateCadastro(cadastroUpdate, email);
            return ResponseEntity.ok(updatedCadastro);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(services.CADASTRO_NAO_ENCONTRADO);
    }

    @DeleteMapping(path="/{email}")
    public ResponseEntity<?> deleteCadastro(@PathVariable @Email String email){
        boolean cadastroDeleted = services.deleteCadastro(email);

        return cadastroDeleted 
            ? ResponseEntity.noContent().build() 
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body(services.CADASTRO_NAO_ENCONTRADO); 
    }
}