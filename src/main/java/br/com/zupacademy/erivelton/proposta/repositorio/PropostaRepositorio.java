package br.com.zupacademy.erivelton.proposta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.erivelton.proposta.entidade.Proposta;

@Repository
public interface PropostaRepositorio extends JpaRepository<Proposta, Long>{

}
