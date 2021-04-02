package zup.orange_talents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zup.orange_talents.models.VacinaModel;

public interface VacinaRepository extends JpaRepository<VacinaModel, Long> {
    public VacinaModel findByNome(String nome);
}
