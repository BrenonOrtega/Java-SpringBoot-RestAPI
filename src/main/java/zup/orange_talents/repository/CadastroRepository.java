package zup.orange_talents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zup.orange_talents.models.CadastroModel;

public interface CadastroRepository extends JpaRepository<CadastroModel, Long> {
    CadastroModel findByCpfIgnoreCase(String cpf);
    CadastroModel findByEmailIgnoreCase(String cpf);
    boolean existsCadastroByEmailIgnoreCase(String email);
    boolean existsCadastroByCpfIgnoreCase(String cpf);
}
