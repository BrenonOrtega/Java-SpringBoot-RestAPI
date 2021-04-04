package zup.orange_talents.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.modelmapper.ModelMapper;

import zup.orange_talents.dtos.Cadastro.CadastroCreateDto;
import zup.orange_talents.dtos.Cadastro.CadastroReadDto;
import zup.orange_talents.dtos.Cadastro.CadastroUpdateDto;
import zup.orange_talents.dtos.Cadastro.RegistroVacinacaoCadastroDto;
import zup.orange_talents.dtos.Cadastro.VacinaCadastroDto;
import zup.orange_talents.dtos.RegistroVacinacao.CadastroRegistroVacinacaoDto;
import zup.orange_talents.dtos.RegistroVacinacao.RegistroVacinacaoCreateDto;
import zup.orange_talents.dtos.RegistroVacinacao.RegistroVacinacaoReadDto;

import zup.orange_talents.dtos.RegistroVacinacao.VacinaRegistroVacinacaoDto;
import zup.orange_talents.models.CadastroModel;
import zup.orange_talents.models.RegistroVacinacaoModel;
import zup.orange_talents.models.VacinaModel;
import zup.orange_talents.repository.CadastroRepository;
import zup.orange_talents.repository.RegistroVacinacaoRepository;
import zup.orange_talents.repository.VacinaRepository;

public class Services {
    
    
    RegistroVacinacaoRepository registroVacinacaoRepository;
    CadastroRepository cadastroRepository;
    VacinaRepository vacinaRepository; 
    static ModelMapper modelMapper = new ModelMapper();

    public final String CADASTRO_JA_VACINADO = "{\"error\":\"Este usuário já foi vacinado.\"}";
    public final String CADASTRO_NAO_ENCONTRADO = "{\"error\":\"Cadastro não Encontrado.\"}";
    public final String REGISTRO_NAO_ENCONTRADO = "{\"error\":\"Registro não Encontrado\"}";
    
    //#region Construtores
    public Services(VacinaRepository vacinaRepository, 
        RegistroVacinacaoRepository registroVacinacaoRepository,
		CadastroRepository cadastroRepository) {
            this.cadastroRepository = cadastroRepository;
            this.vacinaRepository = vacinaRepository;
            this.registroVacinacaoRepository = registroVacinacaoRepository;
	}

    public Services (CadastroRepository cadastroRepository){
        this.cadastroRepository = cadastroRepository;
    }
    //#endregion

    //#region Booleans
	public boolean hasCadastro(String email){ 
        return cadastroRepository.existsCadastroByEmailIgnoreCase(email);
    }

    public boolean hasCadastro(long id){
        return cadastroRepository.existsById(id);
    }

    public boolean hasVacina(String nomeVacina){
        return vacinaRepository.existsByNomeIgnoreCase(nomeVacina);
    }

    public boolean isCadastroVacinated(String emailCadastro, String nomeVacina){
        boolean alreadyVacinated = false;
        if (hasCadastro(emailCadastro) && hasVacina(nomeVacina)){
            CadastroModel cadastro = cadastroRepository.findByEmailIgnoreCase(emailCadastro);
            VacinaModel vacina = vacinaRepository.findByNomeIgnoreCase(nomeVacina);
            for(int i = 0; i < cadastro.getRegistroVacinacoes().size(); i++){
                if (cadastro.getRegistroVacinacoes().get(i).getVacina().getNome() == vacina.getNome()){
                    alreadyVacinated = true;
                    break;
                }
            }
        }
        return alreadyVacinated;
    }

    //#endregion

    //#region get lists
    public List<RegistroVacinacaoReadDto> getAllRegistros() {
        List<RegistroVacinacaoReadDto> listaRegistrosDto = new ArrayList<>();
        registroVacinacaoRepository.findAll().forEach(registro -> {
            RegistroVacinacaoReadDto mappedRegistro = this.mapRegistroVacinacaoDto(registro);
            listaRegistrosDto.add(mappedRegistro);
        });
        return listaRegistrosDto;
    }

    public List<CadastroReadDto> getAllCadastros(){
        List<CadastroReadDto> listaCadastrosDto = new ArrayList<>();

        cadastroRepository.findAll().forEach(cadastro -> {
            CadastroReadDto mappedCadastro =this.mapCadastroDto(cadastro);
            listaCadastrosDto.add(mappedCadastro);
        });
        return listaCadastrosDto;
    }

    //#endregion

    //#region Get Single instance
    public CadastroReadDto getCadastro(long id){
        CadastroModel cadastro = cadastroRepository.findById(id).orElse(null);
        CadastroReadDto mappedCadastro =this.mapCadastroDto(cadastro);
        return mappedCadastro;    
    }

    public RegistroVacinacaoReadDto getRegistroById(long id) {
        RegistroVacinacaoModel registro = registroVacinacaoRepository.findById(id).orElse(null);
        if (registro ==null) { return null; }

        RegistroVacinacaoReadDto registroDto = this.mapRegistroVacinacaoDto(registro);
        return registroDto;
    }

    //#endregion

    //#region saving and creating entities
    public RegistroVacinacaoReadDto CreateAndSaveRegistroVacinacao(RegistroVacinacaoCreateDto novoRegistroCreateDto) {
        if (this.hasVacina(novoRegistroCreateDto.getNomeVacina()) == false){
            this.CreateAndSaveVacina(novoRegistroCreateDto.getNomeVacina());
        }
        var cadastro = cadastroRepository.findByEmailIgnoreCase(novoRegistroCreateDto.getEmailVacinado());
        VacinaModel vacina = vacinaRepository.findByNomeIgnoreCase(novoRegistroCreateDto.getNomeVacina());
        RegistroVacinacaoModel novoRegistroModel = modelMapper.map(novoRegistroCreateDto, RegistroVacinacaoModel.class);
        novoRegistroModel.setPessoa(cadastro);
        novoRegistroModel.setVacina(vacina);
        registroVacinacaoRepository.save(novoRegistroModel);
        RegistroVacinacaoReadDto novoRegistroReadDto = modelMapper.map(novoRegistroModel, RegistroVacinacaoReadDto.class);
        return novoRegistroReadDto;
    }

