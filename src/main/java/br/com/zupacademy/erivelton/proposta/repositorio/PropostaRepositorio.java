package br.com.zupacademy.erivelton.proposta.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.erivelton.proposta.entidade.Proposta;
import br.com.zupacademy.erivelton.proposta.enums.StatusFinanceiro;

@Repository
public interface PropostaRepositorio extends JpaRepository<Proposta, Long>{

	Optional<Proposta> findByDocumento(String documento);

	List<Proposta> findAllByEstadoAndCartaoIsNull(StatusFinanceiro estado);
}
