package zup.orange_talents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zup.orange_talents.models.VacinaModel;

public interface VacinaRepository extends JpaRepository<VacinaModel, Long> {
    VacinaModel findByNomeIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String Nome);
}