    public VacinaModel CreateAndSaveVacina(String nomeVacina) {
        VacinaModel novaVacina = new VacinaModel();
        novaVacina.setNome(nomeVacina);
        VacinaModel vacina = vacinaRepository.save(novaVacina);
        return vacina;
    }

    public CadastroReadDto createAndSaveCadastro(@Valid CadastroCreateDto novoCadastro){
        CadastroModel cadastro = modelMapper.map(novoCadastro, CadastroModel.class);
        cadastroRepository.save(cadastro);
        return modelMapper.map(cadastro, CadastroReadDto.class);
    }

    //#endregion

    //#region updating entities

    public CadastroReadDto updateCadastro(CadastroUpdateDto cadastroUpdate, @Email String email) {
        CadastroModel cadastro = cadastroRepository.findByEmailIgnoreCase(email);

        String novoEmail = cadastroUpdate.getEmail() == "" ? cadastro.getEmail() : cadastroUpdate.getEmail();
        Date novaDataNascimento = cadastroUpdate.getDataNascimento() == null ? cadastro.getDataNascimento() : cadastroUpdate.getDataNascimento();
        String novoPrimeiroNome = cadastroUpdate.getPrimeiroNome() == "" ? cadastro.getPrimeiroNome() : cadastroUpdate.getPrimeiroNome();
        String novoSobrenome = cadastroUpdate.getSobrenome() == "" ? cadastro.getSobrenome() : cadastroUpdate.getSobrenome();
        
        cadastro.setEmail(novoEmail);
        cadastro.setDataNascimento(novaDataNascimento);
        cadastro.setPrimeiroNome(novoPrimeiroNome);
        cadastro.setSobrenome(novoSobrenome);
        
        cadastroRepository.save(cadastro);
        CadastroReadDto updatedCadastro = modelMapper.map(cadastro, CadastroReadDto.class);
        return updatedCadastro;
    }

    public RegistroVacinacaoReadDto updateRegistro(@Valid RegistroVacinacaoCreateDto registroUpdate, long id) {
        RegistroVacinacaoModel registro = registroVacinacaoRepository.findById(id).orElse(null);
        if(registro == null) { return modelMapper.map(registro, RegistroVacinacaoReadDto.class); }

        String novoNomeVacina = registroUpdate.getNomeVacina();
        String novoEmailVacinado = registroUpdate.getEmailVacinado();
        Timestamp novaDataVacinacao = new Timestamp(registroUpdate.getVacinatedAt().getTime());

        CadastroModel pessoaVacinadaUpdate = this.hasCadastro(novoEmailVacinado) 
            ? cadastroRepository.findByEmailIgnoreCase(novoEmailVacinado)
            : null;

        VacinaModel vacinaUpdate = this.hasVacina(novoNomeVacina) 
            ? vacinaRepository.findByNomeIgnoreCase(novoNomeVacina)
            : null; 
        if (vacinaUpdate == null) {
            vacinaUpdate = this.CreateAndSaveVacina(novoNomeVacina);
        } 
        if (pessoaVacinadaUpdate == null ){ return null; }

        registro.setPessoa(pessoaVacinadaUpdate);
        registro.setVacina(vacinaUpdate);
        registro.setVacinatedAt(novaDataVacinacao);
        registroVacinacaoRepository.save(registro);

        RegistroVacinacaoReadDto registroUpdatedReadDto = modelMapper.map(registro, RegistroVacinacaoReadDto.class); 
        return registroUpdatedReadDto;
    }

    //#endregion

    //#region Deleting Entities
    public boolean deleteCadastro(String email) {
        if (this.hasCadastro(email)){
            CadastroModel cadastro = cadastroRepository.findByEmailIgnoreCase(email);
            cadastroRepository.delete(cadastro);
            return true;  
        }
        return false;
    }

    public boolean deleteRegistro(long id){
        RegistroVacinacaoModel registro = registroVacinacaoRepository.findById(id).orElse(null);

        if (registro == null) {
            return false;
        } else {
            registroVacinacaoRepository.delete(registro);
            return true;
        }
    }    

    //#endregion

    //#region Entity Map Functions
    private RegistroVacinacaoReadDto mapRegistroVacinacaoDto(RegistroVacinacaoModel registro){
        RegistroVacinacaoReadDto mappedRegistro = modelMapper.map(registro, RegistroVacinacaoReadDto.class);
        mappedRegistro.setPessoa( modelMapper.map(registro.getPessoa(), CadastroRegistroVacinacaoDto.class) );
        mappedRegistro.setVacina( modelMapper.map(registro.getVacina(), VacinaRegistroVacinacaoDto.class) );
        return mappedRegistro;
    }
    
    private CadastroReadDto mapCadastroDto(CadastroModel cadastro) {
        CadastroReadDto mappedCadastro = modelMapper.map(cadastro, CadastroReadDto.class);
            cadastro.getRegistroVacinacoes().forEach(registro -> {
                RegistroVacinacaoCadastroDto mappedRegistro = modelMapper.map(registro, RegistroVacinacaoCadastroDto.class);
                VacinaCadastroDto vacina = modelMapper.map(registro.getVacina(), VacinaCadastroDto.class);
                mappedRegistro.setVacina(vacina);
                mappedCadastro.registroVacinacoes.add(mappedRegistro);
            });
            return mappedCadastro;
    }

    //#endregion
}
