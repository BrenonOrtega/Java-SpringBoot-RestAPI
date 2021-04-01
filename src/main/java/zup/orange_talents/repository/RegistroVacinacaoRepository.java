package zup.orange_talents.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import zup.orange_talents.models.RegistroVacinacaoModel;

public interface RegistroVacinacaoRepository extends JpaRepository<RegistroVacinacaoModel, Long>{
    List<RegistroVacinacaoModel> findByPessoa_Id(long id);
}
